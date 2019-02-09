package ru.vito.web.app.jersey.rest.rest;

import org.javamoney.moneta.Money;
import org.junit.Test;
import ru.vito.web.app.jersey.model.dto.request.MoneyTransferRequest;
import ru.vito.web.app.jersey.model.dto.response.OperationsDto;
import ru.vito.web.app.jersey.model.types.MoneyTransferStatus;
import ru.vito.web.app.jersey.model.types.OperationType;
import ru.vito.web.app.jersey.rest.BaseTest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static ru.vito.web.app.jersey.rest.commons.TestUtils.*;

public class AccountControllerTest extends BaseTest {

    @Test
    public void getBalance() {
        final String moneyString = getBalance(FIRST_ACCOUNT_ID);

        assertEquals(moneyString, ONE_HUNDRED_USD.subtract(TWENTY_USD).toString());
    }

    private String getBalance(final String accountId) {
        final Invocation.Builder request = target("/getBalance/" + accountId).request();

        final Response response = request.get();
        assertEquals(EXPECTED_STATUS.getStatusCode(), response.getStatus());

        return response.readEntity(String.class);
    }

    @Test
    public void transferMoneyOk() {
        final Invocation.Builder request = target("/moneyTransfer").request(MediaType.APPLICATION_JSON);

        final MoneyTransferRequest moneyTransferRequest = MoneyTransferRequest.builder()
                .fromAccountId(FIRST_ACCOUNT_ID)
                .toAccountId(SECOND_ACCOUNT_ID)
                .currencyCode(TWENTY_USD.getCurrency().getCurrencyCode())
                .number(TWENTY_USD.getNumberStripped())
                .build();

        final Money balanceFirstAccountBeforeTransfer = Money.parse(getBalance(FIRST_ACCOUNT_ID));
        final Money balanceSecondAccountBeforeTransfer = Money.parse(getBalance(SECOND_ACCOUNT_ID));
        final Response response = request.post(Entity.entity(moneyTransferRequest, MediaType.APPLICATION_JSON));
        assertEquals(EXPECTED_STATUS.getStatusCode(), response.getStatus());

        final Money balanceFirstAccountAfterTransfer = Money.parse(getBalance(FIRST_ACCOUNT_ID));
        final Money balanceSecondAccountAfterTransfer = Money.parse(getBalance(SECOND_ACCOUNT_ID));

        assertEquals(MoneyTransferStatus.SUCCESS.name(), response.readEntity(String.class));

        assertEquals(balanceFirstAccountBeforeTransfer.subtract(TWENTY_USD), balanceFirstAccountAfterTransfer);
        assertEquals(balanceSecondAccountBeforeTransfer.add(TWENTY_USD), balanceSecondAccountAfterTransfer);
    }

    @Test
    public void transferMoneyFail_insufficientFunds() {
        final Invocation.Builder request = target("/moneyTransfer").request(MediaType.APPLICATION_JSON);

        final MoneyTransferRequest moneyTransferRequest = MoneyTransferRequest.builder()
                .fromAccountId(SECOND_ACCOUNT_ID)
                .toAccountId(FIRST_ACCOUNT_ID)
                .currencyCode(ONE_HUNDRED_USD.getCurrency().getCurrencyCode())
                .number(ONE_HUNDRED_USD.getNumberStripped())
                .build();

        final Response response = request.post(Entity.entity(moneyTransferRequest, MediaType.APPLICATION_JSON));
        assertEquals(EXPECTED_STATUS.getStatusCode(), response.getStatus());

        assertEquals(MoneyTransferStatus.FAIL.name(), response.readEntity(String.class));
    }

    @Test
    public void transferMoneyFail_accountNotExists() {
        final Invocation.Builder request = target("/moneyTransfer").request(MediaType.APPLICATION_JSON);

        final String nonexistentAccountId = "-1";
        final MoneyTransferRequest moneyTransferRequest = MoneyTransferRequest.builder()
                .fromAccountId(FIRST_ACCOUNT_ID)
                .toAccountId(nonexistentAccountId)
                .currencyCode(TWENTY_USD.getCurrency().getCurrencyCode())
                .number(TWENTY_USD.getNumberStripped())
                .build();

        final Response response = request.post(Entity.entity(moneyTransferRequest, MediaType.APPLICATION_JSON));
        assertEquals(EXPECTED_STATUS.getStatusCode(), response.getStatus());

        assertEquals(MoneyTransferStatus.FAIL.name(), response.readEntity(String.class));
    }

    @Test
    public void balanceIsEqualByDifferentWayToCounting() {
        final Money balanceByMethod = Money.parse(getBalance(FIRST_ACCOUNT_ID));

        final Invocation.Builder request = target("/getAllOperation/" + FIRST_ACCOUNT_ID).request();
        final Response response = request.accept(MediaType.APPLICATION_JSON).get();
        assertEquals(EXPECTED_STATUS.getStatusCode(), response.getStatus());

        final OperationsDto operationsDto = response.readEntity(OperationsDto.class);
        assertEquals(balanceByMethod.getNumber().longValueExact(),
                operationsDto.getOperations().stream()
                        .map(o -> {
                            if (OperationType.DEBIT.equals(o.getOperationType())) {
                                return o.getAmount().negate();
                            }
                            return o.getAmount();
                        })
                        .reduce(BigDecimal.ZERO, BigDecimal::add).longValue()
        );
    }
}

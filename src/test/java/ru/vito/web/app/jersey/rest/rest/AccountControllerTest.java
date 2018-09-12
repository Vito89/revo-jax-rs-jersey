package ru.vito.web.app.jersey.rest.rest;

import org.junit.Test;
import ru.vito.web.app.jersey.model.dto.request.MoneyTransferRequest;
import ru.vito.web.app.jersey.model.types.MoneyTransferStatus;
import ru.vito.web.app.jersey.rest.BaseTest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class AccountControllerTest extends BaseTest {

    @Test
    public void checkBalanceTest() {
        final Invocation.Builder request = target("/getBalance/" + DEFAULT_ACCOUNT_ID).request();

        final Response response = request.get();
        assertEquals(EXPECTED_STATUS.getStatusCode(), response.getStatus());

        final String moneyString = response.readEntity(String.class);

        assertEquals(moneyString, ONE_HUNDRED_USD.toString());
    }

    @Test
    public void transferMoneyOk() {
        final Invocation.Builder request = target("/moneyTransfer").request(MediaType.APPLICATION_JSON);

        final MoneyTransferRequest moneyTransferRequest = MoneyTransferRequest.builder().build();
        moneyTransferRequest.setFromAccountId("test01");
        moneyTransferRequest.setToAccountId("test01");

        final Response response = request.post(Entity.entity(moneyTransferRequest, MediaType.APPLICATION_JSON));

        assertEquals(EXPECTED_STATUS.getStatusCode(), response.getStatus());

        assertEquals(MoneyTransferStatus.SUCCESS.name(), response.readEntity(String.class));
    }

//    @Test
//    public void getAllOperationsTest() {
//        final Invocation.Builder request = target("/root/newurl").request();
//
//        final int responseStatus = request.get().getStatus();
//        assertEquals(HttpStatus.OK_200.getStatusCode(), responseStatus);
//
//        assertEquals(null, request);
//    }
}

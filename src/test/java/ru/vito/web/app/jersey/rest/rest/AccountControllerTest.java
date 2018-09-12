package ru.vito.web.app.jersey.rest.rest;

import org.junit.Test;
import ru.vito.web.app.jersey.rest.BaseTest;

import javax.ws.rs.client.Invocation;
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
    public void transferMoneyGood() {
        final Invocation.Builder request = target("/moneyTransfer").request();

//        double val = 0.00;
//        for (int i = 0; i < 10; i++)
//            val += 0.10;
//        System.out.println( val == 1.00 );
//        System.out.println( val == 1.00 );

//        Entity<?> entity = null; // TODO
//        final Response response = request.post(entity);
//
//        final int responseStatus = response.getStatus();
//        assertEquals(HttpStatus.OK_200.getStatusCode(), responseStatus);
//
//        assertEquals(MoneyTransferStatus.SUCCESS, response.getEntity());
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

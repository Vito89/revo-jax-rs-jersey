package ru.vito.web.app.jersey.rest.rest;

import org.junit.Test;
import ru.vito.web.app.jersey.rest.BaseTest;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AccountControllerTest extends BaseTest {

    @Test
    public void checkBalanceTest() {
        final Invocation.Builder request = target("/getBalance/" + DEFAULT_ACCOUNT_ID).request();

        final Response response = request.get();
        assertEquals(EXPECTED_STATUS.getStatusCode(), response.getStatus());

        assertThat(response.readEntity(Long.class), instanceOf(Long.class));
    }

    @Test
    public void transferMoneyGood() {
        final Invocation.Builder request = target("/moneyTransfer").request();

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

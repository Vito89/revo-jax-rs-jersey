package ru.vito.web.app.jersey.rest.rest;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Test;
import ru.vito.web.app.jersey.rest.BaseTest;

import javax.ws.rs.client.Invocation;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AccountControllerTest extends BaseTest {

    private static final String DEFAULT_ACCOUNT_ID = "777";

    @Test
    public void checkBalanceTest() {
        final Invocation.Builder request = target("/getBalance/" + DEFAULT_ACCOUNT_ID).request();

        final int responseStatus = request.get().getStatus();
        assertEquals(HttpStatus.OK_200.getStatusCode(), responseStatus);

        assertThat(request.get(Long.class), instanceOf(Long.class));
    }
}

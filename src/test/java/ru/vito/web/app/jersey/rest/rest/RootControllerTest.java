package ru.vito.web.app.jersey.rest.rest;

import org.junit.Test;
import ru.vito.web.app.jersey.rest.BaseTest;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class RootControllerTest extends BaseTest {

    @Test
    public void healthCheckTest() {
        final Invocation.Builder request = target("/root/healthCheck").request();

        final Response response = request.get();
        assertEquals(EXPECTED_STATUS.getStatusCode(), response.getStatus());

        assertEquals(EXPECTED_STATUS.getReasonPhrase(), response.getStatusInfo().toString());
    }
}

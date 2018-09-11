package ru.vito.web.app.jersey.rest.rest;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.Test;
import ru.vito.web.app.jersey.rest.BaseTest;

import javax.ws.rs.client.Invocation;

import static org.junit.Assert.assertEquals;

public class RootControllerTest extends BaseTest {

    @Test
    public void healthCheckTest() {
        final Invocation.Builder request = target("/root/healthCheck").request();

        final int responseStatus = request.get().getStatus(); // TODO By Aspects
        assertEquals(HttpStatus.OK_200.getStatusCode(), responseStatus);

        final String responseBody = request.get(String.class);
        assertEquals("OK", responseBody);
    }
}

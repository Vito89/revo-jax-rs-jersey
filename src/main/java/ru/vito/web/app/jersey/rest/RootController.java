package ru.vito.web.app.jersey.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/root")
public class RootController {

    @GET
    @Produces(TEXT_PLAIN)
    @Path("/healthCheck")
    public Response healthCheck() {
        final String body = "OK";
        return Response.ok(body).build();
    }
}

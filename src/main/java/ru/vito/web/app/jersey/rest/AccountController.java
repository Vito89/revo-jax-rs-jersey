package ru.vito.web.app.jersey.rest;

import ru.vito.web.app.jersey.service.AccountService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountController {

    private AccountService accountService;

    @Inject
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Produces(TEXT_PLAIN)
    @Path("getBalance/{accountId}")
    public Response getBalance(@PathParam(value = "accountId") final String accountId) {
        final Long amount = accountService.getBalance(accountId);

        return Response.ok(amount).build();
    }
}

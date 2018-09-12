package ru.vito.web.app.jersey.rest;

import org.javamoney.moneta.Money;
import ru.vito.web.app.jersey.model.dto.request.MoneyTransferRequest;
import ru.vito.web.app.jersey.model.types.MoneyTransferStatus;
import ru.vito.web.app.jersey.service.AccountService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/")
public class AccountController {

    @Inject
    private AccountService accountService;

    @GET
    @Produces(TEXT_PLAIN)
    @Path("getBalance/{accountId}")
    public Response getBalance(@PathParam(value = "accountId") final String accountId) {
        final Money amount = accountService.getBalance(accountId);

        return Response.ok(amount.toString()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("moneyTransfer")
    public Response moneyTransfer(final MoneyTransferRequest moneyTransferRequest) {
        final MoneyTransferStatus transferStatus = accountService.moneyTransfer();

        return Response.ok(transferStatus.name()).build();
    }
}

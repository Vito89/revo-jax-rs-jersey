package ru.vito.web.app.jersey.service;

import org.javamoney.moneta.Money;
import org.jvnet.hk2.annotations.Contract;
import ru.vito.web.app.jersey.model.dto.request.MoneyTransferRequest;
import ru.vito.web.app.jersey.model.types.MoneyTransferStatus;

@Contract
public interface AccountService {

    Money getBalance(String accountId);

    MoneyTransferStatus moneyTransfer(MoneyTransferRequest moneyTransferRequest);
}

package ru.vito.web.app.jersey.service;

import org.jvnet.hk2.annotations.Contract;
import ru.vito.web.app.jersey.model.types.MoneyTransferStatus;

@Contract
public interface AccountService {

    Long getBalance(String accountId);

    MoneyTransferStatus moneyTransfer();
}

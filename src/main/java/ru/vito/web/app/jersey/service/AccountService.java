package ru.vito.web.app.jersey.service;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface AccountService {

    Long getBalance(String accountId);
}

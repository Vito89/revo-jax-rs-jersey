package ru.vito.web.app.jersey.service.impl;

import ru.vito.web.app.jersey.service.AccountService;

import javax.inject.Singleton;

//@Resource
//@Service
//@ManagedBean
@Singleton
public class AccountServiceImpl implements AccountService {

    public Long getBalance(String accountId) {
        return 0L;
    }
}

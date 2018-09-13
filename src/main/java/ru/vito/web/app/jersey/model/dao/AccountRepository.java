package ru.vito.web.app.jersey.model.dao;

import org.javamoney.moneta.Money;
import org.jvnet.hk2.annotations.Contract;
import ru.vito.web.app.jersey.model.entity.Operation;

import java.util.List;

@Contract
public interface AccountRepository {
    List<Operation> getAllOperations(final String accountId);

    void addOperation(String accountFrom, String accountTo, Money transfer);

    boolean accountNonExist(final String accountId);
}

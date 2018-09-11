package ru.vito.web.app.jersey.model.dao;

import org.jvnet.hk2.annotations.Contract;
import ru.vito.web.app.jersey.model.entity.Operation;

import java.util.List;

@Contract
public interface AccountRepository {
    public List<Operation> getAllOperations(final String accountId);
}

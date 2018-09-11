package ru.vito.web.app.jersey.model.dao.impl;

import ru.vito.web.app.jersey.model.dao.AccountRepository;
import ru.vito.web.app.jersey.model.entity.Operation;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Singleton
public class AccountRepositoryImpl implements AccountRepository {

    public List<Operation> getAllOperations(final String accountId) {
        return Optional.ofNullable(AbstractRepository.data.get(accountId)).orElse(Collections.emptyList());
    }
}

package ru.vito.web.app.jersey.model.dao.impl;

import org.javamoney.moneta.Money;
import ru.vito.web.app.jersey.model.dao.AccountRepository;
import ru.vito.web.app.jersey.model.entity.Operation;
import ru.vito.web.app.jersey.model.types.OperationType;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class AccountRepositoryImpl implements AccountRepository {

    public List<Operation> getAllOperations(final String accountId) {
        return Optional.ofNullable(AbstractRepository.data.get(accountId)).orElse(Collections.emptyList());
    }

  //@Transactional
    @Override
    public void addOperation(final String accountFrom, final String accountTo, final Money transfer) {
        List<Operation> operations = AbstractRepository.data.get(accountFrom);
        Operation operation = Operation.builder()
                .id((UUID.randomUUID().getLeastSignificantBits() + 16) * 100)
                .operationType(OperationType.DEBIT)
                .createdDate(LocalDateTime.now())
                .partnerAccountId(accountTo)
                .amount(transfer)
                .build();
        operations.add(operation);
        AbstractRepository.data.put(accountFrom, operations);

        operations = AbstractRepository.data.get(accountTo);
        operation = Operation.builder()
                .id((UUID.randomUUID().getLeastSignificantBits() + 16) * 100)
                .operationType(OperationType.CREDIT)
                .createdDate(LocalDateTime.now())
                .partnerAccountId(accountFrom)
                .amount(transfer)
                .build();
        operations.add(operation);
        AbstractRepository.data.put(accountTo, Collections.singletonList(operation));


    }

    @Override
    public boolean accountNonExist(final String accountId) {
        return !AbstractRepository.data.containsKey(accountId);
    }
}

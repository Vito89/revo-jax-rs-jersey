package ru.vito.web.app.jersey.service.impl;

import org.javamoney.moneta.Money;
import ru.vito.web.app.jersey.model.dao.AccountRepository;
import ru.vito.web.app.jersey.model.entity.Operation;
import ru.vito.web.app.jersey.model.types.MoneyTransferStatus;
import ru.vito.web.app.jersey.model.types.OperationType;
import ru.vito.web.app.jersey.service.AccountService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountRepository accountRepository;

    @Override
    public Money getBalance(final String accountId) {
        final List<Operation> operations = accountRepository.getAllOperations(accountId);

        return operations.stream()
                .filter(operation -> (OperationType.CREDIT.equals(operation.getOperationType()) ||
                        OperationType.DEBIT.equals(operation.getOperationType())))
                .map(o -> {
                    if (OperationType.DEBIT.equals(o.getOperationType())) {
                        return o.getAmount().negate();
                    }
                    return o.getAmount();
                })
                .reduce(Money.of(0, "USD"), Money::add);
    }

    @Override
    public MoneyTransferStatus moneyTransfer() {
        return MoneyTransferStatus.SUCCESS;  // TODO
    }
}

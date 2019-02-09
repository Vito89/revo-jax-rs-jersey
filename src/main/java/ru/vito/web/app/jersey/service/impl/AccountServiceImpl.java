package ru.vito.web.app.jersey.service.impl;

import org.javamoney.moneta.Money;
import ru.vito.web.app.jersey.model.dao.AccountRepository;
import ru.vito.web.app.jersey.model.dto.request.MoneyTransferRequest;
import ru.vito.web.app.jersey.model.dto.response.OperationDto;
import ru.vito.web.app.jersey.model.dto.response.OperationsDto;
import ru.vito.web.app.jersey.model.entity.Operation;
import ru.vito.web.app.jersey.model.types.MoneyTransferStatus;
import ru.vito.web.app.jersey.model.types.OperationType;
import ru.vito.web.app.jersey.service.AccountService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class AccountServiceImpl implements AccountService {

    @Inject
    private AccountRepository accountRepository;

    @Override
    public Money getBalance(final String accountId) {
        if (accountRepository.accountNonExist(accountId)) {
            throw new RuntimeException("account non-existent");
        }
        final List<Operation> operations = accountRepository.getAllOperations(accountId);

        return operations.stream()
                .filter(operation -> (OperationType.CREDIT.equals(operation.getOperationType())
                        || OperationType.DEBIT.equals(operation.getOperationType())))
                .map(o -> {
                    if (OperationType.DEBIT.equals(o.getOperationType())) {
                        return o.getAmount().negate();
                    }
                    return o.getAmount();
                })
                .reduce(Money.of(0, "USD"), Money::add);
    }

    @Override
    public MoneyTransferStatus moneyTransfer(final MoneyTransferRequest moneyTransferRequest) {
        final Money transfer = Money.of(moneyTransferRequest.getNumber(), moneyTransferRequest.getCurrencyCode());
        final String accountFrom = moneyTransferRequest.getFromAccountId();
        final String accountTo = moneyTransferRequest.getToAccountId();
        if (accountRepository.accountNonExist(accountFrom) || accountRepository.accountNonExist(accountTo)) {
            // log.error event
            return MoneyTransferStatus.FAIL;
        }

        final Money moneyAccountFrom = getBalance(accountFrom);
        if (moneyAccountFrom.isLessThan(transfer)) {
            return MoneyTransferStatus.FAIL;
        }

        try {
            doTransfer(accountFrom, accountTo, transfer);
        } catch (Exception e) {
            e.printStackTrace();
            return MoneyTransferStatus.FAIL;
        }

        return MoneyTransferStatus.SUCCESS;
    }

    @Override
    public OperationsDto getAllOperation(final String accountId) {
        return new OperationsDto(
                accountRepository.getAllOperations(accountId).stream()
                        .map(o -> {
                            final Money oAmount = o.getAmount();
                            return OperationDto.builder()   // TODO modelMapper http://modelmapper.org/
                                    .operationType(o.getOperationType())
                                    .createdDate(o.getCreatedDate().toString())
                                    .partnerAccountId(o.getPartnerAccountId())
                                    .amount(oAmount.getNumberStripped())
                                    .currencyCode(oAmount.getCurrency().getCurrencyCode())

                                    .build();
                        }).collect(Collectors.toList()));
    }

    private void doTransfer(final String accountFrom, final String accountTo, final Money transfer) {
        accountRepository.addOperation(accountFrom, accountTo, transfer);
    }
}

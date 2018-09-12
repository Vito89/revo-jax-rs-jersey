package ru.vito.web.app.jersey.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoneyTransferRequest {
    private String fromAccountId;
    private String toAccountId;
    private Money money;
}
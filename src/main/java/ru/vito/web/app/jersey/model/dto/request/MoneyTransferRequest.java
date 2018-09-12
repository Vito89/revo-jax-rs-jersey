package ru.vito.web.app.jersey.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoneyTransferRequest {
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal number;
    private String currencyCode;
}
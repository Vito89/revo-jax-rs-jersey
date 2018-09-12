package ru.vito.web.app.jersey.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;
import ru.vito.web.app.jersey.model.types.OperationType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    private Long id;
    private String partnerAccountId;
    private LocalDateTime createdDate;
    private OperationType operationType;
    private Money amount;
}

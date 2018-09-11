package ru.vito.web.app.jersey.model.dto;

import ru.vito.web.app.jersey.model.types.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OperationDto {
    private Long accountId;
    private LocalDateTime createdDate;
    private OperationType operationType;
    private BigDecimal amount;
}

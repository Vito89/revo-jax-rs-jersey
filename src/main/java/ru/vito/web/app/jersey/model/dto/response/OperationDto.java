package ru.vito.web.app.jersey.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vito.web.app.jersey.model.types.OperationType;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto implements Serializable {
    private OperationType operationType;
    private String createdDate;
    private String partnerAccountId;
    private BigDecimal amount;
    private String currencyCode;
}

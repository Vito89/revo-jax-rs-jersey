package ru.vito.web.app.jersey.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OperationsDto implements Serializable {

    private List<OperationDto> operations;
}

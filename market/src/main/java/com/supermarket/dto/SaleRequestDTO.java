package com.supermarket.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaleRequestDTO {
    
    @NotNull(message = "El ID del empleado es obligatorio.")
    private Long employeeId;

    @NotEmpty(message = "La venta debe contener al menos un producto.")
    @Valid
    private List<SalesDetailRequestDTO> items;

}

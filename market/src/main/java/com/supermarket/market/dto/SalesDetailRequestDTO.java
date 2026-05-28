package com.supermarket.market.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SalesDetailRequestDTO {
    
    @NotNull(message = "El ID del producto es obligatorio.")
    private Long productId;

    @NotNull(message = "La cantidad es obligatoria.")
    @Min(value = 1, message = "La cantidad mínima a vender debe ser 1.")
    private Integer quantitiy;
}

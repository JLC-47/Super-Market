package com.supermarket.dto;

import java.util.Set;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductResgisterDTO {
    
    @NotBlank(message = "El nombre del producto es obligatorio y no puede estar vacío.")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
    private String name;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres.")
    private String description;

    @NotBlank(message = "El código de barras es obligatorio.")
    @Size(min = 5, max = 50, message = "El código de barras debe tener entre 5 y 50 caracteres.")
    private String barcode;

    @NotNull(message = "El precio es obligatorio.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser un valor positivo y mayor a 0.")
    private Double price;

    @NotNull(message = "El stock inicial es obligatorio.")
    @Min(value = 0, message = "El stock inicial no puede ser un número negativo.")
    private Long stock;

    private boolean status;

    @NotNull(message = "La categoría del producto es obligatoria.")
    private Long categoryId;

    @NotNull(message = "La lista de proveedores no puede ser nula.")
    private Set<Long> supplierIds;

}

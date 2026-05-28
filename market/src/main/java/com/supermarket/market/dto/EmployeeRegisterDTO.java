package com.supermarket.market.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeRegisterDTO {

    @NotBlank(message = "La cédula es obligatoria")
    @Size(max = 20, message = "La cédula no puede tener más de 20 caracteres")
    private String nationalId;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150, message = "El nombre es demasiado largo")
    private String name;

    @NotBlank(message = "El cargo es obligatorio (ADMINISTRADOR, CAJERO, AUXILIAR)")
    private String role;

    @NotNull(message = "La fecha de contratación es obligatoria")
    private LocalDate hireDate;

    @NotNull(message = "El salario es obligatorio")
    @Min(value = 1, message = "El salario debe ser mayor a 0")
    private Double salary;
}
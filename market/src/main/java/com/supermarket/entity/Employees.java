package com.supermarket.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employees {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Corregido: nationalId
    @Column(name = "national_id", nullable = false, unique = true, length = 20 )
    private String nationalId;

    @Column(name = "name", nullable = false, length = 150 )
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    // Corregido: hireDate y nombre de la columna a hire_date
    @Column(name = "hire_date", nullable = false )
    private LocalDate hireDate;

    @Column(name = "salary", nullable = false )
    private Double salary;

    // Corregido: Mayúsculas y ortografía de ADMINISTRADOR
    public enum Role{
        ADMINISTRADOR, CAJERO, AUXILIAR
    }
}

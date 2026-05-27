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
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name =  "national_id", nullable = false, unique = true, length = 20 )
    private String natinalId;

    @Column(name =  "name", nullable = false,  length = 150 )
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name =  "role", nullable = false)
    private Role role;

    @Column(name =  "hirDate", nullable = false )
    private LocalDate hirDate;

    @Column(name =  "salary", nullable = false )
    private Double salary;


    public enum Role{
        adminstrador, cajero, auxiliar
    }
}

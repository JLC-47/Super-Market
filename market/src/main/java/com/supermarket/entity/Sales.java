package com.supermarket.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "sales")
public class Sales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name =  "sale_date", insertable = false, updatable = false)
    private LocalDateTime saleDate;

    @Column(name =  "subtotal", nullable = false)
    private Double subtotal;

    @Column(name =  "subtotal", nullable = false)
    private Double vat;

    @Column(name =  "total", nullable = false)
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employees employee;

    @OneToMany(mappedBy = "sale", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaleDetails> details;
}

package com.supermarket.dto;

import java.util.Set;

import lombok.Data;

@Data
public class ProductResgisterDTO {
    
    private String name;

    private String description;

    private String barcode;

    private Double price;

    private Long stock;
 
    private boolean status;


    private Long categoryId;

    private Set<Long> supplierIds;

} 
package com.supermarket.market.dto;

import java.util.Set;

import lombok.Data;

@Data
public class ProductResponseDTO {
    
    private Long id;

    private String name;

    private String description;

    private String barcode;

    private Double price;

    private Long stock;

    private boolean status;


    private Long categoryId;

    private Set<Long> supplierIds;

}

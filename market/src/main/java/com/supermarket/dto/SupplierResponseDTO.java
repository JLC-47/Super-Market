package com.supermarket.dto;

import lombok.Data;

@Data
public class SupplierResponseDTO {
    
    private Long id;
    private String nit;
    private String name;
    private String phone;
    private String address;
    
}
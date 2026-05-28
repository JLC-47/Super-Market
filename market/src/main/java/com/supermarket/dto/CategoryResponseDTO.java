package com.supermarket.dto;

import java.util.List;
import lombok.Data;

@Data
public class CategoryResponseDTO {
    
    private Long id;
    private String name;
    private String description;
    private List<ProductResponseDTO> products;
    
}
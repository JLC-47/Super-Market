package com.supermarket.service;

import com.supermarket.dto.CategoryRequestDTO;
import com.supermarket.dto.CategoryResponseDTO;
import com.supermarket.dto.ProductResponseDTO;
import com.supermarket.entity.Categories;
import com.supermarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String createCategory(CategoryRequestDTO data) {
        if (categoryRepository.existsByName(data.getName())) {
            throw new RuntimeException("Error: Ya existe una categoría con este nombre.");
        }
        
        Categories category = new Categories();
        category.setName(data.getName());
        category.setDescription(data.getDescription());
        
        categoryRepository.save(category);
        return "Categoría creada con éxito";
    }

    public List<CategoryResponseDTO> getAllCategories() {
        List<Categories> categories = categoryRepository.findByStatusTrue();
        
        return categories.stream().map(cat -> {
            CategoryResponseDTO dto = new CategoryResponseDTO();
            dto.setId(cat.getId());
            dto.setName(cat.getName());
            dto.setDescription(cat.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public CategoryResponseDTO getCategoryById(Long id) {
        Categories category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Categoría no encontrada."));

        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());

        if (category.getProducts() != null) {
            List<ProductResponseDTO> activeProducts = category.getProducts().stream()
                    .filter(product -> product.isStatus())
                    .map(product -> {
                        ProductResponseDTO productDto = new ProductResponseDTO();
                        productDto.setId(product.getId());
                        productDto.setName(product.getName());
                        productDto.setDescription(product.getDescription());
                        productDto.setBarcode(product.getBarcode());
                        productDto.setPrice(product.getPrice());
                        productDto.setStock(product.getStock());
                        productDto.setStatus(product.isStatus());
                        productDto.setCategoryId(category.getId());
                        return productDto;
                    }).collect(Collectors.toList());
            dto.setProducts(activeProducts);
        }

        return dto;
    }

    public String updateCategory(Long id, CategoryRequestDTO data) {
        Categories category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Categoría no encontrada."));

        if (!category.getName().equals(data.getName()) && categoryRepository.existsByName(data.getName())) {
            throw new RuntimeException("Error: El nombre ya está en uso.");
        }

        category.setName(data.getName());
        category.setDescription(data.getDescription());
        
        categoryRepository.save(category);
        return "Categoría actualizada con éxito";
    }

    public String deleteCategory(Long id) {
        Categories category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Categoría no encontrada."));

        category.setStatus(false);
        categoryRepository.save(category);
        
        return "Categoría eliminada correctamente.";
    }
}
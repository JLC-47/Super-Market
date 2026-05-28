package com.supermarket.service;

import com.supermarket.dto.CategoryRequestDTO;
import com.supermarket.dto.CategoryResponseDTO;
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

//(Crear)
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
//(Leer)
    public List<CategoryRequestDTO> getAllCategories() {
        List<Categories> categories = categoryRepository.findByStatusTrue();
        
        return categories.stream().map(cat -> {
            CategoryResponseDTO dto = new CategoryResponseDTO();
            dto.setId(cat.getId());
            dto.setName(cat.getName());
            dto.setDescription(cat.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }
//(Actualizar)
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
//(Eliminar - Borrado Lógico)
    public String deleteCategory(Long id) {
        Categories category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Categoría no encontrada."));

        category.setStatus(false);
        categoryRepository.save(category);
        
        return "Categoría eliminada correctamente.";
    }
}
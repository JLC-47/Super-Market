package com.supermarket.market.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.supermarket.market.dto.CategoryRequestDTO;
import com.supermarket.market.dto.CategoryResponseDTO;
import com.supermarket.market.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryRequestDTO data) {
        String result = categoryService.createCategory(data);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-categories")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<String> updateCategory(
            @PathVariable Long id, 
            @Valid @RequestBody CategoryRequestDTO data) {
        
        String result = categoryService.updateCategory(id, data);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        String result = categoryService.deleteCategory(id);
        return ResponseEntity.ok(result);
    }
}
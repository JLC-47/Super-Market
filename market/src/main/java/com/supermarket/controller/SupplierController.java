package com.supermarket.controller;

import com.supermarket.dto.SupplierRequestDTO;
import com.supermarket.dto.SupplierResponseDTO;
import com.supermarket.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<String> createSupplier(@Valid @RequestBody SupplierRequestDTO data) {
        String result = supplierService.createSupplier(data);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        List<SupplierResponseDTO> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable Long id) {
        SupplierResponseDTO supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSupplier(
            @PathVariable Long id, 
            @Valid @RequestBody SupplierRequestDTO data) {
        String result = supplierService.updateSupplier(id, data);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        String result = supplierService.deleteSupplier(id);
        return ResponseEntity.ok(result);
    }
}
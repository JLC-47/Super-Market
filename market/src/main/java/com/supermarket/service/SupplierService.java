package com.supermarket.service;

import com.supermarket.dto.SupplierRequestDTO;
import com.supermarket.dto.SupplierResponseDTO;
import com.supermarket.entity.Suppliers;
import com.supermarket.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public String createSupplier(SupplierRequestDTO data) {
        // Usamos existsByTaxId
        if (supplierRepository.existsByTaxId(data.getNit())) {
            throw new RuntimeException("Error: Ya existe un proveedor con este NIT.");
        }
        
        Suppliers supplier = new Suppliers();
        supplier.setTaxId(data.getNit()); // Cambiado a setTaxId
        supplier.setName(data.getName());
        supplier.setPhone(data.getPhone());
        supplier.setAddress(data.getAddress());
        
        supplierRepository.save(supplier);
        return "Proveedor creado con éxito";
    }

    public List<SupplierResponseDTO> getAllSuppliers() {
        List<Suppliers> suppliers = supplierRepository.findAll();
        
        return suppliers.stream().map(sup -> {
            SupplierResponseDTO dto = new SupplierResponseDTO();
            dto.setId(sup.getId());
            dto.setNit(sup.getTaxId()); // Cambiado a getTaxId
            dto.setName(sup.getName());
            dto.setPhone(sup.getPhone());
            dto.setAddress(sup.getAddress());
            return dto;
        }).collect(Collectors.toList());
    }

    public SupplierResponseDTO getSupplierById(Long id) {
        Suppliers supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Proveedor no encontrado."));

        SupplierResponseDTO dto = new SupplierResponseDTO();
        dto.setId(supplier.getId());
        dto.setNit(supplier.getTaxId()); // Cambiado a getTaxId
        dto.setName(supplier.getName());
        dto.setPhone(supplier.getPhone());
        dto.setAddress(supplier.getAddress());
        return dto;
    }

    public String updateSupplier(Long id, SupplierRequestDTO data) {
        Suppliers supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Proveedor no encontrado."));

        // Usamos getTaxId y existsByTaxId
        if (!supplier.getTaxId().equals(data.getNit()) && supplierRepository.existsByTaxId(data.getNit())) {
            throw new RuntimeException("Error: El NIT ya está registrado en otro proveedor.");
        }

        supplier.setTaxId(data.getNit()); // Cambiado a setTaxId
        supplier.setName(data.getName());
        supplier.setPhone(data.getPhone());
        supplier.setAddress(data.getAddress());
        
        supplierRepository.save(supplier);
        return "Proveedor actualizado con éxito";
    }

    public String deleteSupplier(Long id) {
        Suppliers supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Proveedor no encontrado."));

        supplierRepository.delete(supplier);
        
        return "Proveedor eliminado correctamente.";
    }
}
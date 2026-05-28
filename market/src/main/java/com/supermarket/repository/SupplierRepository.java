package com.supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.entity.Suppliers;

public interface SupplierRepository  extends JpaRepository<Suppliers, Long>{
    
}
 
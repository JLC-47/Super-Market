package com.supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {

    boolean existsByBarcode(String barcode);
}

package com.supermarket.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.market.entity.Products;



public interface ProductRepository extends JpaRepository<Products, Long> {

    boolean existsByBarcode(String barcode);
}
 
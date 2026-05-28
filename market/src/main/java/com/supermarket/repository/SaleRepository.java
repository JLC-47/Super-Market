package com.supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.entity.Sales;

public interface SaleRepository  extends JpaRepository <Sales, Long>{
    
}

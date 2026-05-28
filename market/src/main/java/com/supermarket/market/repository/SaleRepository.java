package com.supermarket.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.market.entity.Sales;



public interface SaleRepository  extends JpaRepository <Sales, Long>{
    
}

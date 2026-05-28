package com.supermarket.market.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.market.entity.Suppliers;

@Repository
public interface SupplierRepository extends JpaRepository<Suppliers, Long> {



     boolean existsByTaxId(String taxId);
}


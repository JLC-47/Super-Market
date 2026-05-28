package com.supermarket.repository;

import com.supermarket.entity.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Suppliers, Long> {

}
     boolean existsByTaxId(String taxId);
}

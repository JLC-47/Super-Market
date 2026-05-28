package com.supermarket.market.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.market.entity.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    boolean existsByNationalId(String nationalId);
}
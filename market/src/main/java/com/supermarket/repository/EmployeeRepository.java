package com.supermarket.repository;

import com.supermarket.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    boolean existsByNationalId(String nationalId);
}
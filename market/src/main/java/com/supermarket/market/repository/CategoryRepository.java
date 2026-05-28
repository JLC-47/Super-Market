package com.supermarket.market.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.market.entity.Categories;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    
    List<Categories> findByStatusTrue();

    boolean existsByName(String name);
}
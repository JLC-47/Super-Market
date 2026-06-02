package com.supermarket.market.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.market.entity.Categories;



@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    

    boolean existsByName(String name);
}
package com.supermarket.repository;

import com.supermarket.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    
    List<Categories> findByStatusTrue();

    boolean existsByName(String name);
}
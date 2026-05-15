package com.stock.demo.infrastructure.output.jpa.repository;

import com.stock.demo.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
    Optional<CategoryEntity> findById(Long id);
    void deleteById(Long id);
    List<CategoryEntity> findAll();
}

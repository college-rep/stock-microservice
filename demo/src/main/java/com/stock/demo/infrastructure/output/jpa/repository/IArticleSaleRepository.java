package com.stock.demo.infrastructure.output.jpa.repository;

import com.stock.demo.infrastructure.output.jpa.entity.ArticleSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IArticleSaleRepository extends JpaRepository<ArticleSaleEntity, Long> {
    Optional<ArticleSaleEntity> findById(Long id);
    void deleteById(Long id);
    @Query("""
       SELECT a
       FROM ArticleSaleEntity a
       WHERE a.saleDate BETWEEN :startDate AND :endDate
       """)
    List<ArticleSaleEntity> findBySaleDate(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("""
       DELETE
       FROM ArticleSaleEntity a
       WHERE a.saleDate BETWEEN :startDate AND :endDate
       """)
    void deleteBySaleDateBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
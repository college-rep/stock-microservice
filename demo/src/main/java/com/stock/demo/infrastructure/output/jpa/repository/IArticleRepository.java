package com.stock.demo.infrastructure.output.jpa.repository;

import com.stock.demo.infrastructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<ArticleEntity> findByName(String brandName);
    void deleteById(Long id);
    Optional<ArticleEntity> findById(Long id);
}

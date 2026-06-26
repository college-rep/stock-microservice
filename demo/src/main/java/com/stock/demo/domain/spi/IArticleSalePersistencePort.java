package com.stock.demo.domain.spi;

import com.stock.demo.domain.model.ArticleSale;

import java.util.List;
import java.time.LocalDateTime;

public interface IArticleSalePersistencePort {
    ArticleSale createArticleSale(ArticleSale articleSale);
    ArticleSale getArticleSaleById(Long id);
    boolean articleSaleIdExists(Long id);
    List<ArticleSale> getArticleSales(LocalDateTime startDate, LocalDateTime endDate);
    void deleteArticleSaleById(Long id);
    void deleteArticleSalesById(LocalDateTime startDate, LocalDateTime endDate);
}

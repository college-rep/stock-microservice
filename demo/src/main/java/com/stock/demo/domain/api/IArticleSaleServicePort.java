package com.stock.demo.domain.api;

import com.stock.demo.domain.model.ArticleSale;

import java.util.List;
import java.time.LocalDateTime;

public interface IArticleSaleServicePort {
    void validate(ArticleSale articleSale);
    //this function will have a connection with the persistence
    ArticleSale createArticleSale(ArticleSale articleSale);
    ArticleSale getArticleSaleById(Long id);
    List<ArticleSale> getArticleSales(LocalDateTime startDate, LocalDateTime endDate);
    void deleteArticleSaleById(Long id);
    void deleteArticleSalesByDate(LocalDateTime startDate, LocalDateTime endDate);
}
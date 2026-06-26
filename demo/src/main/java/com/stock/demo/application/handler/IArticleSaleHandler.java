package com.stock.demo.application.handler;

import com.stock.demo.application.dto.ArticleSaleRequest;
import com.stock.demo.application.dto.ArticleSaleResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface IArticleSaleHandler {
    ArticleSaleResponse createArticleSale(ArticleSaleRequest categoryRequest);
    ArticleSaleResponse getArticleSaleResponseById(Long id);
    boolean articleSaleIdExists(Long id);
    List<ArticleSaleResponse> getArticleSaleResponses(LocalDateTime startDate, LocalDateTime endDate);
    void deleteArticleSaleById(Long id);
    void deleteArticleSalesByDate(LocalDateTime startDate, LocalDateTime endDate);
}

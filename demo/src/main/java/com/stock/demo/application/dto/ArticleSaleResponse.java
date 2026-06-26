package com.stock.demo.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleSaleResponse {
    private Long id;
    private Long idUser;
    private Long idArticle;
    private String name;
    private LocalDateTime saleDate;
    private Long amount;
    private BigDecimal unitPrice;
}

package com.stock.demo.domain.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSale {
    private Long id;
    private Long idUser;
    private Long idArticle;
    private String name;
    private LocalDateTime saleDate;
    private Long amount;
    private BigDecimal price;
}
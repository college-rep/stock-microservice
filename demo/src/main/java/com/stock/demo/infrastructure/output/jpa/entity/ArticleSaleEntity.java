package com.stock.demo.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="article_sale")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleSaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "id_user", nullable = true)
    private Long idUser;
    @Column(name = "id_article", nullable = false)
    private Long idArticle;
    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;
    private Long amount;
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;
}

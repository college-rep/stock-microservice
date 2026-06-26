package com.stock.demo.infrastructure.output.jpa.adapter;

import com.stock.demo.domain.model.ArticleSale;
import com.stock.demo.domain.spi.IArticleSalePersistencePort;
import com.stock.demo.infrastructure.output.jpa.mapper.IArticleSaleEntityMapper;
import com.stock.demo.infrastructure.output.jpa.repository.IArticleSaleRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ArticleSaleJpaAdapter implements IArticleSalePersistencePort {
    private final IArticleSaleRepository articleSaleRepository;
    private final IArticleSaleEntityMapper categoryEntityMapper;
    @Override
    public ArticleSale createArticleSale(ArticleSale articleSale) {
        return null;
    }

    @Override
    public ArticleSale getArticleSaleById(Long id) {
        return null;
    }

    @Override
    public boolean articleSaleIdExists(Long id) {
        return false;
    }

    @Override
    public List<ArticleSale> getArticleSales(LocalDateTime startDate, LocalDateTime endDate) {
        return List.of();
    }

    @Override
    public void deleteArticleSaleById(Long id) {

    }

    @Override
    public void deleteArticleSalesById(LocalDateTime startDate, LocalDateTime endDate) {

    }
}

package com.stock.demo.infrastructure.output.jpa.adapter;

import com.stock.demo.domain.model.ArticleSale;
import com.stock.demo.domain.spi.IArticleSalePersistencePort;
import com.stock.demo.infrastructure.exception.CategoryPersistenceException;
import com.stock.demo.infrastructure.output.jpa.entity.ArticleSaleEntity;
import com.stock.demo.infrastructure.output.jpa.entity.CategoryEntity;
import com.stock.demo.infrastructure.output.jpa.mapper.IArticleSaleEntityMapper;
import com.stock.demo.infrastructure.output.jpa.repository.IArticleSaleRepository;
import lombok.RequiredArgsConstructor;

import static com.stock.demo.util.ArticleSaleConstants.ARTICLE_SALE_NOT_FOUND;

import java.time.LocalDateTime;
import java.util.List;

import static com.stock.demo.util.CategoryConstants.CATEGORY_NOT_FOUND;

@RequiredArgsConstructor
public class ArticleSaleJpaAdapter implements IArticleSalePersistencePort {
    private final IArticleSaleRepository articleSaleRepository;
    private final IArticleSaleEntityMapper articleSaleEntityMapper;
    @Override
    public ArticleSale createArticleSale(ArticleSale articleSale) {
        ArticleSaleEntity articleSaleEntity = articleSaleEntityMapper.toEntity(articleSale);
        articleSaleEntity=articleSaleRepository.save(articleSaleEntity);
        return articleSaleEntityMapper.toArticleSale(articleSaleEntity);
    }

    @Override
    public ArticleSale getArticleSaleById(Long id) {
        ArticleSaleEntity articleSaleEntity = articleSaleRepository.findById(id).orElseThrow(()->new CategoryPersistenceException(
                ARTICLE_SALE_NOT_FOUND));
        return articleSaleEntityMapper.toArticleSale(articleSaleEntity);
    }

    @Override
    public boolean articleSaleIdExists(Long id) {
        return articleSaleRepository.findById(id).isPresent();
    }

    @Override
    public List<ArticleSale> getArticleSales(LocalDateTime startDate, LocalDateTime endDate) {
        List<ArticleSaleEntity> articleSaleEntities = articleSaleRepository.findBySaleDate(startDate, endDate);
        return articleSaleEntityMapper.toArticleSales(articleSaleEntities);
    }

    @Override
    public void deleteArticleSaleById(Long id) {
        articleSaleRepository.deleteById(id);
    }

    @Override
    public void deleteArticleSalesByDate(LocalDateTime startDate, LocalDateTime endDate) {
        articleSaleRepository.deleteBySaleDateBetween(startDate, endDate);
    }
}

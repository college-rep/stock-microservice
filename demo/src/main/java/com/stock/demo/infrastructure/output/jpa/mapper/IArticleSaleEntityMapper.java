package com.stock.demo.infrastructure.output.jpa.mapper;

import com.stock.demo.domain.model.ArticleSale;
import com.stock.demo.infrastructure.output.jpa.entity.ArticleSaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IArticleSaleEntityMapper {
    ArticleSaleEntity toEntity(ArticleSale articleSale);
    ArticleSale toArticleSale(ArticleSaleEntity articleSaleEntity);
    List<ArticleSale> toArticleSales(List<ArticleSaleEntity> articleSaleEntities);
}
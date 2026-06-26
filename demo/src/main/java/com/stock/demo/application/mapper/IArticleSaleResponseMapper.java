package com.stock.demo.application.mapper;

import com.stock.demo.application.dto.ArticleSaleRequest;
import com.stock.demo.domain.model.ArticleSale;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IArticleSaleResponseMapper {
    ArticleSale toArticleSaleResponse(ArticleSaleRequest categoryRequest);
    List<ArticleSale> toArticleSaleResponses(List<ArticleSaleRequest> categoryRequestList);
}
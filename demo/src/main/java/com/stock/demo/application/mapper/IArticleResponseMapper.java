package com.stock.demo.application.mapper;

import com.stock.demo.application.dto.ArticleResponse;
import com.stock.demo.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
uses={IArticleResponseMapper.class
        ,IBrandResponseMapper.class
        ,ICategoryResponseMapper.class})
public interface IArticleResponseMapper {
    @Mapping(source = "article.id", target = "id")
    @Mapping(source = "article.name", target = "name")
    @Mapping(source = "article.description", target = "description")
    @Mapping(source = "article.stock", target = "stock")
    @Mapping(source = "article.price", target = "price")
    @Mapping(source = "article.brand", target = "brand")
    @Mapping(source = "article.categories", target = "categories")
    ArticleResponse toArticleResponse(Article article);
}

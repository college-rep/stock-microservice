package com.stock.demo.domain.api;

import com.stock.demo.domain.model.Article;
import com.stock.demo.domain.usecase.PageResponse;

import java.util.List;


public interface IArticleServicePort {
    void validate(Article article);
    Article createArticle(Article article);
    Article getArticleById(Long id);
    List<Article> getArticles(Boolean ascendingOrder, String comparator);
    PageResponse<Article> getArticlePage(Boolean ascendingOrder, String comparator, Long pageSize, Long pageNumber);
    void validateGetArticlesRequestParam(Long pageSize, Long pageNumber,List<Article> articles);
    void updateArticle(Article article);
    void deleteArticle(Long id);
}

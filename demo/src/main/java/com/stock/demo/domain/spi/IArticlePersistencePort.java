package com.stock.demo.domain.spi;

import com.stock.demo.domain.model.Article;

import java.util.List;

public interface IArticlePersistencePort {
    Article createArticle(Article article);
    Article getArticleById(Long id);
    List<Article> getArticles();
    Boolean articleIdExists(Long id);
    Boolean articleNameExists(String name);
    void updateArticle(Article article);
    void deleteArticle(Long id);
}
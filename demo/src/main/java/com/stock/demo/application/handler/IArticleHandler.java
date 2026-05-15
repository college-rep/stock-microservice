package com.stock.demo.application.handler;

import com.stock.demo.application.dto.ArticleRequest;
import com.stock.demo.application.dto.ArticleResponse;
import com.stock.demo.domain.usecase.PageResponse;

import java.util.List;

public interface IArticleHandler {
    void createArticle(ArticleRequest articleRequest);
    ArticleResponse getArticleResponseById(Long id);
    List<ArticleResponse> getArticles(Boolean ascendingOrder, String comparator);
    PageResponse<ArticleResponse> getArticlePage(Boolean ascendingOrder, String comparator,Long pageSize,Long pageNumber);
    void updateArticle(ArticleRequest articleRequest);
}

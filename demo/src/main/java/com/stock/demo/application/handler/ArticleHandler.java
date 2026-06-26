package com.stock.demo.application.handler;

import com.stock.demo.application.dto.ArticleRequest;
import com.stock.demo.application.dto.ArticleResponse;
import com.stock.demo.application.mapper.IArticleRequestMapper;
import com.stock.demo.application.mapper.IArticleResponseMapper;
import com.stock.demo.domain.api.IArticleServicePort;
import com.stock.demo.domain.model.Article;
import com.stock.demo.domain.usecase.PageResponse;
import com.stock.demo.infrastructure.input.rest.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.stock.demo.util.ArticleConstants.ARTICLE_CREATED;


@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {
    private final IArticleServicePort articleServicePort;
    private final IArticleRequestMapper articleRequestMapper;
    private final IArticleResponseMapper articleResponseMapper;

    @Override
    public ArticleResponse createArticle(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);
        article = articleServicePort.createArticle(article);
        ArticleResponse articleResponse= articleResponseMapper.toArticleResponse(article);
        return articleResponse;
    }

    @Override
    public ArticleResponse getArticleResponseById(Long id) {
        Article article= articleServicePort.getArticleById(id);
        return articleResponseMapper.toArticleResponse(article);
    }

    @Override
    public List<ArticleResponse> getArticles(Boolean ascendingOrder, String comparator) {
        List<Article> articles=articleServicePort.getArticles(ascendingOrder,comparator);
        return articles
                .stream()
                .map(articleResponseMapper::toArticleResponse)
                .toList();
    }

    @Override
    public PageResponse<ArticleResponse> getArticlePage(Boolean ascendingOrder, String comparator,Long pageSize,Long pageNumber) {
        PageResponse<Article> articles = articleServicePort.getArticlePage(ascendingOrder,comparator,pageSize,pageNumber);
        List<ArticleResponse> articleResponses=new ArrayList<>();
        for(Article article:articles.getContent()){
            articleResponses.add(articleResponseMapper.toArticleResponse(article));
        }
        return new PageResponse<>(
                articleResponses,
                articles.getTotalPages(),
                articles.getTotalElements(),
                articles.getPageSize(),
                articles.getCurrentPage()
        );
    }

    @Override
    public void updateArticle(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.updateArticle(article);
    }
    @Override
    public void deleteArticle(Long id){articleServicePort.deleteArticle(id);}
}

package com.stock.demo.infrastructure.output.jpa.adapter;

import com.stock.demo.domain.model.Article;
import com.stock.demo.domain.spi.IArticlePersistencePort;
import com.stock.demo.infrastructure.output.jpa.entity.ArticleEntity;
import com.stock.demo.infrastructure.output.jpa.mapper.IArticleEntityMapper;
import com.stock.demo.infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {
    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;

    @Override
    public Article createArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toArticleEntity(article);
        articleEntity = articleRepository.save(articleEntity);
        return articleEntityMapper.toArticle(articleEntity);
    }

    @Override
    public Article getArticleById(Long id) {
        //I called .isPresent() before getting to this line, in another class
        ArticleEntity articleEntity= articleRepository.findById(id).get();
        return articleEntityMapper.toArticle(articleEntity);
    }

    @Override
    public List<Article> getArticles() {
        List<ArticleEntity> articleEntityList= articleRepository.findAll();
        return articleEntityMapper.toArticles(articleEntityList);
    }

    @Override
    public Boolean articleIdExists(Long id) {
        return articleRepository.findById(id).isPresent();
    }

    @Override
    public Boolean articleNameExists(String name) {
        return articleRepository.findByName(name).isPresent();
    }

    @Override
    public void updateArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toArticleEntity(article);
        articleRepository.save(articleEntity);
    }
    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}

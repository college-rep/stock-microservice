package com.stock.demo.domain.usecase;

import com.stock.demo.domain.api.IArticleSaleServicePort;
import com.stock.demo.domain.model.ArticleSale;
import com.stock.demo.domain.spi.IArticleSalePersistencePort;

import java.time.LocalDateTime;
import java.util.List;

//for some reason we did not use @RequiredArgsConstructor this time
//we used the class constructor, how funny
public class ArticleSaleUseCase implements IArticleSaleServicePort {
    private final IArticleSalePersistencePort articleSalePersistencePort;
    public ArticleSaleUseCase(IArticleSalePersistencePort categoryPersistencePort) {
        this.articleSalePersistencePort = categoryPersistencePort;
    }
    @Override
    public void validate(ArticleSale articleSale) {

    }

    @Override
    public ArticleSale createArticleSale(ArticleSale articleSale) {
        return null;
    }

    @Override
    public ArticleSale getArticleSaleById(Long id) {
        return null;
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

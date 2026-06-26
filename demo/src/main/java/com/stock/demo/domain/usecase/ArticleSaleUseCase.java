package com.stock.demo.domain.usecase;

import com.stock.demo.domain.api.IArticleSaleServicePort;
import com.stock.demo.domain.exception.ArticleSaleUseCaseException;
import com.stock.demo.domain.model.ArticleSale;
import com.stock.demo.domain.spi.IArticlePersistencePort;
import com.stock.demo.domain.spi.IArticleSalePersistencePort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.stock.demo.util.ArticleSaleConstants.*;
import static com.stock.demo.util.GenericConstants.EMPTY_BODY;

//for some reason we did not use @RequiredArgsConstructor this time
//we used the class constructor, how funny
public class ArticleSaleUseCase implements IArticleSaleServicePort {
    private final IArticleSalePersistencePort articleSalePersistencePort;
    private final IArticlePersistencePort articlePersistencePort;

    public ArticleSaleUseCase(
            IArticleSalePersistencePort articleSalePersistencePort,
            IArticlePersistencePort articlePersistencePort) {
        this.articleSalePersistencePort = articleSalePersistencePort;
        this.articlePersistencePort=articlePersistencePort;
    }
    @Override
    public void validate(ArticleSale articleSale) {
        List<String> errorList=new ArrayList<>();
        if(articleSale==null) {
            errorList.add(EMPTY_BODY);
            throw new ArticleSaleUseCaseException(errorList);
        }
        if(articleSale.getIdArticle()==null){
            errorList.add(ARTICLE_ID_NOT_PROVIDED);
        }
        else if(Boolean.FALSE.equals(articlePersistencePort.articleIdExists(articleSale.getIdArticle()))){
            errorList.add(ARTICLE_ID_NOT_FOUND);
        }
        if(articleSale.getAmount()==null){
            errorList.add(ARTICLE_AMOUNT_NOT_PROVIDED);
        }
        else if(articleSale.getAmount()<1){
            errorList.add(ARTICLE_AMOUNT_NOT_POSITIVE);
        }
        if(articleSale.getUnitPrice()==null){
            errorList.add(ARTICLE_PRICE_NOT_PROVIDED);
        }
        else if(articleSale.getUnitPrice().floatValue()<=0f){
            errorList.add(ARTICLE_PRICE_NOT_POSITIVE);
        }
        if(!errorList.isEmpty()) {
            throw new ArticleSaleUseCaseException(errorList);
        }
    }

    @Override
    public ArticleSale createArticleSale(ArticleSale articleSale) {
        validate(articleSale);
        articleSale = articleSalePersistencePort.createArticleSale(articleSale);
        return articleSale;
    }

    @Override
    public ArticleSale getArticleSaleById(Long id) {
        return articleSalePersistencePort.getArticleSaleById(id);
    }

    @Override
    public List<ArticleSale> getArticleSales(LocalDateTime startDate, LocalDateTime endDate) {
        return articleSalePersistencePort.getArticleSales(startDate, endDate);
    }

    @Override
    public void deleteArticleSaleById(Long id) {
        articleSalePersistencePort.deleteArticleSaleById(id);
    }

    @Override
    public void deleteArticleSalesByDate(LocalDateTime startDate, LocalDateTime endDate) {
        articleSalePersistencePort.deleteArticleSalesByDate(startDate, endDate);
    }
}

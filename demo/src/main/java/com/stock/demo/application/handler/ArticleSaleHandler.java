package com.stock.demo.application.handler;

import com.stock.demo.application.dto.ArticleSaleRequest;
import com.stock.demo.application.dto.ArticleSaleResponse;
import com.stock.demo.application.mapper.IArticleSaleRequestMapper;
import com.stock.demo.application.mapper.IArticleSaleResponseMapper;
import com.stock.demo.domain.api.IArticleSaleServicePort;
import com.stock.demo.domain.model.ArticleSale;

import com.stock.demo.domain.model.ArticleSale;
import com.stock.demo.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor//the private final variables will be injected
@Transactional
public class ArticleSaleHandler implements IArticleSaleHandler{
    //Could not autowire. No beans of 'IArticleSaleServicePort' type found.
    private final IArticleSaleServicePort articleSaleServicePort;
    private final IArticleSaleRequestMapper articleSaleRequestMapper;
    private final IArticleSaleResponseMapper articleSaleResponseMapper;
    @Override
    public ArticleSaleResponse createArticleSale(ArticleSaleRequest articleSaleRequest) {
        ArticleSale articleSale = articleSaleRequestMapper.toArticleSale(articleSaleRequest);
        articleSale=articleSaleServicePort.createArticleSale(articleSale);
        ArticleSaleResponse articleSaleResponse = articleSaleResponseMapper.toArticleSaleResponse(articleSale);
        return articleSaleResponse;
    }

    @Override
    public ArticleSaleResponse getArticleSaleResponseById(Long id) {
        ArticleSale articleSale = articleSaleServicePort.getArticleSaleById(id);
        return articleSaleResponseMapper.toArticleSaleResponse(articleSale);
    }

    @Override
    public boolean articleSaleIdExists(Long id) {
        return false;
    }

    @Override
    public List<ArticleSaleResponse> getArticleSaleResponses(LocalDateTime startDate, LocalDateTime endDate) {
        List<ArticleSale> articleSales = articleSaleServicePort.getArticleSales(startDate,endDate);
        return articleSales
                .stream()
                .map(articleSaleResponseMapper::toArticleSaleResponse)
                .toList();
    }

    @Override
    public void deleteArticleSaleById(Long id) {
        articleSaleServicePort.deleteArticleSaleById(id);
    }

    @Override
    public void deleteArticleSalesByDate(LocalDateTime startDate, LocalDateTime endDate) {
        articleSaleServicePort.deleteArticleSalesByDate(startDate,endDate);
    }
}

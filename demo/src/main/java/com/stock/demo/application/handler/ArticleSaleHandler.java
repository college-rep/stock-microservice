package com.stock.demo.application.handler;

import com.stock.demo.application.dto.ArticleSaleRequest;
import com.stock.demo.application.dto.ArticleSaleResponse;
import com.stock.demo.application.mapper.IArticleSaleRequestMapper;
import com.stock.demo.application.mapper.IArticleSaleResponseMapper;
import com.stock.demo.domain.api.IArticleSaleServicePort;
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
        return null;
    }

    @Override
    public ArticleSaleResponse getArticleSaleResponseById(Long id) {
        return null;
    }

    @Override
    public boolean articleSaleIdExists(Long id) {
        return false;
    }

    @Override
    public List<ArticleSaleResponse> getArticleSaleResponses(LocalDateTime startDate, LocalDateTime endDate) {
        return List.of();
    }

    @Override
    public void deleteArticleSaleById(Long id) {

    }

    @Override
    public void deleteArticleSalesById(LocalDateTime startDate, LocalDateTime endDate) {

    }
}

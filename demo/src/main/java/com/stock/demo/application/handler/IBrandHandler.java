package com.stock.demo.application.handler;

import com.stock.demo.application.dto.BrandRequest;
import com.stock.demo.application.dto.BrandResponse;

import java.util.List;

public interface IBrandHandler {
    void createBrand(BrandRequest brandRequest);
    BrandResponse getBrandResponseById(Long id);
    BrandResponse getBrandResponseByName(String name);
    List<BrandResponse> getBrandResponses(Boolean ascendingOrder);
    void updateBrand(BrandRequest brandRequest);
    void deleteBrand(Long id);
}

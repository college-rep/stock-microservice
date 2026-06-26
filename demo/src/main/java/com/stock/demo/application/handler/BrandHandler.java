package com.stock.demo.application.handler;

import com.stock.demo.application.dto.BrandRequest;
import com.stock.demo.application.dto.BrandResponse;
import com.stock.demo.application.mapper.IBrandRequestMapper;
import com.stock.demo.application.mapper.IBrandResponseMapper;
import com.stock.demo.domain.api.IBrandServicePort;
import com.stock.demo.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;
    @Override
    public void createBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.createBrand(brand);
    }

    @Override
    public BrandResponse getBrandResponseById(Long id) {
        Brand brand= brandServicePort.getBrandById(id);
        return brandResponseMapper.toBrandResponse(brand);
    }

    @Override
    public BrandResponse getBrandResponseByName(String name) {
        Brand brand = brandServicePort.getBrandByName(name);
        return brandResponseMapper.toBrandResponse(brand);
    }
    @Override
    public List<BrandResponse> getBrandResponses(Boolean ascendingOrder) {
        List<Brand> brands = brandServicePort.getBrands(ascendingOrder);
        return brands
                .stream()
                .map(brandResponseMapper::toBrandResponse)
                .toList();
    }

    @Override
    public void updateBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.updateBrand(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        brandServicePort.deleteBrand(id);
    }
}

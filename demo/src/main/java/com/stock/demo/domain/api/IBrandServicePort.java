package com.stock.demo.domain.api;

import com.stock.demo.domain.model.Brand;

import java.util.List;

public interface IBrandServicePort {
    void validate(Brand brand);
    void createBrand(Brand brand);
    Brand getBrandById(Long id);
    Brand getBrandByName(String name);
    List<Brand> getBrands(Boolean ascendingOrder);
    void updateBrand(Brand brand);
    void deleteBrand(Long id);
}

package com.stock.demo.infrastructure.output.jpa.adapter;

import com.stock.demo.domain.model.Brand;
import com.stock.demo.domain.spi.IBrandPersistencePort;
import com.stock.demo.infrastructure.exception.BrandPersistenceException;
import com.stock.demo.infrastructure.output.jpa.entity.BrandEntity;
import com.stock.demo.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.stock.demo.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.stock.demo.util.BrandConstants.BRAND_NAME_ALREADY_EXISTS;
import static com.stock.demo.util.BrandConstants.BRAND_NOT_FOUND;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    @Override
    public Brand createBrand(Brand brand) {
        if(brandRepository.findByName(brand.getName()).isPresent()) {
            throw new BrandPersistenceException(BRAND_NAME_ALREADY_EXISTS);
        }
        BrandEntity brandEntity = brandEntityMapper.toBrandEntity(brand);
        brandEntity=brandRepository.save(brandEntity);
        return brandEntityMapper.toBrand(brandEntity);
    }

    @Override
    public Brand getBrandById(Long id) {
        BrandEntity brandEntity = brandRepository.findById(id)
                .orElseThrow(()->new BrandPersistenceException(
                        BRAND_NOT_FOUND));
        return brandEntityMapper.toBrand(brandEntity);
    }

    @Override
    public Brand getBrandByName(String name) {
        BrandEntity brandEntity = brandRepository.findByName(name)
                .orElseThrow(()-> new BrandPersistenceException(
                        BRAND_NOT_FOUND));
        return brandEntityMapper.toBrand(brandEntity);
    }

    @Override
    public boolean brandNameExists(String name) {
        return brandRepository.findByName(name).isPresent();
    }

    @Override
    public boolean brandIdExists(Long id) {
        return brandRepository.findById(id).isPresent();
    }
    @Override
    public List<Brand> getBrands() {
        List<BrandEntity> brands=brandRepository.findAll();
        return brandEntityMapper.toBrands(brands);
    }
    public void updateBrand(Brand brand) {
        BrandEntity brandEntity = brandEntityMapper.toBrandEntity(brand);
        brandRepository.save(brandEntity);
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}

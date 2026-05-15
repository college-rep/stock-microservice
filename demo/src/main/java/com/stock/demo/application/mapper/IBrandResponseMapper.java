package com.stock.demo.application.mapper;

import com.stock.demo.application.dto.BrandResponse;
import com.stock.demo.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandResponseMapper {
    BrandResponse toBrandResponse(Brand brand);
}

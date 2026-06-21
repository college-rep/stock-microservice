package com.stock.demo.application.handler;


import com.stock.demo.application.dto.CategoryRequest;
import com.stock.demo.application.dto.CategoryResponse;

import java.util.List;
public interface ICategoryHandler {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse getCategoryResponseById(Long id);
    CategoryResponse getCategoryResponseByName(String id);
    List<CategoryResponse> getCategoryResponses(Boolean ascendingOrder);
    CategoryResponse updateCategory(CategoryRequest categoryRequest);
    void deleteCategoryById(Long id);
}

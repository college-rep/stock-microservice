package com.stock.demo.application.handler;

import com.stock.demo.application.dto.CategoryRequest;
import com.stock.demo.application.dto.CategoryResponse;
import com.stock.demo.application.mapper.ICategoryRequestMapper;
import com.stock.demo.application.mapper.ICategoryResponseMapper;
import com.stock.demo.domain.api.ICategoryServicePort;
import com.stock.demo.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor//the private final variables will be injected
@Transactional
public class CategoryHandler implements ICategoryHandler{
    //very similar methods to the domain infrastructure, but with elements
    //of the same layer, we're using requests and responses.
    //we'll make the orchestration of the different use cases
    //and the injection using annotations
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;
    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category= categoryRequestMapper.toCategory(categoryRequest);
        category=categoryServicePort.createCategory(category);
        //System.out.println("test for category in categoryHandler, response");
        //System.out.println(category.getId());
        CategoryResponse categoryResponse=categoryResponseMapper.toCategoryResponse(category);
        //System.out.println("test for category response mapper in categoryHandler");
        //System.out.println(categoryResponse.getId());
        return categoryResponse;
    }

    @Override
    public CategoryResponse getCategoryResponseById(Long id) {
        //validation will belong to the infrastructure
        Category category = categoryServicePort.getCategoryById(id);
        return categoryResponseMapper.toCategoryResponse(category);
    }
    @Override
    public CategoryResponse getCategoryResponseByName(String name) {
        //validation will belong to the infrastructure
        Category category = categoryServicePort.getCategoryByName(name);
        return categoryResponseMapper.toCategoryResponse(category);
    }
    @Override
    public List<CategoryResponse> getCategoryResponses(Boolean ascendingOrder) {
        List<Category> categories = categoryServicePort.getCategories(ascendingOrder);
        return categories
                .stream()
                .map(categoryResponseMapper::toCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest) {
        Category category= categoryRequestMapper.toCategory(categoryRequest);
        return categoryResponseMapper.toCategoryResponse(categoryServicePort.updateCategory(category));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryServicePort.deleteCategoryById(id);
    }
}

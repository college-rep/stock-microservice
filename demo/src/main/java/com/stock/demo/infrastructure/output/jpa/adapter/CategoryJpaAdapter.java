package com.stock.demo.infrastructure.output.jpa.adapter;


import com.stock.demo.domain.model.Category;
import com.stock.demo.domain.spi.ICategoryPersistencePort;
import com.stock.demo.infrastructure.exception.CategoryPersistenceException;
import com.stock.demo.infrastructure.output.jpa.entity.CategoryEntity;
import com.stock.demo.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.stock.demo.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.stock.demo.util.CategoryConstants.CATEGORY_NOT_FOUND;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public Category createCategory(Category category) {
        CategoryEntity categoryEntity= categoryRepository.save(categoryEntityMapper.toEntity(category));
        //System.out.println("test for jpa adapter");
        //System.out.println(categoryEntity.getId());
        return categoryEntityMapper.toCategory(categoryEntity);
    }

    @Override
    public Category getCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(()->new CategoryPersistenceException(
                        CATEGORY_NOT_FOUND));
        return categoryEntityMapper.toCategory(categoryEntity);
    }

    @Override
    public Category getCategoryByName(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name)
                .orElseThrow(()-> new CategoryPersistenceException(
                        CATEGORY_NOT_FOUND));
        return categoryEntityMapper.toCategory(categoryEntity);
    }

    @Override
    public boolean categoryNameExists(String categoryName) {
        return categoryRepository.findByName(categoryName).isPresent();
    }

    @Override
    public boolean categoryIdExists(Long id) {
        return categoryRepository.findById(id).isPresent();
    }

    @Override
    public List<Category> getCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return categoryEntityMapper.toCategories(categoryEntities);
    }

    @Override
    public Category updateCategory(Category category) {
        CategoryEntity categoryEntity=categoryRepository.save(categoryEntityMapper.toEntity(category));
        return categoryEntityMapper.toCategory(categoryEntity);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}

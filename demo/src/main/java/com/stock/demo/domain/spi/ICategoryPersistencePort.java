package com.stock.demo.domain.spi;

import com.stock.demo.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {
    void createCategory(Category category);
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    boolean categoryNameExists(String categoryName);
    boolean categoryIdExists(Long id);
    List<Category> getCategories();
    void updateCategory(Category category);
    void deleteCategoryById(Long id);
}

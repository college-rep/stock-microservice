package com.stock.demo.domain.api;

import com.stock.demo.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {
    void validate(Category category);
    //this function will have a connection with the persistence
    Category createCategory(Category category);
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getCategories(Boolean ascendingOrder);
    Category updateCategory(Category category);
    void deleteCategoryById(Long id);
}

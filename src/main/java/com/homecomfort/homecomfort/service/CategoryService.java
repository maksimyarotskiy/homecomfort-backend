package com.homecomfort.homecomfort.service;

import com.homecomfort.homecomfort.entity.Category;
import com.homecomfort.homecomfort.exception.CategoryNotFoundException;
import com.homecomfort.homecomfort.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category createCategory(Category category) {
        if (category == null || category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category or its name cannot be null or empty");
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        if (category == null || category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category or its name cannot be null or empty");
        }
        Category categoryForUpdate = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryForUpdate.setName(category.getName());
        return categoryRepository.save(categoryForUpdate);
    }

    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }

}

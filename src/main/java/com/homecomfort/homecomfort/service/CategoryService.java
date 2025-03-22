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
        return categoryRepository.findById(id).orElse(null);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        Category categoryForUpdate = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryForUpdate.setName(category.getName());
        return categoryRepository.save(categoryForUpdate);
    }

    public void deleteCategory(Long id) {
        Category categoryForDelete = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.delete(categoryForDelete);
    }

}

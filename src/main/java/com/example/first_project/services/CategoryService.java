package com.example.first_project.services;

import com.example.first_project.models.Category;
import com.example.first_project.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        if (category.getCategoryName() == null || category.getCategoryName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name is required");
        }

        Category newCategory = new Category();
        newCategory.setCategoryName(category.getCategoryName());
        newCategory.setDescription(category.getDescription());
        return categoryRepository.save(newCategory);
    }
}

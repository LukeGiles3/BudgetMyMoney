package com.example.budgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    public static CategoryRepository categoryRepo;

    @Autowired
    public CategoryService(CategoryRepository repo) {
        CategoryService.categoryRepo = repo;
    }

    public static void createNewBudgetCategory(Category category) { categoryRepo.save(category); }

    public static List<Category> getAllCategories() { return categoryRepo.findAll(); }

    public static Optional<Category> getCategoryByID(Long id) {
        return categoryRepo.findById(id);
    }

    public static void editCategory(Long id, String name, float amount) {
        Optional<Category> categoryToUpdate = categoryRepo.findById(id);
        Category category = categoryToUpdate.get();
        category.setCategoryName(name);
        category.setCategoryAmount(amount);
        categoryRepo.save(category);
    }

    public static void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    public static void deleteAllCategories() { categoryRepo.deleteAll(); }
}

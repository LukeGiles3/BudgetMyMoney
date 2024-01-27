package com.example.budgets;

import com.example.budgets.entity.Category;
import com.example.budgets.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CategoryControllerTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void saveCategory() {
        Category category1 = new Category("NewCategory1", 100);
        categoryRepository.save(category1);
        Category category2 = new Category("NewCategory2", 200);
        categoryRepository.save(category2);
        Category category3 = new Category("NewCategory3", 300);
        categoryRepository.save(category3);

        Assertions.assertEquals(3, categoryRepository.findAll().size());
        categoryRepository.deleteAll();
    }

    @Test
    void readCategory() {
        Category category1 = new Category("NewCategory1", 100);
        category1.setCategoryID(1L);

        Assertions.assertEquals(1L, category1.getCategoryID());
        Assertions.assertEquals("NewCategory1", category1.getCategoryName());
        Assertions.assertEquals(100, category1.getCategoryAmount());
        categoryRepository.deleteAll();
    }

    @Test
    void deleteCategory() {
        Category category1 = new Category("NewCategory1", 100);
        categoryRepository.save(category1);
        Category category2 = new Category("NewCategory2", 200);
        categoryRepository.save(category2);
        Category category3 = new Category("NewCategory3", 300);
        categoryRepository.save(category3);

        categoryRepository.deleteById(3L);

        Assertions.assertEquals(2, categoryRepository.findAll().size());
        Assertions.assertTrue(categoryRepository.findById(3L).isEmpty());
        categoryRepository.deleteAll();
    }

    @Test
    void editCategory() {
        Category category1 = new Category("NewCategory1", 100);
        category1.setCategoryID(1L);
        categoryRepository.save(category1);

        Optional<Category> categoryToUpdate = categoryRepository.findById(category1.getCategoryID());

        if (categoryToUpdate.isPresent()) {
            Category category = categoryToUpdate.get();
            category.setCategoryName("Edit Category Name");
            category.setCategoryAmount(400);

            categoryRepository.save(category);

            Optional<Category> updatedCategory = categoryRepository.findById(category.getCategoryID());
            Assertions.assertTrue(updatedCategory.isPresent());
            Assertions.assertEquals("Edit Category Name", updatedCategory.get().getCategoryName());
            Assertions.assertEquals(400, updatedCategory.get().getCategoryAmount());
        } else {
            Assertions.fail("Category not found for ID: " + category1.getCategoryID());
        }
        categoryRepository.deleteAll();
    }
}











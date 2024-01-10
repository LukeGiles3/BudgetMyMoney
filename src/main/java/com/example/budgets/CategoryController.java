package com.example.budgets;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryRepository repo;
    CategoryController(CategoryRepository repo) {
        this.repo = repo;
    }

    @PostMapping(value = "/createNewCategory/save")
    public void createNewCategory(@RequestBody Category category) {
        CategoryService.createNewBudgetCategory(category);
    }

    @PostMapping(value = "/editCategory")
    public void editCategory(@RequestParam(name = "id") Long ID, @RequestParam(name = "categoryName") String name, @RequestParam(name = "categoryAmount") float amount) {
        CategoryService.editCategory(ID, name, amount);
    }

    @DeleteMapping(value = "/deleteCategory")
    public void deleteCategory(@RequestParam(name = "id") Long id) {
        CategoryService.deleteCategory(id);
    }

    @GetMapping(value = "/category")
    public Optional<Category> getCategoryByID(@RequestParam(name = "id") Long ID) {
        return CategoryService.getCategoryByID(ID);
    }

    @GetMapping(value = "/allCategories")
    public List<Category> getAllCategories() {
        return CategoryService.getAllCategories();
    }

    @DeleteMapping(value = "/deleteAllCategories")
    public void deleteAllCategories() { CategoryService.deleteAllCategories(); }
}

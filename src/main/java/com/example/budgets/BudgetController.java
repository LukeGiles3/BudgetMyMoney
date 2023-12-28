package com.example.budgets;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BudgetController {
    private final BudgetsRepository repo;

    BudgetController(BudgetsRepository repo) {
        this.repo = repo;
    }
    @GetMapping(value = "/test")
    public String getTest() {
        return "yup";
    }

    @GetMapping(value = "/budgets")
    public List<Budget> getBudgets() {
        return List.of();
    }

    @PostMapping
    public void createNewBudget(@RequestBody Budget budget) {
        BudgetService.createNewBudget(budget);
    }
}

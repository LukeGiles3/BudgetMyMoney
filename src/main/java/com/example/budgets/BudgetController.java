package com.example.budgets;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return BudgetService.getBudgets();
    }

    @GetMapping(value = "/budget")
    public Optional<Budget> getBudgetByID(@RequestParam(name = "id") Long ID) {
        return BudgetService.getBudgetByID(ID);
    }

    @PostMapping
    public void createNewBudget(@RequestBody Budget budget) {
        BudgetService.createNewBudget(budget);
    }
}

package com.example.budgets.controller;

import com.example.budgets.entity.Budget;
import com.example.budgets.service.BudgetService;
import com.example.budgets.repository.BudgetsRepository;
import com.example.budgets.service.IBudgetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BudgetController {
    private final IBudgetService svc;

    public BudgetController(
                     IBudgetService svc) {
        this.svc = svc;
    }

    @GetMapping(value = "/budgets")
    public List<Budget> getBudgets() {
        return svc.getBudgets();
    }

    @GetMapping(value = "/budget")
    public Optional<Budget> getBudgetByID(
            @RequestParam(name = "id") Long ID) {
        return svc.getBudgetByID(ID);
    }

    @PostMapping
    public void createNewBudget(@RequestBody Budget budget) {
        svc.createNewBudget(budget);
    }
}

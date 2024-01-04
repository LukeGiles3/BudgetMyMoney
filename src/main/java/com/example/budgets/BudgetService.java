package com.example.budgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    public static BudgetsRepository budgetRepo;

    @Autowired
    public BudgetService(BudgetsRepository repo) {
        BudgetService.budgetRepo = repo;
    }

    public static List<Budget> getBudgets() {
        return budgetRepo.findAll();
    }

    public static void createNewBudget(Budget budget) {
        budgetRepo.save(budget);
    }

    public static Optional<Budget> getBudgetByID(Long id) {
        return budgetRepo.findById(id);
    }
}

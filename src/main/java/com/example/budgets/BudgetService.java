package com.example.budgets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    public static BudgetsRepository repo;

    @Autowired
    public BudgetService(BudgetsRepository repo) {
        BudgetService.repo = repo;
    }

    public static List<Budget> getBudgets() {
        return repo.findAll();
    }

    public static void createNewBudget(Budget budget) {
        repo.save(budget);
    }
}

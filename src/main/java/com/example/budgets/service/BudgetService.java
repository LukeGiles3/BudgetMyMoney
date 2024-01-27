package com.example.budgets.service;

import com.example.budgets.entity.Budget;
import com.example.budgets.repository.BudgetsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService implements IBudgetService {
    public static BudgetsRepository budgetRepo;

    public BudgetService(BudgetsRepository repo) {
        budgetRepo = repo;
    }

    @Override
    public List<Budget> getBudgets() {
        return budgetRepo.findAll();
    }

    @Override

    public void createNewBudget(Budget budget) {
        budgetRepo.save(budget);
    }
    @Override
    public Optional<Budget> getBudgetByID(Long id) {
        return budgetRepo.findById(id);
    }
}

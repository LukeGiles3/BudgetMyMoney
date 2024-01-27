package com.example.budgets.service;

import com.example.budgets.entity.Budget;

import java.util.List;
import java.util.Optional;

public interface IBudgetService {
    List<Budget> getBudgets();

    void createNewBudget(Budget budget);

    Optional<Budget> getBudgetByID(Long id);
}

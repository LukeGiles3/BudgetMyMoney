package com.example.budgets.repository;

import com.example.budgets.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetsRepository extends JpaRepository<Budget, Long> {
}

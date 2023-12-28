package com.example.budgets;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetsRepository extends JpaRepository<Budget, Long> {
}

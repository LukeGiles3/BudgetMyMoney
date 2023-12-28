package com.example.budgets;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.Objects;

@Entity
class Budget {

    private @Id @GeneratedValue Long budgetID;
    private String budgetName;

    public Budget(String budgetName) {
        this.budgetName = budgetName;
    }

    public Budget() {

    }

    public Long getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(Long budgetID) {
        this.budgetID = budgetID;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Budget budget))
            return false;
        return Objects.equals(this.budgetID, budget.budgetID) && Objects.equals(this.budgetName, budget.budgetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.budgetID, this.budgetName);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.budgetID + ", name='" + this.budgetName + '\'' + '}';
    }
}

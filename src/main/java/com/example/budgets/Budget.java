package com.example.budgets;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
class Budget {

    private @Id @GeneratedValue Long budgetID;

    private String budgetName;

    @ElementCollection
    private List<String> budgetCategories;

    @ElementCollection
    private List<String> budgetTransactions;

    public Budget(String budgetName, List<String> budgetCategories, List<String> budgetTransactions) {
        this.budgetName = budgetName;
        this.budgetCategories = budgetCategories;
        this.budgetTransactions = budgetTransactions;
    }

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

    public List<String> getBudgetCategories() {
        return budgetCategories;
    }

    public void setBudgetCategories(List<String> budgetCategories) {
        this.budgetCategories = budgetCategories;
    }

    public List<String> getBudgetTransactions() {
        return budgetTransactions;
    }

    public void setBudgetTransactions(List<String> budgetTransactions) {
        this.budgetTransactions = budgetTransactions;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.budgetID + ", name='" + this.budgetName + '\'' + '}';
    }
}

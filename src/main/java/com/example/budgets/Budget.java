package com.example.budgets;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.Objects;

@Entity
class Budget {

    private @Id @GeneratedValue Long budgetID;

    private String budgetName;

    @ElementCollection
    private ArrayList<String> budgetCategories;

    @ElementCollection
    private ArrayList<String> budgetTransactions;

    public Budget(String budgetName, ArrayList<String> budgetCategories, ArrayList<String> budgetTransactions) {
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

    public ArrayList<String> getBudgetCategories() {
        return budgetCategories;
    }

    public void setBudgetCategories(ArrayList<String> budgetCategories) {
        this.budgetCategories = budgetCategories;
    }

    public ArrayList<String> getBudgetTransactions() {
        return budgetTransactions;
    }

    public void setBudgetTransactions(ArrayList<String> budgetTransactions) {
        this.budgetTransactions = budgetTransactions;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.budgetID + ", name='" + this.budgetName + '\'' + '}';
    }
}

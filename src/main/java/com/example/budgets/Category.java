package com.example.budgets;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Category {
    private @Id @GeneratedValue Long categoryID;
    private String categoryName;
    private float categoryAmount;
    private Long budgetID;

    public Category(String categoryName, float categoryAmount, Long budgetID) {
        this.categoryName = categoryName;
        this.categoryAmount = categoryAmount;
        this.budgetID = budgetID;
    }

    public Category() {

    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public float getCategoryAmount() {
        return categoryAmount;
    }

    public void setCategoryAmount(float categoryAmount) {
        this.categoryAmount = categoryAmount;
    }

    public Long getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(Long budgetID) {
        this.budgetID = budgetID;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", categoryAmount=" + categoryAmount +
                '}';
    }
}

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
    @ElementCollection
    private List<Long> transactionIDs;

    public Category(String categoryName, float categoryAmount, List<Long> transactionIDs) {
        this.categoryName = categoryName;
        this.categoryAmount = categoryAmount;
        this.transactionIDs = transactionIDs;
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

    public List<Long> getTransactionIDs() {
        return transactionIDs;
    }

    public void setTransactionIDs(List<Long> transactionIDs) {
        this.transactionIDs = transactionIDs;
    }
}

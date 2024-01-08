package com.example.budgets;

import jakarta.persistence.*;

@Entity
class Transaction {

    private @Id @GeneratedValue Long transactionID;

    private String transactionName;

    private float transactionAmount;

    private Long categoryID;

    public Transaction(String transactionName, float transactionAmount, Long categoryID) {
        this.transactionName = transactionName;
        this.transactionAmount = transactionAmount;
        this.categoryID = categoryID;
    }

    public Transaction() {

    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }
}

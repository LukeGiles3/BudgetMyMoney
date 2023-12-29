package com.example.budgets;

import jakarta.persistence.*;

@Entity
class Transaction {

    private @Id @GeneratedValue Long transactionID;

    private String transactionName;

    private float transactionAmount;

    private String transactionCategory; //category name

    public Transaction(String transactionName, float transactionAmount, String transactionCategory) {
        this.transactionName = transactionName;
        this.transactionAmount = transactionAmount;
        this.transactionCategory = transactionCategory;
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

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", transactionName='" + transactionName + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionCategory='" + transactionCategory + '\'' +
                '}';
    }
}

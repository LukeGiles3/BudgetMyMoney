package com.example.budgets.entities;

public class Transaction {
    private int transactionID;
    private String transactionName;
    private double transactionAmount;
    private String budgetCategory;

    public Transaction(int transactionID, String transactionName, double transactionAmount, String budgetCategory) {
        this.transactionID = transactionID;
        this.transactionName = transactionName;
        this.transactionAmount = transactionAmount;
        this.budgetCategory = budgetCategory;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(String budgetCategory) {
        this.budgetCategory = budgetCategory;
    }
}

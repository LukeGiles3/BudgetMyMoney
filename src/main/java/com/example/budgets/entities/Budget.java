package com.example.budgets.entities;

import java.util.ArrayList;

public class Budget {
    private int budgetID;
    private String budgetName;
    private ArrayList<String> budgetItems;

    public Budget(int budgetID, String budgetName, ArrayList<String> budgetItems) {
        this.budgetID = budgetID;
        this.budgetName = budgetName;
        this.budgetItems = budgetItems;
    }

    public int getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(int budgetID) {
        this.budgetID = budgetID;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public ArrayList<String> getBudgetItems() {
        return budgetItems;
    }

    public void setBudgetItems(ArrayList<String> budgetItems) {
        this.budgetItems = budgetItems;
    }
}

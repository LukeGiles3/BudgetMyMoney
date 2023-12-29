package com.example.budgets;

public class Category {
    private Long categoryID;

    private String categoryName;

    private float categoryAmount;

    public Category(Long categoryID, String categoryName, float categoryAmount) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryAmount = categoryAmount;
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

    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", categoryAmount=" + categoryAmount +
                '}';
    }
}

package com.foodorder.model;

/**
 * Represents an item in a restaurant's menu with its price.
 * Updated for Java 8.
 */
public class MenuItem {
    private final String itemId;
    private double price;

    public MenuItem(String itemId, double price) {
        this.itemId = itemId;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
               "itemId='" + itemId + '\'' +
               ", price=" + String.format("%.2f", price) +
               '}';
    }
} 
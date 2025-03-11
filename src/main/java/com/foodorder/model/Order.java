package com.foodorder.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a customer order with items and associated restaurant.
 * Updated for Java 8.
 */
public class Order {
    private final String orderId;
    private final List<String> items;
    private final String restaurantId;
    private final double totalAmount;
    private boolean dispatched;

    public Order(String orderId, List<String> items, String restaurantId, double totalAmount) {
        this.orderId = orderId;
        this.items = new ArrayList<>(items);
        this.restaurantId = restaurantId;
        this.totalAmount = totalAmount;
        this.dispatched = false;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isDispatched() {
        return dispatched;
    }

    public void markAsDispatched() {
        this.dispatched = true;
    }

    @Override
    public String toString() {
        return "Order{" +
               "orderId='" + orderId + '\'' +
               ", items=" + items +
               ", restaurantId='" + restaurantId + '\'' +
               ", totalAmount=" + String.format("%.2f", totalAmount) +
               ", dispatched=" + dispatched +
               '}';
    }
} 
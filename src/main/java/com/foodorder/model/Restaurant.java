package com.foodorder.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * Represents a restaurant with its menu, processing capacity, and current orders.
 * Updated for Java 8.
 */
public class Restaurant {
    private final String restaurantId;
    private final Map<String, MenuItem> menu;
    private final int maxProcessingCapacity;
    private final Set<String> currentOrders;
    private final Map<String, Integer> itemsServed;

    public Restaurant(String restaurantId, List<MenuItem> menuItems, int maxProcessingCapacity) {
        this.restaurantId = restaurantId;
        this.menu = new HashMap<>();
        for (MenuItem item : menuItems) {
            this.menu.put(item.getItemId(), item);
        }
        this.maxProcessingCapacity = maxProcessingCapacity;
        this.currentOrders = new HashSet<>();
        this.itemsServed = new HashMap<>();
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public Map<String, MenuItem> getMenu() {
        return new HashMap<>(menu);
    }

    public int getMaxProcessingCapacity() {
        return maxProcessingCapacity;
    }

    public int getCurrentOrderCount() {
        return currentOrders.size();
    }

    public boolean hasCapacity() {
        return currentOrders.size() < maxProcessingCapacity;
    }

    public boolean hasItem(String itemId) {
        return menu.containsKey(itemId);
    }

    public MenuItem getMenuItem(String itemId) {
        return menu.get(itemId);
    }

    public void updateItemPrice(String itemId, double newPrice) {
        if (menu.containsKey(itemId)) {
            menu.get(itemId).setPrice(newPrice);
        } else {
            throw new IllegalArgumentException("Item " + itemId + " not found in restaurant " + restaurantId);
        }
    }

    public boolean addOrder(String orderId) {
        if (hasCapacity()) {
            currentOrders.add(orderId);
            return true;
        }
        return false;
    }

    public void dispatchOrder(String orderId, List<String> items) {
        if (currentOrders.contains(orderId)) {
            currentOrders.remove(orderId);
            
            // Update items served count
            for (String itemId : items) {
                Integer count = itemsServed.getOrDefault(itemId, 0);
                itemsServed.put(itemId, count + 1);
            }
        } else {
            throw new IllegalArgumentException("Order " + orderId + " not found in restaurant " + restaurantId);
        }
    }

    public Map<String, Integer> getItemsServed() {
        return new HashMap<>(itemsServed);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
               "restaurantId='" + restaurantId + '\'' +
               ", menu=" + menu +
               ", maxProcessingCapacity=" + maxProcessingCapacity +
               ", currentOrders=" + currentOrders.size() +
               '}';
    }
} 
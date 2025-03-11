package com.foodorder.strategy;

import com.foodorder.model.MenuItem;
import com.foodorder.model.Restaurant;
import java.util.List;

/**
 * Strategy that selects the restaurant with the lowest total price for the order.
 * Updated for Java 8.
 */
public class LowestPriceStrategy implements RestaurantSelectionStrategy {
    
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, List<String> items) {
        Restaurant selectedRestaurant = null;
        double lowestPrice = Double.MAX_VALUE;
        
        // Calculate total price for each restaurant
        for (Restaurant restaurant : restaurants) {
            // Check if restaurant has all items and capacity
            if (!hasAllItems(restaurant, items) || !restaurant.hasCapacity()) {
                continue;
            }
            
            // Calculate total price
            double totalPrice = calculateTotalPrice(restaurant, items);
            
            // Update selected restaurant if this one has a lower price
            if (totalPrice < lowestPrice) {
                lowestPrice = totalPrice;
                selectedRestaurant = restaurant;
            }
        }
        
        return selectedRestaurant;
    }
    
    /**
     * Check if a restaurant has all the items in the order.
     */
    private boolean hasAllItems(Restaurant restaurant, List<String> items) {
        for (String itemId : items) {
            if (!restaurant.hasItem(itemId)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Calculate the total price of the order at a restaurant.
     */
    private double calculateTotalPrice(Restaurant restaurant, List<String> items) {
        double total = 0.0;
        for (String itemId : items) {
            total += restaurant.getMenuItem(itemId).getPrice();
        }
        return total;
    }
} 
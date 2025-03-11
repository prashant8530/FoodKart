package com.foodorder.strategy;

import com.foodorder.model.Restaurant;
import java.util.List;

/**
 * Strategy that selects the restaurant with the highest remaining processing capacity.
 * Updated for Java 8.
 */
public class HighestCapacityStrategy implements RestaurantSelectionStrategy {
    
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, List<String> items) {
        Restaurant selectedRestaurant = null;
        int highestRemainingCapacity = -1;
        
        for (Restaurant restaurant : restaurants) {
            // Check if restaurant has all items and capacity
            if (!hasAllItems(restaurant, items) || !restaurant.hasCapacity()) {
                continue;
            }
            
            // Calculate remaining capacity
            int remainingCapacity = restaurant.getMaxProcessingCapacity() - restaurant.getCurrentOrderCount();
            
            // Update selected restaurant if this one has higher remaining capacity
            if (remainingCapacity > highestRemainingCapacity) {
                highestRemainingCapacity = remainingCapacity;
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
} 
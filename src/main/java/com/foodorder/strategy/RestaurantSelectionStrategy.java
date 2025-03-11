package com.foodorder.strategy;

import com.foodorder.model.Restaurant;
import java.util.List;

/**
 * Strategy interface for selecting a restaurant for an order.
 * Updated for Java 17.
 */
public interface RestaurantSelectionStrategy {
    /**
     * Selects a restaurant from the given list based on the strategy implementation.
     * 
     * @param restaurants List of available restaurants
     * @param items List of items in the order
     * @return Selected restaurant or null if no restaurant can fulfill the order
     */
    Restaurant selectRestaurant(List<Restaurant> restaurants, List<String> items);
} 
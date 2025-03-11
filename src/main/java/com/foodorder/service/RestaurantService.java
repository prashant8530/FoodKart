package com.foodorder.service;

import com.foodorder.model.MenuItem;
import com.foodorder.model.Restaurant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for managing restaurants in the system.
 * Updated for Java 8.
 */
public class RestaurantService {
    private final Map<String, Restaurant> restaurants;

    public RestaurantService() {
        this.restaurants = new HashMap<>();
    }

    /**
     * Onboard a new restaurant with its menu and processing capacity.
     * 
     * @param restaurantId Unique identifier for the restaurant
     * @param menuItems List of menu items
     * @param maxProcessingCapacity Maximum number of orders the restaurant can process at once
     * @return The newly created restaurant
     */
    public Restaurant onboardRestaurant(String restaurantId, List<MenuItem> menuItems, int maxProcessingCapacity) {
        if (restaurants.containsKey(restaurantId)) {
            throw new IllegalArgumentException("Restaurant with ID " + restaurantId + " already exists");
        }
        
        Restaurant restaurant = new Restaurant(restaurantId, menuItems, maxProcessingCapacity);
        restaurants.put(restaurantId, restaurant);
        return restaurant;
    }

    /**
     * Update the price of a menu item in a restaurant.
     * 
     * @param restaurantId ID of the restaurant
     * @param itemId ID of the menu item
     * @param newPrice New price for the item
     * @return The updated restaurant
     */
    public Restaurant updateItemPrice(String restaurantId, String itemId, double newPrice) {
        Restaurant restaurant = getRestaurant(restaurantId);
        restaurant.updateItemPrice(itemId, newPrice);
        return restaurant;
    }

    /**
     * Get a restaurant by its ID.
     * 
     * @param restaurantId ID of the restaurant
     * @return The restaurant or null if not found
     */
    public Restaurant getRestaurant(String restaurantId) {
        if (!restaurants.containsKey(restaurantId)) {
            throw new IllegalArgumentException("Restaurant with ID " + restaurantId + " not found");
        }
        return restaurants.get(restaurantId);
    }

    /**
     * Get all restaurants in the system.
     * 
     * @return List of all restaurants
     */
    public List<Restaurant> getAllRestaurants() {
        return new ArrayList<>(restaurants.values());
    }

    /**
     * List all items served by a restaurant with their prices.
     * 
     * @param restaurantId ID of the restaurant
     * @return Map of item IDs to prices
     */
    public Map<String, Double> listItems(String restaurantId) {
        Restaurant restaurant = getRestaurant(restaurantId);
        Map<String, MenuItem> menu = restaurant.getMenu();
        
        Map<String, Double> result = new HashMap<>();
        for (Map.Entry<String, MenuItem> entry : menu.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getPrice());
        }
        
        return result;
    }
    
    /**
     * Get items served by a restaurant.
     * 
     * @param restaurantId ID of the restaurant
     * @return Map of item IDs to count of times served
     */
    public Map<String, Integer> getItemsServed(String restaurantId) {
        Restaurant restaurant = getRestaurant(restaurantId);
        return restaurant.getItemsServed();
    }
} 
package com.foodorder.command;

import com.foodorder.model.Restaurant;
import com.foodorder.service.RestaurantService;

/**
 * Command to update the price of an item in a restaurant's menu.
 */
public class UpdatePriceCommand implements Command {
    private final RestaurantService restaurantService;
    private final String restaurantId;
    private final String itemId;
    private final double newPrice;
    private final long timestamp;

    public UpdatePriceCommand(RestaurantService restaurantService, String restaurantId, 
                             String itemId, double newPrice, long timestamp) {
        this.restaurantService = restaurantService;
        this.restaurantId = restaurantId;
        this.itemId = itemId;
        this.newPrice = newPrice;
        this.timestamp = timestamp;
    }

    @Override
    public String execute() {
        try {
            Restaurant restaurant = restaurantService.updateItemPrice(restaurantId, itemId, newPrice);
            return "Price updated successfully. Updated menu: " + restaurant.getMenu();
        } catch (Exception e) {
            return "Failed to update price: " + e.getMessage();
        }
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
} 
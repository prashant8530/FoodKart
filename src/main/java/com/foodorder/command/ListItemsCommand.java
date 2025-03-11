package com.foodorder.command;

import com.foodorder.service.RestaurantService;
import java.util.Map;

/**
 * Command to list items served by a restaurant.
 */
public class ListItemsCommand implements Command {
    private final RestaurantService restaurantService;
    private final String restaurantId;
    private final long timestamp;

    public ListItemsCommand(RestaurantService restaurantService, String restaurantId, long timestamp) {
        this.restaurantService = restaurantService;
        this.restaurantId = restaurantId;
        this.timestamp = timestamp;
    }

    @Override
    public String execute() {
        try {
            Map<String, Integer> itemsServed = restaurantService.getItemsServed(restaurantId);
            if (itemsServed.isEmpty()) {
                return "Restaurant " + restaurantId + " has not served any items yet.";
            }
            
            StringBuilder result = new StringBuilder("Items served by restaurant " + restaurantId + ":\n");
            for (Map.Entry<String, Integer> entry : itemsServed.entrySet()) {
                result.append(entry.getKey()).append(": ").append(entry.getValue()).append(" times\n");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "Failed to list items: " + e.getMessage();
        }
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
} 
package com.foodorder.command;

import com.foodorder.model.MenuItem;
import com.foodorder.model.Restaurant;
import com.foodorder.service.RestaurantService;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command to onboard a new restaurant.
 */
public class OnboardRestaurantCommand implements Command {
    private final RestaurantService restaurantService;
    private final String restaurantId;
    private final List<MenuItem> menuItems;
    private final int maxProcessingCapacity;
    private final long timestamp;

    public OnboardRestaurantCommand(RestaurantService restaurantService, String restaurantId, 
                                   String menuItemsStr, int maxProcessingCapacity, long timestamp) {
        this.restaurantService = restaurantService;
        this.restaurantId = restaurantId;
        this.menuItems = parseMenuItems(menuItemsStr);
        this.maxProcessingCapacity = maxProcessingCapacity;
        this.timestamp = timestamp;
    }

    private List<MenuItem> parseMenuItems(String menuItemsStr) {
        List<MenuItem> items = new ArrayList<>();
        
        // Clean up the input string
        menuItemsStr = menuItemsStr.trim();
        if (menuItemsStr.startsWith("[")) {
            menuItemsStr = menuItemsStr.substring(1);
        }
        if (menuItemsStr.endsWith("]")) {
            menuItemsStr = menuItemsStr.substring(0, menuItemsStr.length() - 1);
        }
        
        // Parse menu items from string like "[(item1, 50),(item2, 40),(item3, 40)]"
        Pattern pattern = Pattern.compile("\\(([^,]+),\\s*(\\d+)\\)");
        Matcher matcher = pattern.matcher(menuItemsStr);
        
        while (matcher.find()) {
            String itemId = matcher.group(1).trim();
            double price = Double.parseDouble(matcher.group(2).trim());
            items.add(new MenuItem(itemId, price));
        }
        
        return items;
    }

    @Override
    public String execute() {
        try {
            Restaurant restaurant = restaurantService.onboardRestaurant(restaurantId, menuItems, maxProcessingCapacity);
            return "Restaurant onboarded successfully: " + restaurant;
        } catch (Exception e) {
            return "Failed to onboard restaurant: " + e.getMessage();
        }
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
} 
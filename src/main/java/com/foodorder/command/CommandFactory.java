package com.foodorder.command;

import com.foodorder.service.OrderService;
import com.foodorder.service.RestaurantService;

/**
 * Factory for creating command objects from input strings.
 * Updated for Java 8.
 */
public class CommandFactory {
    private final RestaurantService restaurantService;
    private final OrderService orderService;

    public CommandFactory(RestaurantService restaurantService, OrderService orderService) {
        this.restaurantService = restaurantService;
        this.orderService = orderService;
    }

    /**
     * Create a command from an input string.
     * 
     * @param input Input string in the format "timestamp, command-type, arg1, arg2, ..."
     * @return Command object
     */
    public Command createCommand(String input) {
        String[] parts = input.split(",", 3);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid command format: " + input);
        }
        
        long timestamp = Long.parseLong(parts[0].trim());
        String commandType = parts[1].trim();
        String args = parts.length > 2 ? parts[2] : "";
        
        switch (commandType) {
            case "onboard-restaurant":
                return createOnboardRestaurantCommand(input, timestamp);
            case "update-price":
                return createUpdatePriceCommand(args, timestamp);
            case "place-order":
                return createPlaceOrderCommand(args, timestamp);
            case "dispatch-order":
                return createDispatchOrderCommand(args, timestamp);
            case "list-items":
                return createListItemsCommand(args, timestamp);
            case "list-dispatched-orders":
                return new ListDispatchedOrdersCommand(orderService, timestamp);
            case "change-strategy":
                return createChangeStrategyCommand(args, timestamp);
            default:
                throw new IllegalArgumentException("Unknown command type: " + commandType);
        }
    }

    private OnboardRestaurantCommand createOnboardRestaurantCommand(String input, long timestamp) {
        // Special parsing for onboard-restaurant command due to complex format
        // Format: timestamp, onboard-restaurant, restaurantId, [(item1, price1),(item2, price2),...], maxCapacity
        
        int firstCommaIndex = input.indexOf(',');
        int secondCommaIndex = input.indexOf(',', firstCommaIndex + 1);
        int thirdCommaIndex = input.indexOf(',', secondCommaIndex + 1);
        
        // Find the last comma before the closing bracket
        int closingBracketIndex = input.lastIndexOf(']');
        int fourthCommaIndex = input.indexOf(',', closingBracketIndex);
        
        if (firstCommaIndex == -1 || secondCommaIndex == -1 || thirdCommaIndex == -1 || 
            closingBracketIndex == -1 || fourthCommaIndex == -1) {
            throw new IllegalArgumentException("Invalid onboard-restaurant command format: " + input);
        }
        
        String restaurantId = input.substring(secondCommaIndex + 1, thirdCommaIndex).trim();
        String menuItemsStr = input.substring(thirdCommaIndex + 1, fourthCommaIndex).trim();
        String capacityStr = input.substring(fourthCommaIndex + 1).trim();
        
        int maxProcessingCapacity = Integer.parseInt(capacityStr);
        
        return new OnboardRestaurantCommand(restaurantService, restaurantId, menuItemsStr, maxProcessingCapacity, timestamp);
    }

    private UpdatePriceCommand createUpdatePriceCommand(String args, long timestamp) {
        String[] parts = args.split(",", 3);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid update-price command format");
        }
        
        String restaurantId = parts[0].trim();
        String itemId = parts[1].trim();
        double newPrice = Double.parseDouble(parts[2].trim());
        
        return new UpdatePriceCommand(restaurantService, restaurantId, itemId, newPrice, timestamp);
    }

    private PlaceOrderCommand createPlaceOrderCommand(String args, long timestamp) {
        String[] parts = args.split(",", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid place-order command format");
        }
        
        String orderId = parts[0].trim();
        String itemsStr = parts[1].trim();
        
        return new PlaceOrderCommand(orderService, orderId, itemsStr, timestamp);
    }

    private DispatchOrderCommand createDispatchOrderCommand(String args, long timestamp) {
        String orderId = args.trim();
        return new DispatchOrderCommand(orderService, orderId, timestamp);
    }

    private ListItemsCommand createListItemsCommand(String args, long timestamp) {
        String restaurantId = args.trim();
        return new ListItemsCommand(restaurantService, restaurantId, timestamp);
    }
    
    private ChangeStrategyCommand createChangeStrategyCommand(String args, long timestamp) {
        String strategyName = args.trim();
        return new ChangeStrategyCommand(orderService, strategyName, timestamp);
    }
} 
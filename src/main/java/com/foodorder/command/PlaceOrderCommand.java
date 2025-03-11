package com.foodorder.command;

import com.foodorder.model.Order;
import com.foodorder.service.OrderService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Command to place a new order.
 */
public class PlaceOrderCommand implements Command {
    private final OrderService orderService;
    private final String orderId;
    private final List<String> items;
    private final long timestamp;

    public PlaceOrderCommand(OrderService orderService, String orderId, List<String> items, long timestamp) {
        this.orderService = orderService;
        this.orderId = orderId;
        this.items = new ArrayList<>(items);
        this.timestamp = timestamp;
    }

    public PlaceOrderCommand(OrderService orderService, String orderId, String itemsStr, long timestamp) {
        this.orderService = orderService;
        this.orderId = orderId;
        this.items = parseItems(itemsStr);
        this.timestamp = timestamp;
    }

    private List<String> parseItems(String itemsStr) {
        // Parse items from string like "item1, item2, item3"
        String[] itemArray = itemsStr.split(",");
        List<String> itemList = new ArrayList<>();
        
        for (String item : itemArray) {
            itemList.add(item.trim());
        }
        
        return itemList;
    }

    @Override
    public String execute() {
        try {
            Order order = orderService.placeOrder(orderId, items);
            if (order == null) {
                return "Failed to place order: No restaurant can fulfill the order";
            }
            return "Order placed successfully: " + order + "\nTotal amount: " + order.getTotalAmount() + 
                   ", Restaurant: " + order.getRestaurantId();
        } catch (Exception e) {
            return "Failed to place order: " + e.getMessage();
        }
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
} 
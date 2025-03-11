package com.foodorder.command;

import com.foodorder.model.Order;
import com.foodorder.service.OrderService;
import java.util.List;

/**
 * Command to list all dispatched orders.
 */
public class ListDispatchedOrdersCommand implements Command {
    private final OrderService orderService;
    private final long timestamp;

    public ListDispatchedOrdersCommand(OrderService orderService, long timestamp) {
        this.orderService = orderService;
        this.timestamp = timestamp;
    }

    @Override
    public String execute() {
        try {
            List<Order> dispatchedOrders = orderService.getDispatchedOrders();
            if (dispatchedOrders.isEmpty()) {
                return "No orders have been dispatched yet.";
            }
            
            StringBuilder result = new StringBuilder("Dispatched orders:\n");
            for (Order order : dispatchedOrders) {
                result.append(order).append("\n");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "Failed to list dispatched orders: " + e.getMessage();
        }
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
} 
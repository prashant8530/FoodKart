package com.foodorder.command;

import com.foodorder.model.Order;
import com.foodorder.service.OrderService;

/**
 * Command to dispatch an order.
 */
public class DispatchOrderCommand implements Command {
    private final OrderService orderService;
    private final String orderId;
    private final long timestamp;

    public DispatchOrderCommand(OrderService orderService, String orderId, long timestamp) {
        this.orderService = orderService;
        this.orderId = orderId;
        this.timestamp = timestamp;
    }

    @Override
    public String execute() {
        try {
            Order order = orderService.dispatchOrder(orderId);
            return "Order dispatched successfully: " + order;
        } catch (Exception e) {
            return "Failed to dispatch order: " + e.getMessage();
        }
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
} 
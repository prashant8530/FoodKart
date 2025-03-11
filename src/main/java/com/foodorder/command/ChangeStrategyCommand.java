package com.foodorder.command;

import com.foodorder.service.OrderService;
import com.foodorder.strategy.RestaurantSelectionStrategy;
import com.foodorder.strategy.StrategyFactory;

/**
 * Command to change the restaurant selection strategy at runtime.
 */
public class ChangeStrategyCommand implements Command {
    private final OrderService orderService;
    private final String strategyName;
    private final long timestamp;

    public ChangeStrategyCommand(OrderService orderService, String strategyName, long timestamp) {
        this.orderService = orderService;
        this.strategyName = strategyName;
        this.timestamp = timestamp;
    }

    @Override
    public String execute() {
        try {
            RestaurantSelectionStrategy strategy = StrategyFactory.createStrategy(strategyName);
            orderService.setSelectionStrategy(strategy);
            return "Restaurant selection strategy changed to: " + strategyName;
        } catch (Exception e) {
            return "Failed to change strategy: " + e.getMessage();
        }
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }
} 
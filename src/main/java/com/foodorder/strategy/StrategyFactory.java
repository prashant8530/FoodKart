package com.foodorder.strategy;

/**
 * Factory for creating restaurant selection strategy objects.
 * Updated for Java 8.
 */
public class StrategyFactory {
    
    /**
     * Create a strategy based on the strategy name.
     * 
     * @param strategyName Name of the strategy to create
     * @return The created strategy
     */
    public static RestaurantSelectionStrategy createStrategy(String strategyName) {
        String strategy = strategyName.toLowerCase();
        if ("lowest-price".equals(strategy)) {
            return new LowestPriceStrategy();
        } else if ("highest-capacity".equals(strategy)) {
            return new HighestCapacityStrategy();
        } else {
            throw new IllegalArgumentException("Unknown strategy: " + strategyName);
        }
    }
} 
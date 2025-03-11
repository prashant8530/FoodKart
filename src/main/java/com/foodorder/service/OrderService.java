package com.foodorder.service;

import com.foodorder.model.MenuItem;
import com.foodorder.model.Order;
import com.foodorder.model.Restaurant;
import com.foodorder.strategy.RestaurantSelectionStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for managing orders in the system.
 * Updated for Java 8.
 */
public class OrderService {
    private final Map<String, Order> orders;
    private final RestaurantService restaurantService;
    private RestaurantSelectionStrategy selectionStrategy;

    public OrderService(RestaurantService restaurantService, RestaurantSelectionStrategy selectionStrategy) {
        this.orders = new HashMap<>();
        this.restaurantService = restaurantService;
        this.selectionStrategy = selectionStrategy;
    }

    /**
     * Set the restaurant selection strategy.
     * 
     * @param selectionStrategy The strategy to use for selecting restaurants
     */
    public void setSelectionStrategy(RestaurantSelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    /**
     * Place a new order with the given items.
     * 
     * @param orderId Unique identifier for the order
     * @param items List of item IDs in the order
     * @return The newly created order or null if the order cannot be placed
     */
    public Order placeOrder(String orderId, List<String> items) {
        if (orders.containsKey(orderId)) {
            throw new IllegalArgumentException("Order with ID " + orderId + " already exists");
        }
        
        // Select a restaurant based on the strategy
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        Restaurant selectedRestaurant = selectionStrategy.selectRestaurant(restaurants, items);
        
        if (selectedRestaurant == null) {
            return null; // No restaurant can fulfill the order
        }
        
        // Calculate total price
        double totalAmount = 0;
        for (String itemId : items) {
            MenuItem menuItem = selectedRestaurant.getMenuItem(itemId);
            totalAmount += menuItem.getPrice();
        }
        
        // Create and store the order
        Order order = new Order(orderId, items, selectedRestaurant.getRestaurantId(), totalAmount);
        orders.put(orderId, order);
        
        // Add the order to the restaurant
        selectedRestaurant.addOrder(orderId);
        
        return order;
    }

    /**
     * Dispatch an order.
     * 
     * @param orderId ID of the order to dispatch
     * @return The dispatched order
     */
    public Order dispatchOrder(String orderId) {
        if (!orders.containsKey(orderId)) {
            throw new IllegalArgumentException("Order with ID " + orderId + " not found");
        }
        
        Order order = orders.get(orderId);
        if (order.isDispatched()) {
            throw new IllegalStateException("Order with ID " + orderId + " is already dispatched");
        }
        
        // Mark the order as dispatched
        order.markAsDispatched();
        
        // Update the restaurant
        Restaurant restaurant = restaurantService.getRestaurant(order.getRestaurantId());
        restaurant.dispatchOrder(orderId, order.getItems());
        
        return order;
    }

    /**
     * Get an order by its ID.
     * 
     * @param orderId ID of the order
     * @return The order or null if not found
     */
    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    /**
     * Get all orders in the system.
     * 
     * @return List of all orders
     */
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    /**
     * Get all dispatched orders.
     * 
     * @return List of dispatched orders
     */
    public List<Order> getDispatchedOrders() {
        return orders.values().stream()
                .filter(Order::isDispatched)
                .collect(Collectors.toList());
    }
} 
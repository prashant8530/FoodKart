package com.foodorder;

import java.util.Arrays;
import java.util.List;

/**
 * Demo class for the Food Order Management System.
 * Updated for Java 8.
 */
public class FoodOrderSystemDemo {
    
    /**
     * Main method to run the demo.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        FoodOrderSystem system = new FoodOrderSystem();
        
        System.out.println("Food Order Management System Demo");
        System.out.println("================================");
        
        // Process demo commands
        processCommands(system);
        
        // Execute all commands
        List<String> results = system.executeCommands();
        
        // Print results
        System.out.println("\nExecution Results:");
        System.out.println("================");
        
        for (String result : results) {
            System.out.println(result);
            System.out.println("-------------------");
        }
    }
    
    /**
     * Process demo commands.
     * 
     * @param system The food order system
     */
    private static void processCommands(FoodOrderSystem system) {
        List<String> commands = Arrays.asList(
            // Onboard restaurants
            "1, onboard-restaurant, restaurant1, [(item1, 50),(item2, 40),(item3, 40)], 4",
            "2, onboard-restaurant, restaurant2, [(item1, 45),(item2, 45),(item4, 60)], 3",
            "3, onboard-restaurant, restaurant3, [(item3, 35),(item4, 50),(item5, 70)], 2",
            
            // Update prices
            "4, update-price, restaurant1, item1, 60",
            "5, update-price, restaurant2, item1, 40",
            
            // Place orders
            "6, place-order, order1, item1, item2",
            "7, place-order, order2, item1, item2",
            "8, place-order, order3, item3, item4",
            
            // List items
            "9, list-items, restaurant1",
            "10, list-items, restaurant2",
            
            // Dispatch orders
            "11, dispatch-order, order1",
            "12, dispatch-order, order2",
            
            // List dispatched orders
            "13, list-dispatched-orders",
            
            // Change strategy
            "14, change-strategy, highest-capacity",
            
            // Place more orders with new strategy
            "15, place-order, order4, item1, item2",
            "16, place-order, order5, item3, item5"
        );
        
        for (String command : commands) {
            String result = system.processCommand(command);
            System.out.println(result);
        }
    }
} 
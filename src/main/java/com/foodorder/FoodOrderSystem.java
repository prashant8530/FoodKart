package com.foodorder;

import com.foodorder.command.Command;
import com.foodorder.command.CommandFactory;
import com.foodorder.command.CommandProcessor;
import com.foodorder.service.OrderService;
import com.foodorder.service.RestaurantService;
import com.foodorder.strategy.LowestPriceStrategy;
import com.foodorder.strategy.RestaurantSelectionStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Main class for the Food Order Management System.
 * Updated for Java 8.
 */
public class FoodOrderSystem {
    private final RestaurantService restaurantService;
    private final OrderService orderService;
    private final CommandFactory commandFactory;
    private final CommandProcessor commandProcessor;

    public FoodOrderSystem() {
        this.restaurantService = new RestaurantService();
        RestaurantSelectionStrategy strategy = new LowestPriceStrategy();
        this.orderService = new OrderService(restaurantService, strategy);
        this.commandFactory = new CommandFactory(restaurantService, orderService);
        this.commandProcessor = new CommandProcessor();
    }

    /**
     * Process a command input.
     * 
     * @param input Command input string
     * @return Result of the command execution
     */
    public String processCommand(String input) {
        try {
            Command command = commandFactory.createCommand(input);
            commandProcessor.addCommand(command);
            return "Command added to queue: " + input;
        } catch (Exception e) {
            return "Error processing command: " + e.getMessage();
        }
    }

    /**
     * Execute all queued commands in timestamp order.
     * 
     * @return List of execution results
     */
    public List<String> executeCommands() {
        return commandProcessor.executeAll();
    }

    /**
     * Main method to run the application.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        FoodOrderSystem system = new FoodOrderSystem();
        
        System.out.println("Food Order Management System");
        System.out.println("Enter commands (type 'exit' to quit, 'execute' to run queued commands):");
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while (!(input = reader.readLine()).equalsIgnoreCase("exit")) {
                if (input.equalsIgnoreCase("execute")) {
                    List<String> results = system.executeCommands();
                    System.out.println("\nExecution Results:");
                    for (String result : results) {
                        System.out.println(result);
                        System.out.println("-------------------");
                    }
                } else {
                    String result = system.processCommand(input);
                    System.out.println(result);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }
} 
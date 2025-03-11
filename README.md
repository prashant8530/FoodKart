# Food Order Management System

A Java-based system for managing restaurant orders, menus, and dispatching.

## Features

- Onboard restaurants with their menu and processing capacity
- Update menu item prices
- Place orders based on restaurant selection strategies
- Dispatch orders and track order status
- List items served by restaurants
- Process commands in timestamp order
- Change restaurant selection strategy at runtime

## System Architecture

The system is built using object-oriented design principles and follows several design patterns:

1. **Strategy Pattern**: For restaurant selection algorithms
2. **Command Pattern**: For processing user commands
3. **Factory Pattern**: For creating command objects and strategies

## Java 17 Features Used

This project has been migrated to JDK 17 and uses the following modern Java features:

1. **Text Blocks**: For more readable multi-line strings
2. **Switch Expressions**: For concise and safer switch statements
3. **Local Variable Type Inference (var)**: For cleaner code with implicit typing
4. **Enhanced Stream API**: For functional-style operations on collections
5. **Try-with-resources Enhancements**: For automatic resource management
6. **Collection Factory Methods**: For creating immutable collections

## Project Structure

- `model`: Domain models (Restaurant, MenuItem, Order)
- `service`: Business logic services (RestaurantService, OrderService)
- `strategy`: Restaurant selection strategies
- `command`: Command implementations and processing

## How to Run

### Requirements

- JDK 17 or higher

### Running the Interactive Console

```bash
javac -d out src/main/java/com/foodorder/*.java src/main/java/com/foodorder/*/*.java
java -cp out com.foodorder.FoodOrderSystem
```

### Running the Demo

```bash
javac -d out src/main/java/com/foodorder/*.java src/main/java/com/foodorder/*/*.java
java -cp out com.foodorder.FoodOrderSystemDemo
```

## Command Format

Commands should be entered in the following format:

```
<timestamp>, <command-type>, <arg1>, <arg2>, ...
```

### Available Commands

1. **Onboard Restaurant**
   ```
   <timestamp>, onboard-restaurant, <restaurantId>, [(item1, price1),(item2, price2),...], <maxProcessingCapacity>
   ```
   Example: `1, onboard-restaurant, restaurant1, [(item1, 50),(item2, 40),(item3, 40)], 4`

2. **Update Price**
   ```
   <timestamp>, update-price, <restaurantId>, <itemId>, <newPrice>
   ```
   Example: `2, update-price, restaurant1, item1, 60`

3. **Place Order**
   ```
   <timestamp>, place-order, <orderId>, <item1>, <item2>, ...
   ```
   Example: `3, place-order, order1, item1, item2, item3`

4. **Dispatch Order**
   ```
   <timestamp>, dispatch-order, <orderId>
   ```
   Example: `4, dispatch-order, order1`

5. **List Items**
   ```
   <timestamp>, list-items, <restaurantId>
   ```
   Example: `5, list-items, restaurant1`

6. **List Dispatched Orders**
   ```
   <timestamp>, list-dispatched-orders
   ```
   Example: `6, list-dispatched-orders`

7. **Change Strategy**
   ```
   <timestamp>, change-strategy, <strategyName>
   ```
   Example: `7, change-strategy, highest-capacity`

## Restaurant Selection Strategies

The system currently supports the following restaurant selection strategies:

1. **Lowest Price Strategy** (`lowest-price`): Selects the restaurant with the lowest total price for the order
2. **Highest Capacity Strategy** (`highest-capacity`): Selects the restaurant with the highest remaining processing capacity

## Extending the System

### Adding New Restaurant Selection Strategies

1. Implement the `RestaurantSelectionStrategy` interface
2. Override the `selectRestaurant` method with your custom logic
3. Add the strategy to the `StrategyFactory` class
4. Use the strategy by issuing a `change-strategy` command

### Adding New Commands

1. Create a new command class implementing the `Command` interface
2. Add the command type to the `CommandFactory` class
3. Implement the command parsing and execution logic 
#!/bin/bash

# Food Order Management System Build Script
# Requires JDK 8 or higher

# Check Java version
java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
echo "Using Java version: $java_version"

# Create output directory if it doesn't exist
mkdir -p out

# Compile the project
echo "Compiling the project..."
javac -d out src/main/java/com/foodorder/*.java src/main/java/com/foodorder/*/*.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    
    # Ask user which class to run
    echo ""
    echo "Which class would you like to run?"
    echo "1. FoodOrderSystem (Interactive Console)"
    echo "2. FoodOrderSystemDemo (Demo with predefined commands)"
    read -p "Enter your choice (1 or 2): " choice
    
    if [ "$choice" = "1" ]; then
        echo "Running FoodOrderSystem..."
        java -cp out com.foodorder.FoodOrderSystem
    elif [ "$choice" = "2" ]; then
        echo "Running FoodOrderSystemDemo..."
        java -cp out com.foodorder.FoodOrderSystemDemo
    else
        echo "Invalid choice. Exiting."
        exit 1
    fi
else
    echo "Compilation failed!"
    exit 1
fi 
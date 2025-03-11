package com.foodorder.command;

/**
 * Interface for command pattern implementation.
 * Updated for Java 17.
 */
public interface Command {
    /**
     * Execute the command.
     * 
     * @return Result of the command execution
     */
    String execute();
    
    /**
     * Get the timestamp of the command.
     * 
     * @return Timestamp as a long value
     */
    long getTimestamp();
} 
package com.foodorder.command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Processor for executing commands in timestamp order.
 * Updated for Java 8.
 */
public class CommandProcessor {
    private final PriorityQueue<Command> commandQueue;
    private final List<String> executionResults;

    public CommandProcessor() {
        this.commandQueue = new PriorityQueue<>(Comparator.comparingLong(Command::getTimestamp));
        this.executionResults = new ArrayList<>();
    }

    /**
     * Add a command to the queue.
     * 
     * @param command Command to add
     */
    public void addCommand(Command command) {
        commandQueue.add(command);
    }

    /**
     * Execute all commands in timestamp order.
     * 
     * @return List of execution results
     */
    public List<String> executeAll() {
        executionResults.clear();
        
        while (!commandQueue.isEmpty()) {
            Command command = commandQueue.poll();
            String result = command.execute();
            executionResults.add(result);
        }
        
        return new ArrayList<>(executionResults);
    }

    /**
     * Get the results of the last execution.
     * 
     * @return List of execution results
     */
    public List<String> getExecutionResults() {
        return new ArrayList<>(executionResults);
    }
} 
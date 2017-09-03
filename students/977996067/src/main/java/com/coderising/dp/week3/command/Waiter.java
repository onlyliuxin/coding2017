package com.coderising.dp.week3.command;

import java.util.ArrayDeque;
import java.util.Queue;

public class Waiter {

    private Queue<Command> commandQueue = new ArrayDeque<>();

    public synchronized void addOrder(Command command) {
        commandQueue.add(command);
    }

    public void sendOrders() {
        while (!commandQueue.isEmpty()) {
            commandQueue.poll().cookFood();
        }
    }
}

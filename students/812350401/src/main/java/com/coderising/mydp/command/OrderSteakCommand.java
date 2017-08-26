package com.coderising.mydp.command;

/**
 * Created by thomas_young on 11/8/2017.
 */
public class OrderSteakCommand implements Command {
    private Cook cook;
    public OrderSteakCommand(Cook cook) {
        this.cook = cook;
    }

    @Override
    public void execute() {
        cook.cookSteak();
    }
}

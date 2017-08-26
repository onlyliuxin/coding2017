package com.coderising.mydp.command;

/**
 * Created by thomas_young on 11/8/2017.
 */
public class OrderPorkCommand implements Command {
    private Cook cook;

    public OrderPorkCommand(Cook cook) {
        this.cook = cook;
    }
    @Override
    public void execute() {
        cook.cookPork();
    }
}

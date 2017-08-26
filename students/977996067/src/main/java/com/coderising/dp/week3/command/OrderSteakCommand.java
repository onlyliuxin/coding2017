package com.coderising.dp.week3.command;

public class OrderSteakCommand extends Command {

    public OrderSteakCommand(Cook cook) {
        super(cook);
    }

    @Override
    protected void cookFood() {
        getCook().cookSteak();
    }
}

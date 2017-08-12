package com.coderising.dp.week3.command;

public class OrderPorkCommand extends Command {

    public OrderPorkCommand(Cook cook) {
        super(cook);
    }

    @Override
    protected void cookFood() {
        getCook().cookPork();
    }

}

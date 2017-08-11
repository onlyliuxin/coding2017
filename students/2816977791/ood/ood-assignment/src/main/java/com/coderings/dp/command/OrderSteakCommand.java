package com.coderings.dp.command;

/**
 * @author nvarchar
 *         date 2017/8/11
 */
public class OrderSteakCommand implements Command{
    private Cook cook;

    public OrderSteakCommand(Cook cook) {
        this.cook = cook;
    }

    @Override
    public void execute() {
        cook.cookSteak();
    }
}

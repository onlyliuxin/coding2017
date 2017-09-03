package com.coderings.dp.command;

/**
 * @author nvarchar
 *         date 2017/8/11
 */
public class OrderPorkCommand implements Command{
    private Cook cook;

    public OrderPorkCommand(Cook cook) {
        this.cook = cook;
    }

    @Override
    public void execute() {
        cook.cookPork();
    }
}

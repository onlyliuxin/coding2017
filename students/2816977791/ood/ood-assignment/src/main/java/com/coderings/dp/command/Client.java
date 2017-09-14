package com.coderings.dp.command;

/**
 * @author nvarchar
 *         date 2017/8/11
 */
public class Client {
    public static void main(String[] args) {
        Cook cook = new Cook();

        Waiter waiter = new Waiter();

        Command command1 = new OrderPorkCommand(cook);
        Command command2 = new OrderSteakCommand(cook);
        Command command3 = new OrderSteakCommand(cook);

        waiter.addOrder(command1);
        waiter.addOrder(command2);
        waiter.addOrder(command3);

        waiter.sendOrders();
    }
}

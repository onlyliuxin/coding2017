package com.coderising.mydp.command;

/**
 * Created by thomas_young on 11/8/2017.
 * 命令模式
 */
public class Client {

    public static void main(String[] args) {
        Cook cook = new Cook();
        Waitor waitor = new Waitor();

        Command command1 = new OrderPorkCommand(cook);
        Command command2 = new OrderSteakCommand(cook);

        waitor.addOrder(command1);
        waitor.addOrder(command2);

        waitor.sendOrders();
    }
}

package com.coderising.dp.command;

public class CommandTest {
	public static void main(String[] args) {
		Cook cook = new Cook();
		Waiter waiter = new Waiter();
		Command command1=new OrderSteakCommand(cook);
		Command command2=new OrderPeaKCommand(cook);
		waiter.addOrder(command1);
		waiter.addOrder(command2);
		waiter.sendOrder();
	}
}

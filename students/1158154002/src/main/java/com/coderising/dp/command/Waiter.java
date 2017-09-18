package com.coderising.dp.command;

import java.util.ArrayList;
import java.util.List;

public class Waiter {
	private List<Command> commands = new ArrayList<>();

	public void addOrder(Command command) {
		commands.add(command);
	}
	
	public void sendOrders(){
		for (Command command : commands) {
			command.order();
		}
	}
	
	public static void main(String[] args) {
		Cook cook=new Cook();
		
		Waiter waiter=new Waiter();
		
		Command command1=new OrderSteakCommand(cook);
		Command command2=new OrderPorkCommand(cook);
		
		waiter.addOrder(command1);
		waiter.addOrder(command2);
		
		waiter.sendOrders();
	}

}

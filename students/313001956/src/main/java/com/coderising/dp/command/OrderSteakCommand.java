package com.coderising.dp.command;

public class OrderSteakCommand implements Command {

	private Cook cook;

	public OrderSteakCommand(Cook cook) {
		// TODO Auto-generated constructor stub
		this.cook = cook;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		cook.cookSteak();
	}
}

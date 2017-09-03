package com.coderising.dp.command;

public class OrderSteakCommand implements OrderCommand{
	private Cook cook;
	
	public OrderSteakCommand(Cook cook) {
		this.cook = cook;
	}
	
	@Override
	public void doOrder() {
		this.cook.cookSteak();
	}

}

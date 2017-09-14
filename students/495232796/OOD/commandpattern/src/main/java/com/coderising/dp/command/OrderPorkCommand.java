package com.coderising.dp.command;

public class OrderPorkCommand implements OrderCommand{
	private Cook cook;
	
	public OrderPorkCommand(Cook cook) {
		this.cook = cook;
	}
	
	@Override
	public void doOrder() {
		this.cook.cookPork();;
	}
}

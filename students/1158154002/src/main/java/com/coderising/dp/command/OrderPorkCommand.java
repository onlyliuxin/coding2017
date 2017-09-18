package com.coderising.dp.command;

public class OrderPorkCommand  implements Command{
	private Cook cook;
	public OrderPorkCommand(Cook cook) {
		this.cook=cook;
	}
	@Override
	public void order() {
		cook.cookPork();
	}

}

package com.coderising.dp.command;

public class OrderPeaKCommand implements Command {

	private Cook cook;

	public OrderPeaKCommand(Cook cook) {
		// TODO Auto-generated constructor stub
		this.cook = cook;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		cook.cookPeak();
	}
}

package com.coderising.dp.command;

import java.util.LinkedList;
import java.util.List;

public class Waiter {
	private List<OrderCommand> orderList = new LinkedList<>();
	
	public void addOrder(OrderCommand order) {
		this.orderList.add(order);
	}
	
	public void sendOrders() {
		for (int i = 0; i < orderList.size(); i++) {
			orderList.get(i).doOrder();
		}
	}
}

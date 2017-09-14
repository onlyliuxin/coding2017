package com.coderising.dp.command;

import java.util.ArrayList;
import java.util.List;

public class Waiter {

	List<Command> list = new ArrayList<>();

	public void addOrder(Command command) {
		list.add(command);
	}

	public void sendOrder() {
		for (Command command : list) {
			command.run();
		}
	}
}

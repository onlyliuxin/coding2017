package com.camile._2.litestruts.bean;

import java.util.ArrayList;
import java.util.List;

public class Structs {
	private List<Action> actions = new ArrayList<>();

	public void addAction(Action action) {
		actions.add(action);
	}

	public List<Action> getActions() {
		return actions;
	}
}

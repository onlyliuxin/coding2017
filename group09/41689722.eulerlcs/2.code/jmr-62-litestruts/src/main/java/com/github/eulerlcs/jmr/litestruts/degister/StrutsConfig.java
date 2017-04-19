package com.github.eulerlcs.jmr.litestruts.degister;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class StrutsConfig {
	@Getter
	private Map<String, StrutsAction> actionMap = new HashMap<>();

	public void addAction(StrutsAction action) {
		actionMap.put(action.getName(), action);
	}
}

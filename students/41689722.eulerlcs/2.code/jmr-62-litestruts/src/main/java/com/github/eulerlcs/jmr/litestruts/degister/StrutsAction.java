package com.github.eulerlcs.jmr.litestruts.degister;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class StrutsAction {
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String clazz;
	@Getter
	private Map<String, StrutsActionResult> results = new HashMap<>();

	public void addResult(StrutsActionResult result) {
		results.put(result.getName(), result);
	}
}

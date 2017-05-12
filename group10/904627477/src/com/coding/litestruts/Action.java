package com.coding.litestruts;

import java.util.HashMap;
import java.util.Map;

public class Action {
	
	public final static String DEFAULT_METHOD = "execute";
	
	private String name;
	private String clazz;
	private String method;
	private Map<String, Result> results = new HashMap<String, Result>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, Result> getResults() {
		return results;
	}
	public void setResults(Map<String, Result> results) {
		this.results = results;
	}
	public Action() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Action(String name, String clazz, String method) {
		super();
		this.name = name;
		this.clazz = clazz;
		this.method = method;
		this.results = new HashMap<String, Result>();
	}

	
}

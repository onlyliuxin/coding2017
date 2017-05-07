package com.litestruts.strutsBean;

import java.util.HashMap;

public class Action {
	private String name;
	private String clazz;
	private HashMap<String, String> parameters;
	
	public Action() {
		super();
	}
	
	public Action(String name, String clazz) {
		super();
		this.name = name;
		this.clazz = clazz;
	}

	public Action(String name, String clazz, HashMap<String, String> parameters) {
		super();
		this.name = name;
		this.clazz = clazz;
		this.parameters = parameters;
	}
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
	public HashMap<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	
	
}

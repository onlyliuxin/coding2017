package com.bruce.homework0305.demostruts;

import java.util.Map;

public class View {
	private String jsp;
	private Map parameters;
	
	public String getJsp() {
		return jsp;
	}
	public View setJsp(String jsp) {
		this.jsp = jsp;
		return this;
	}
	public Map getParameters() {
		return parameters;
	}
	public View setParameters(Map parameters) {
		this.parameters = parameters;
		return this;
	}
}

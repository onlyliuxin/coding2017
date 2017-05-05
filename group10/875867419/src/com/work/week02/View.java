package com.work.week02;


import java.util.Map;

public class View {
	private String jsp;
	private Map<String, Object> parameters;
	
	public String getJsp() {
		return jsp;
	}
	public View setJsp(String jsp) {
		this.jsp = jsp;
		return this;
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	public View setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
		return this;
	}
	@Override
	public String toString() {
		return "View [jsp=" + jsp + ", parameters=" + parameters + "]";
	}
	
}

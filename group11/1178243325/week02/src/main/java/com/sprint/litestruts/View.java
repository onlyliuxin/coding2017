package com.sprint.litestruts;

import java.util.Map;
public class View {
	private String jsp;
	private Map parameters;

	public void setJsp(String jsp) {
		this.jsp = jsp;
	}

	public String getJsp() {
		return jsp;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	public Map getParameters() {
		return parameters;
	}
 
}

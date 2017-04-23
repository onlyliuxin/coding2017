package com.coderising.litestruts;

import java.util.Map;

public class View {

	private String jsp;
	private Map parameter;

	public String getJsp() {
		return jsp;
	}

	public View setJsp(String jsp) {
		this.jsp = jsp;
		return this;
	}

	public Map getParameters() {
		return parameter;
	}

	public View setParameterMap(Map parameter) {
		this.parameter = parameter;
		return this;
	}

}

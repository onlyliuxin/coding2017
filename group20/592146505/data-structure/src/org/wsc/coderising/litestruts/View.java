package org.wsc.coderising.litestruts;

import java.util.Map;

public class View {
	private String jsp;
	private Map<String, String> parameters;

	public String getJsp() {
		return jsp;
	}

	public View setJsp(String jsp) {
		this.jsp = jsp;
		return this;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public View setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
		return this;
	}
}

package com.coderising.litestruts;

import java.util.Map;

public class View {

	private String jsp;
	private Map  parameters;
	public View setParameters(Map<String, Object> params) {
		this.parameters=params;
		return this;
	}

	public View setJsp(String jsp) {
		this.jsp=jsp;
		return this;	
	}
	public Map getParameters()
	{
		return parameters;
	}
	public String getJsp()
	{
		return jsp;
	}
   
}

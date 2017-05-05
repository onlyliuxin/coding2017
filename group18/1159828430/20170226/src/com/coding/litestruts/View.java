package com.coding.litestruts;

import java.util.Map;
/**
 * @author Scholar
 * @Time：2017年2月27日 下午8:49:10
 * @version 1.0
 */
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
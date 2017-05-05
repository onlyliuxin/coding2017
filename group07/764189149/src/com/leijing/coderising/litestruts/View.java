package com.leijing.coderising.litestruts;

import java.util.Map;
import java.util.Set;

public class View {
	private String jsp;
	private Map<String , Object> parameters;
	
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
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("jsp:").append(jsp);
		if(null == parameters){
			return sb.toString();
		}
		Set<String> keys =  parameters.keySet();
				
		for (String key : keys) {
			sb.append(",").append(key).append(":").append(parameters.get(key));
		}
		return sb.toString();
	}
}

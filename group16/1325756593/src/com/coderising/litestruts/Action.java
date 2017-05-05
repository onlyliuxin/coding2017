package com.coderising.litestruts;

import java.util.Map;

public class Action {
	private String actioName;
	private String className;
	private Map<String,String> resultAndViewMap;
	public String getActioName() {
		return actioName;
	}
	public void setActioName(String actioName) {
		this.actioName = actioName;
	}
	@Override
	public String toString() {
		return "Action [actioName=" + actioName + ", className=" + className + ", resultAndViewMap=" + resultAndViewMap
				+ "]";
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Map<String, String> getResultAndViewMap() {
		return resultAndViewMap;
	}
	public void setResultAndViewMap(Map<String, String> resultAndViewMap) {
		this.resultAndViewMap = resultAndViewMap;
	}
	
	
	

}

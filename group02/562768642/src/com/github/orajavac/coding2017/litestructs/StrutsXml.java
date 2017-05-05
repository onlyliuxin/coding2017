package com.github.orajavac.coding2017.litestructs;

import java.util.HashMap;
import java.util.Map;

public class StrutsXml {
	private String name;
	private String classz;
	private Map<String,String> result = new HashMap<String,String>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassz() {
		return classz;
	}
	public void setClassz(String classz) {
		this.classz = classz;
	}
	public Map<String, String> getResult() {
		return result;
	}
	public void setResult(Map<String, String> result) {
		this.result = result;
	}
}

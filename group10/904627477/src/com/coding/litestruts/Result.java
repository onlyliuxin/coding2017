package com.coding.litestruts;

public class Result {
	
	public final static String DEFAULT_NAME="success";
	
	private String name;
	private String type;
	private String jspPath;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJspPath() {
		return jspPath;
	}
	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}
	public Result(String name, String type, String jspPath) {
		super();
		this.name = name;
		this.type = type;
		this.jspPath = jspPath;
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}

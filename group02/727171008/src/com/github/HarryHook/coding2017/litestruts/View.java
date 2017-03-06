package com.github.HarryHook.coding2017.litestruts;

import java.util.Map;

public class View 
{
	private String jsp;
	private Map parameters;
	
	//对应action获取Jsp
	public String getJsp() 
	{
		return jsp;
	}
	public View setJsp(String jsp) 
	{
		this.jsp = jsp;
		return this;
	}
	
	//execute()获取对应参数
	public Map getParameters() 
	{
		return parameters;
	}
	public View setParameters(Map parameters) 
	{
		this.parameters = parameters;
		return this;
	}
}

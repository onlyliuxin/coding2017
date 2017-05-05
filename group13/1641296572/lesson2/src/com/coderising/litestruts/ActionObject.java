package com.coderising.litestruts;

import java.util.HashMap;
import java.util.Map;

public class ActionObject
{
	private String actionName;
	private String actionClass;
	private Map<String, String> resultMap = new HashMap<String, String>();

	public String getActionName()
	{
		return actionName;
	}

	public void setActionName(String actionName)
	{
		this.actionName = actionName;
	}

	public String getActionClass()
	{
		return actionClass;
	}

	public void setActionClass(String actionClass)
	{
		this.actionClass = actionClass;
	}

	public Map<String, String> getResultMap()
	{
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap)
	{
		this.resultMap = resultMap;
	}

	@Override
	public String toString()
	{
		return actionName + ", " + actionClass + ", " + resultMap.get("success") + "," + resultMap.get("fail");

	}

}

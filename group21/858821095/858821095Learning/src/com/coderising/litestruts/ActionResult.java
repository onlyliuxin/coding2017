package com.coderising.litestruts;

import java.util.HashMap;
import java.util.Map;

public class ActionResult {
	String actionName;
	String className;
	Map<String, String> resultV = new HashMap<>();

	public ActionResult(String actionName, String className) {
		this.actionName = actionName;
		this.className=className;
	}

	public String getClassName(){
		return className;
		
	}
	public void addResultView(String resultMessage, String resultView) {
		resultV.put(resultMessage, resultView);
	}
	
	public String getResultView(String resultMessage){
		return resultV.get(resultMessage);
		
	}
}

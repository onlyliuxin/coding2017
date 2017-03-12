/**
 * 
 */
package com.coderising.litestruts;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class Action {
	private Map<String,String> resultMap= new HashMap<String,String>();
	public static final String CONST_ACTION = "action";
	public static final String CONST_NAME = "name";
	public static final String CONST_CLASS = "class";
	public static final String CONST_RESULT = "result";
	
	private String actionName;
	private String actionClass;
	
	public Action(){};
	
	public Action(String actionName,String actionClass){
		this.actionName = actionName;
		this.actionClass = actionClass;
	}
	
	public void setActionResultJsp(String result,String dispatcherJsp){
		this.resultMap.put(result, dispatcherJsp);
	}
	
	public String getActionResultJsp(String result){
		return this.resultMap.get(result);
	}
	
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(CONST_ACTION).append(":").append(this.actionName).append(",");
		sb.append(CONST_CLASS).append(":").append(this.actionClass).append(",");
		sb.append("ResultMap").append(":").append(this.resultMap.toString());
		return sb.toString();
	}
}

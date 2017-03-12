package com.work.week02;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class StrutsXmlDao implements Serializable {

	private static final long serialVersionUID = -3117497421210403602L;

	private String actionName;
	private String actionClass;
	private List<Map<String, Object>> actionResult;
	
	public StrutsXmlDao(){
		super();
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

	public List<Map<String, Object>> getActionResult() {
		return actionResult;
	}

	public void setActionResult(List<Map<String, Object>> actionResult) {
		this.actionResult = actionResult;
	}

	@Override
	public String toString() {
		return "StrutsXmlDao [actionName=" + actionName + ", actionClass=" + actionClass + ", actionResult="
				+ actionResult + "]";
	}
	
}

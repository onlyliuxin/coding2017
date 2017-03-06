package test02.litestruts;

import java.util.HashMap;

public class Action {
	String className;
	HashMap<String, String> resultJspMap;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public HashMap<String, String> getResultJspMap() {
		return resultJspMap;
	}
	public void setResultJspMap(HashMap<String, String> resultJspMap) {
		this.resultJspMap = resultJspMap;
	}

}

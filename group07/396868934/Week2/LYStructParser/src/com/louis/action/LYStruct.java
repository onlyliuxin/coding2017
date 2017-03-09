package com.louis.action;

import com.louis.action.LYView;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import com.louis.action.LYDOMParser;

public class LYStruct {
	
	public static LYView runAction(String actionName, Map<String, String> parameters) {
		
		String className = LYDOMParser.shareInstance().getClassName(actionName);
		Class<?> cls = null;
        try{
        	cls = Class.forName(className);
        	Method[] methods = cls.getMethods();
            Object object = cls.newInstance();
            
            String message = "";
            boolean result = false;
            for (int i = 0; i < methods.length; i++) {
    			if (methods[i].getName().equals(actionName)) {
    				result = (boolean) methods[i].invoke(object, parameters.get("name"), parameters.get("password"));
    			} 
    		}
            for (int i = 0; i < methods.length; i++) {
            	if (methods[i].getName().equals("getMessage")) {
    				message = (String)methods[i].invoke(object);
    			} 
    		}
            
            
            LYView view = new LYView();
            Map<String, String> params = new HashMap<String, String>();
			if (result) {
				view.setJsp(LYDOMParser.shareInstance().getSuccessJSP(actionName));
			} else {
				view.setJsp(LYDOMParser.shareInstance().getFailedJSP(actionName));
			}
			params.put("message", message);
			view.setParameters(params);
			return view;
        }catch(Exception e){
            e.printStackTrace();
        }
		return new LYView();
	}
	
	
	public static void main(String[] args) {
		String actionName = "login";
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("name", "test");
		parameters.put("password", "123456");
		
		LYView view = LYStruct.runAction(actionName, parameters);
		System.out.println(view.getJsp());
		System.out.println(view.getParameters());
	}
}

package org.lite.struts;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.comm.util.StringUtil;



public class Struts {
	
	public static void main(String [] args){
		String actionName = "login";
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("name", "test");
		params.put("pwd", "1234");
		runAction(actionName,params);
	}

    public static View runAction(String actionName, Map<String,String> parameters) {
    	HashMap strutsMap = ReadXML.readXml(actionName);
    	View view = null;
    	
    	if(!strutsMap.isEmpty()){
    		String className = (String)strutsMap.get("ClassName");
    		
    		try {
				Class cls = Class.forName(className);
				 try {
					Object obj = cls.newInstance();
					Field nameF = cls.getDeclaredField("name");
					nameF.setAccessible(true);
					nameF.set(obj, StringUtil.objToStr(parameters.get("name")));
					
					 Field pwdF = cls.getDeclaredField("password");
					 pwdF.setAccessible(true);
					 pwdF.set(obj, StringUtil.objToStr(parameters.get("pwd")));
					 
					try {
						Method method = cls.getMethod("execute");
						String result = (String)method.invoke(obj);
						Field messageF = cls.getDeclaredField("message");
						messageF.setAccessible(true);
						String msg = (String)messageF.get(obj);
						String pageUrl = (String)strutsMap.get(result+"URL");
						view = new View();
						view.setJsp(pageUrl);
						HashMap map = new HashMap();
						map.put("msg", msg);
						map.put("result", result);
						view.setParameters(map);
						
						StringUtil.printlnStr("result:"+result);
						StringUtil.printlnStr("msg:"+msg);
						StringUtil.printlnStr("pageUrl:"+pageUrl);
					} catch (NoSuchMethodException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
					 
					
					
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}else{
    		StringUtil.printlnStr("Action is not found");
    	}
    	
    	
    	return view;
    }    

}

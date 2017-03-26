package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.*;


public class Struts {

	
    public static View runAction(String actionName, Map<String,String> parameters) {

        /*
         
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
        
        */
    	
    	XMLHandle xmlHandle;
    	Class<?> action = null;
    	Object object = null;
  	   	             
		try {
			xmlHandle = new XMLHandle();
			String className = xmlHandle.getClassName(actionName);
			if (className == null) {
				return null;
			}
			action = Class.forName(className);
			object = action.newInstance();
			Iterator<Map.Entry<String, String>> mapIt = parameters.entrySet().iterator();
	        while (mapIt.hasNext()) {
	        	Map.Entry<String, String> entry = mapIt.next();
	        	Method[] methods = action.getDeclaredMethods();
	        	for (Method method : methods) {
	        		if (method.getName().equalsIgnoreCase("set" + entry.getKey())) {
	        		    method.invoke(object, entry.getValue());
	        		}
	        	}                   	                 	                    	               	
	        }
	        
	        Method execute = action.getDeclaredMethod("execute");
	        String result = (String)execute.invoke(object);
	        
	        Field[] fields = action.getDeclaredFields();
	        Map<String, String> map = new HashMap<String, String>();
	        for (Field field : fields) {
	        	Method[] methods = action.getMethods();
	        	for (Method method : methods) {
	        		if (method.getName().equalsIgnoreCase("get" + field.getName())) {        			
	        			map.put(field.getName(), (String)method.invoke(object));
	        		}
	        	}
	        }
	        View view = new View();
	        view.setParameters(map);	        
	        String jspResult = xmlHandle.getResult(actionName, result);	        
	        view.setJsp(jspResult);	        
	        return view;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        
    
    	return null;
    }    

}

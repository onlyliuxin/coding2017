package com.coderising.litestruts;

import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Method;
public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

        /*
         
		0. 读取配置文件struts.xml*/
 		/*
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
		try {
			String targetClassName = XmlUtil.parseXML("struts.xml", actionName);
			Class<?> targetClass = Class.forName(targetClassName);
			
			Method setName = targetClass.getMethod("setName", String.class);
			Method setPassword = targetClass.getMethod("setPassword", String.class);
			Object object = targetClass.newInstance();

			setName.invoke(object, parameters.get("name"));
			setPassword.invoke(object, parameters.get("password"));

			Method execute = targetClass.getMethod("execute");
			String result = (String)execute.invoke(object);

			Method getMessage = targetClass.getMethod("getMessage");
			String message = (String)getMessage.invoke(object);

			Map<String, String> params = new HashMap<String, String>();
			params.put("message", message);
			String jspUrl = XmlUtil.getJspUrl("struts.xml", actionName, result);
			View view = new View();
			view.setJsp(jspUrl);
			view.setParameters(params);
			return view;

		} catch (Exception e) {
			e.printStackTrace();
		}
		   
    	
    	return null;
    }    
	
}

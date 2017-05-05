package com.coderising.litestruts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;



public class Struts {

	public static View runAction(String actionName, Map<String,String> parameters)  {
    	
        
		//0. 读取配置文件struts.xml
    	Document xmlDoc = null;
    	View view = null;
 		try {
 			xmlDoc = getXMLDocument("./src/main/java/com/coderising/litestruts/struts.xml");
    	
	 		/*1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
			据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
			("name"="test" ,  "password"="1234") ,     	
			那就应该调用 setName和setPassword方法*/
 		
	 		String classStr = xmlDoc.selectSingleNode("struts/action[@name='"+actionName+"']/@class").getText();
	 		Class<?> actionClass = Class.forName(classStr);
	 		Object action = actionClass.newInstance();
	 		
	 		Iterator<Map.Entry<String,String>> itr = parameters.entrySet().iterator();
	 		Entry<String,String> entry = null;
	 		String key = "";
	 		String value = "";
	 		while(itr.hasNext()) {
	 			entry = itr.next();
	 			key = entry.getKey();
	 			value = entry.getValue();
	 			Method setter = actionClass.getMethod("set"+key.substring(0, 1).toUpperCase()+key.substring(1),String.class);
	 			setter.invoke(action, value);
	 		}
	 		
		
	//		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
			Method exeMethod = actionClass.getMethod("execute");
			String result = exeMethod.invoke(action).toString();
//	 		System.out.println(result);
		 	
			/*3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
			通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
			放到View对象的parameters*/
	 		
		 	Map<String,String> viewDataMap = new HashMap<String,String>();
		 	String methodName = "";
		 	for (Method md : actionClass.getMethods()) {
		 		methodName = md.getName();
				if (methodName.startsWith("get")) {
					viewDataMap.put(methodName.substring(3, 4).toLowerCase()+methodName.substring(4), md.invoke(action).toString());
				}
			}
		 	
//		 	System.out.println(viewDataMap);
		 	
			/**
			4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
			放到View对象的jsp字段中。
	        */
		 	String jspRet = xmlDoc.selectSingleNode("struts/action[@name='"+actionName+"']/result[@name='"+result+"']").getText();
		 	view = new View();
		 	view.setJsp(jspRet);
		 	view.setParameters(viewDataMap);
 		} catch (Exception e) {
			e.printStackTrace();
		}
    	return view;
    }    
    
    private static Document getXMLDocument(String filePath) throws DocumentException {
    	return new SAXReader().read(filePath);
    }

}

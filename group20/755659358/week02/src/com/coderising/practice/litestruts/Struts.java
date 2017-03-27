package com.coderising.practice.litestruts;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;



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
    	
    	
    	
    	View view=new View();
    	Map<String,String> viewMap=new HashMap<String, String>();
    	Map<String, String> xmlMap=parseXML(actionName);
    	
    	try {

			Class<?> clazz= Class.forName(xmlMap.get("className"));

			Object object=clazz.newInstance();
			if (object instanceof LoginAction) {
				LoginAction action =(LoginAction) object;
				action.setName(parameters.get("name"));
				action.setPassword(parameters.get("password"));
			}
			Method execute = clazz.getMethod("execute");
			String executResult=(String) execute.invoke(object);
			Field[] fields=clazz.getDeclaredFields();
			Method[] methods=clazz.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().contains("get")) {
					String resultString=(String) method.invoke(object);
					for (Field field : fields) {
						field.setAccessible(true);
						if (field.get(object).equals(resultString)) {
							viewMap.put(field.getName(), resultString);
						}
					}
				}		
			}
			
			view.setJsp(xmlMap.get(executResult));
			
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	view.setParameters(viewMap);
    	
    	
    	return view;
    }    
    
    public static Map<String, String> parseXML(String actionName){
    	SAXParserFactory factory=SAXParserFactory.newInstance();
    	StrutsHandler hander=new StrutsHandler(actionName);
    	SAXParser parser;
		try {
			parser = factory.newSAXParser();
			parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/coderising/practice/litestruts/struts.xml"),hander);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return hander.getResult();
    }
    
    public static void setPara(Map<String, String> map){
    	
    	
    }
    
    public static String executAction(String className){
    	
    	return null;
    }
    
  
    
   

}

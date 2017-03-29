package com.danny.hw2;

import java.io.File;
import java.lang.invoke.CallSite;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.jaxen.*;

public class Struts {

	private static Document file = null;
	private static String file_path = 
			new File("").getAbsolutePath()+".\\xmlFolder\\struts.xml";

	private static String viewPath="com.danny.hw2.View";
	
	//0. 读取配置文件struts.xml
    private static Document parse(String path){
        SAXReader reader = new SAXReader();
        Document document = null;
		try {
			document = reader.read(path);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
        return document;
    }
    
    private static Element getAction(Document document,String name){

    	List<Element> lElement = (List<Element>) file.selectObject("/struts/action");
    	Element actionElement = null;
    	for(Element e:lElement){
    		if(e.attributeValue("name").equals(name)){
    			actionElement = e;    			
    			break;
    		}
    	}
    	return actionElement;
    }
	
    public static View runAction(String actionName, Map<String,String> parameters) {
    	
    	file = parse(file_path);
    	
    	//返回的class View;
    	Object viewClass = null;
    	
    	
    	//0 Get Action Element    	
    	Element actionElement = getAction(file, actionName);

    	
    	String actionClassPath = actionElement.attributeValue("class");
    	System.out.println(actionClassPath);
    	
    	Object action = null;
    	
    	try {
    		/*
    		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
    		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
    		("name"="test" ,  "password"="1234") ,   		     	
    		那就应该调用 setName和setPassword方法
    		*/
			action = Class.forName(actionClassPath).newInstance();
			
			Method setName = action.getClass().getMethod("setName",String.class);
			setName.invoke(action, parameters.get("name"));
			
			Method setPassword = action.getClass().getMethod("setPassword",String.class);
			setPassword.invoke(action, parameters.get("password"));
			
			
			//2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
			Method execute = action.getClass().getMethod("execute", null);
			Object status = execute.invoke(action);
			
			System.out.println(status.toString());
			
			
			//3获得所有的Getter字段
			Field[] files = action.getClass().getDeclaredFields();
			
			HashMap<String,String> save = new HashMap<>();
			
			//找到需要的数据，线存到save中
			for (int i = 0; i < files.length; i++) {
				String name = files[i].getName();
				name = "get" + name.substring(0,1).toUpperCase() + 
						name.substring(1);
				
				
				Method getter = action.getClass().getMethod(name,null);
				save.put(files[i].getName(), (String) getter.invoke(action));
				
			}
			//塞进viewClass里面
			viewClass = Class.forName(viewPath).newInstance();
			Method setParameters = viewClass.getClass().getMethod("setParameters", Map.class);
			setParameters.invoke(viewClass, save);
			
			
			
			//4将jsp放进去
			List<Element> resultElement = actionElement.selectNodes("result");
			for(Element e:resultElement){
				if(e.attributeValue("name").equals(status.toString())){
					String jspFields= e.getStringValue();
					viewClass.getClass().getMethod("setJsp", String.class).
					invoke(viewClass, jspFields);
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new RuntimeException(e.getMessage());
		}
    	
    	
    	
    	
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
    	
    
    	return (View) viewClass;
    }    


}

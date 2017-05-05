package com.coderising.litestruts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class Struts {

    @SuppressWarnings("unchecked")
	public static View runAction(String actionName, Map<String,String> parameters) throws Exception{
    	
    	Class<?> viewClass = Class.forName("com.coderising.litestruts.View");
        View view = (View)viewClass.newInstance();
    	
    	SAXReader saxReader = new SAXReader();  
        Document doc =  saxReader.read("src/com/coderising/litestruts/struts.xml");  
        Element root = doc.getRootElement();
        List<Element> list = root.elements("action");
        for(Element action_element : list) {  
        	if(actionName.equals(action_element.attributeValue("name"))){
               String className = action_element.attributeValue("class");
               Class<?> clazz = Class.forName(className);
               LoginAction la = (LoginAction)clazz.newInstance();
               Class<?> clazz1 = la.getClass();
               
               if(parameters.containsKey("name")){
            	   Method m1 = clazz1.getDeclaredMethod("setName", String.class);
            	   m1.invoke(la, parameters.get("name"));
               }
               if(parameters.containsKey("password")){
            	   Method m2 = clazz1.getDeclaredMethod("setPassword", String.class);
            	   m2.invoke(la, parameters.get("password"));
               }
               
               Method m3 = clazz1.getDeclaredMethod("execute");
               String result = (String)m3.invoke(la);
               Map<String, String> map = new HashMap<String, String>();
               // 这里没有通过反射，后续要改正成反射
               map.put("name", la.getName());
               map.put("password", la.getPassword());
               map.put("message", la.getMessage());
               
               view.setParameters(map);
               
               
                for(Element rusult_element : (List<Element>)action_element.elements("result")) {  
                	if(result != null && result.equals(rusult_element.attributeValue("name"))){
                		view.setJsp(rusult_element.getText());
                		break;
                	}
                }
                break;
        	}
        }
		return view;  
	}
	
}

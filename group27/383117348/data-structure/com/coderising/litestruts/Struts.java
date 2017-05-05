package com.coderising.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@SuppressWarnings("unchecked")
public class Struts {
	private static List<Element> list = new ArrayList<Element>();
	static{
		SAXReader read = new SAXReader();
    	Document doc = null;
		try {
			doc = read.read(Struts.class.getResourceAsStream("struts.xml"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Element root = doc.getRootElement();
    	list = root.elements();
	}
	
	
	
    public static View runAction(String actionName, Map<String,String> parameters){
    	String className = "";
    	Map<String,String> viewName = new HashMap<String,String>();
    	Element element = null;
    	View view = new View();
    	for(Element e : list){
    		if(e.attributeValue("name").equals(actionName)){
    			className = e.attribute("class").getText();
    			element = e;
    		}
    	}
    	Iterator<Element> e = element.elementIterator();
    	while(e.hasNext()){
    		Element result = e.next();
    		if(result.getName().equals("result"));
    		viewName.put(result.attributeValue("name"), result.getStringValue());
    	}
    	Class clazz = null;
		try {
			clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			Iterator<String> keys = parameters.keySet().iterator();
			while(keys.hasNext()){
					String key = keys.next();
					String methodName = "set"+key.substring(0,1).toUpperCase()+key.substring(1).toLowerCase();
					Method method =obj.getClass().getMethod(methodName, String.class);
					method.invoke(obj, parameters.get(key));	
			}
			Method execute = obj.getClass().getDeclaredMethod("execute");
			String result = (String)execute.invoke(obj);
			Map<String,Object> map = new HashMap<String,Object>();
			Method[] methods = obj.getClass().getDeclaredMethods();
			for(Method method : methods){
				String methodName = method.getName();
				if(methodName.startsWith("get")){
					map.put(methodName.substring(3,4).toLowerCase()+methodName.substring(4), method.invoke(obj));
					String s = (String)method.invoke(obj);
					System.out.println(methodName.substring(3,4).toLowerCase()+methodName.substring(4));
					System.out.println(s);
				}
				
			}
			view.setJsp(viewName.get(result));
			view.setParameters(map);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
    	
    	return view;
    }   
    
    public static void main(String[] args) {
    	SAXReader read = new SAXReader();
    	Document doc = null;
		try {
			doc = read.read(Struts.class.getResourceAsStream("struts.xml"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Element root = doc.getRootElement();
    	list = root.elements();
    	for(Element e : list){
    		System.out.println(e.attributeValue("class"));
    	}
	}
}

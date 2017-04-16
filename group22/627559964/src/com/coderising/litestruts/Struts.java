package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) throws Exception {

        /*
         
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class �? 例如LoginAction,   通过反射实例化（创建对象�?
		据parameters中的数据，调用对象的setter方法�? 例如parameters中的数据�? 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法�? 并获得返回�?�，例如"success"
		
		3. 通过反射找到对象的所有getter方法（例�? getMessage�?,  
		通过反射来调用， 把�?�和属�?�形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回�?�，  确定哪一个jsp�?  
		放到View对象的jsp字段中�??
        
        */
    	
    	//创建view对象
    	View view = new View();
    	
    	//读取xml文件的Document对象
    	SAXReader reader = new SAXReader();
    	Document document = reader.read(new File("src/com/coderising/litestruts/struts.xml"));
    	
    	//获取根节�?
    	Element root = document.getRootElement();
    	//根节点不是struts�?,结束方法
    	if (!root.getName().equals("struts")) {
    		return null;
    	}
    	//获取action匹配actionName的节�?
    	List<Element> children = root.elements("action");
    	Element targetElement = null;
    	for (Element element : children) {
    		System.out.println("name:" + element.attributeValue("name"));
    		System.out.println("class" + element.attributeValue("class"));
			if (element.attributeValue("name").equals(actionName)) {
				targetElement = element;
			}
		}
    	//没有name参数�?,结束方法
    	if (targetElement.attributeCount() <= 0) {
    		return null;
    	}
    	
    	Class clazz = Class.forName(targetElement.attributeValue("class"));
    	Object obj = clazz.newInstance();
    	Method setName = clazz.getDeclaredMethod("setName", String.class);
    	Method setPassword = clazz.getDeclaredMethod("setPassword", String.class);
    	Method execute = clazz.getDeclaredMethod("execute");
    	setName.invoke(obj, parameters.get("name"));
    	setPassword.invoke(obj, parameters.get("password"));
    	String remsg = (String) execute.invoke(obj);
    	System.out.println("结果�?" + remsg);
    	
    	Map parameter = new HashMap();
    	Method[] gets = clazz.getDeclaredMethods();
    	for (Method method : gets) {
			String methodName = method.getName();
			String name = methodName.substring(0,3);
			if (name.equals("get")) {
				Method getxxx = clazz.getDeclaredMethod(methodName);
				String xxx = methodName.substring(3, methodName.length()).toLowerCase();
				String temp = (String) getxxx.invoke(obj);
				parameter.put(xxx, temp);
			}
		}
    	List<Element> targetChilren = targetElement.elements();
    	for (Element element : targetChilren) {
    		String resultName = element.attributeValue("name");
    		System.out.println(resultName);
			if ("success".equalsIgnoreCase(resultName)) {
				view.setJsp(element.getText());
				continue;
			}
			if ("fail".equalsIgnoreCase(resultName)) {
				view.setJsp(element.getText());
				continue;
			}
		}
    	view.setParameters(parameter);
    	
    	return view;
    }

}

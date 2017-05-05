package com.coderising.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) throws ClassNotFoundException {

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
    	
    	
    	Class<?> targetClass = getTargetClass("struts.xml", actionName);
    	Object foo = null;
    	try {
			 foo = targetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Method method = null;
    	Method methodOne = null;
    	String result  = null;
    	Map<String, String> viewParas = new HashMap<>();
    	try {
    		for (String key : parameters.keySet()) {

    			method = foo.getClass().getMethod("set"+key.substring(0, 1).toUpperCase()+key.substring(1), String.class);
    			method.invoke(foo, parameters.get(key));
    		}
    		
    		methodOne = foo.getClass().getMethod("execute");
    		result = (String) methodOne.invoke(foo);
    		Method[] allMethods = foo.getClass().getDeclaredMethods();
    		for (Method m : allMethods) {
    			if (m.getName().startsWith("get")) {
    				viewParas.put(lowerFirstLetter(m.getName().substring(3)), (String) m.invoke(foo));
    			}
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	View view = new View();
    	view.setParameters(viewParas);
    	String destination = getDestiLoca("struts.xml", actionName, result);
    	view.setJsp(destination);

    	
    	return view;
    }    
    
    private static String lowerFirstLetter(String input) {
    	String result = input.substring(0,1).toLowerCase() + input.substring(1);
    	return result;
    }
    
    private static Document readXML (String fileName) {
    	Document doc = null;
    	try {
    		File xmlFile = new File(fileName);
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
    	
    	} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
    	
    	return doc;
    }
    
    private static Element getTargetElement(String fileName, String actionName) {
    	Document doc = readXML(fileName);
    	NodeList nList = doc.getElementsByTagName("action");
    	Element target = null;
    	for (int i = 0; i < nList.getLength(); i++) {
    		Node nNode = nList.item(i);
    		
    		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
    			Element element = (Element)nNode;
    			if (element.getAttribute("name").equals(actionName)){
    				target = element;
    				break;
    			}
    		}
    	}
    	
    	return target;
    }
    private static String getDestiLoca(String fileName, String actionName, String result) {
    	Document doc = readXML(fileName);
    	NodeList nList = doc.getElementsByTagName("action");
    	String resultDes = null;
    	for (int i = 0; i < nList.getLength(); i++) {
    		Node nNode = nList.item(i);
    		Element el = (Element)nNode;
    		
    		if (el.getAttribute("name").equals(actionName)){
    			
    			NodeList nListSub = el.getElementsByTagName("result");
    			for (int j = 0; j < nListSub.getLength(); j++) {
    				Node nNodeSub = nListSub.item(j);
    				Element eSub = (Element) nNodeSub;
    				if (eSub.getAttribute("name").equals(result)){
    					resultDes = eSub.getTextContent();
    					break;
    				}
    			}
    		}
    		
    	}
    	
    	return resultDes;
    }
    
    private static Class<?> getTargetClass(String fileName, String actionName) {
    	Element target = getTargetElement(fileName, actionName);
    	
    	String className = target.getAttribute("class");
    	Class<?> targetClass = null;
		try {
			targetClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
    	return targetClass;
    }

}

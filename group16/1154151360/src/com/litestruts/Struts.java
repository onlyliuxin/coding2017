package com.litestruts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

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
	
	
		//创建SAXreader对象
		SAXReader reader = new SAXReader();
		//将读取的文件封装成document对象
		Document document = reader.read(new File("src/com/litestruts/struts.xml"));
		//获取文档的根节点
		Element root = document.getRootElement();
		
		List<Element> elist = listNode(root);
		
		Map <String,Map<String,String>> actionMap =  getAction(elist, actionName);
		
		for(Map.Entry<String, Map<String,String>> entity: actionMap.entrySet()){
			 String className = entity.getKey();
			 Class clazz = Class.forName(className);
			 Method setName = clazz.getMethod("setName", String.class);
			 Method setPassword = clazz.getMethod("setPassword", String.class);
			 Method getMessage = clazz.getMethod("getMessage");
			 Method execute = clazz.getMethod("execute");
			 Object object = clazz.newInstance();
			 setName.invoke(object, parameters.get("name"));
			 setPassword.invoke(object, parameters.get("password"));
			 String status = (String) execute.invoke(object);
			 String message = (String) getMessage.invoke(object);
			 
			 String jsp = entity.getValue().get(status);
			 
			 Map<String,String> parameter = new HashMap<String, String>();
			 
			 parameter.put("message", message);
			 
			 View view = new View();
			 
			 view.setJsp(jsp);
			 view.setParameters(parameter);
			 
			 return view;
		 }
		 
		return null;
	}  
	
	//获取节点的所有子节点
	private static List<Element> listNode(Element node){
		
			Iterator<Element> iterator = node.elementIterator();
			List<Element> elist = new ArrayList<Element>();
			while (iterator.hasNext()){
				Element e = iterator.next();
				elist.add(e);
			}
		if (elist.isEmpty())
			return null;
		else
			return elist;
	}
	
	//获取对应的Action信息
	private static  Map <String,Map<String,String>>  getAction(List<Element> elist,String actionName){
		
		for (Element node:elist){
			List<Attribute> attributes = node.attributes();
			if (attributes.isEmpty())
				getAction(listNode(node),actionName);
			else{
				Attribute attribute = node.attribute("name");
				if (attribute.getValue().equals(actionName)){
					String className = node.attribute("class").getValue();
					List<Element> childElements = listNode(node);
					Map<String,String> resMap =install(childElements);
					Map <String,Map<String,String>> actionMap = new HashMap<String, Map<String,String>>();
					actionMap.put(className, resMap);
					return actionMap;
				}
			}
		}
		return null;
	}
	
	//组装action的result
	private static Map<String,String> install(List<Element> elements){
		Map<String,String> resultMap = new HashMap<String, String>();
		for (Element node: elements){
			Attribute attribute  = node.attribute("name");
			String value;
			if (node.getTextTrim().isEmpty())
				value = "";
			else
				value = node.getTextTrim();
			
			resultMap.put(attribute.getValue(), value);
			
		}
		return resultMap;
	}

}

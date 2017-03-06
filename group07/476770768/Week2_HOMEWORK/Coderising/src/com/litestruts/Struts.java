package com.litestruts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
		try {
			String path = "./src/com/litestruts/struts.xml";
			Document document = readStruts(path);
			NodeList actionList = document.getElementsByTagName("action");
			String actionClassName = getActionClassName(actionName, actionList);
			//System.out.println(actionClassName);
			Class c = Class.forName(actionClassName);
			Object o = c.newInstance();
			String result = executeAction(c, o, actionClassName, parameters);
			String message = getMessage(c, o);
			String jsp = getJSP(document, result, actionName, actionList);
			Map<String, String> m = new HashMap<String,String>();
			m.put("message", message);
			View view = new View();
			view.setJsp(jsp);
			view.setParameters(m);
			return view;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}   	
    	return null;
    }    
	
	/**
	 * get jsp message
	 * @param document
	 * @param result
	 * @param actionName
	 * @param actionList
	 * @return
	 */
	private static String getJSP(Document document, String result, String actionName, NodeList actionList) {
		Node actionNode = getactionNode(actionName, actionList);
		NodeList childNodes = actionNode.getChildNodes();
		String jsp = getContent(result, childNodes);
		return jsp;
	}

	/**
	 *
	 * @param result
	 * @param nodes: nodelist of childNode for action node
	 * @return
	 */
	private static String getContent(String result, NodeList nodes) {
		String jsp;
		if(result.equals("success")){
			jsp = getResultNodeForSuccess(nodes);
		}else{
			jsp = getResultNode(result, nodes);
		}
		return jsp;
	}
	
	/**
	 * get the certain result node while result is success
	 * @param nodes
	 * @return
	 */
	private static String getResultNodeForSuccess(NodeList nodes) {
		for(int i=0; i<nodes.getLength(); i++){
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				if(!node.hasAttributes()){
					//success is default
					return node.getTextContent();
				}else{
					NamedNodeMap attrs = node.getAttributes();
					for(int j=0; j<attrs.getLength(); j++){
						Node attr = attrs.item(j);
						if((attr.getNodeName().toString().equals("name")) && (attr.getNodeValue().toString().equals("success"))){
							String s = node.getTextContent();
							return s;
						}
					}
				
				}
			}
		}
		return null;
	}

	/**
	 * get the certain result node according to result
	 * @param result
	 * @param nodes
	 * @return
	 */
	private static String getResultNode(String result, NodeList nodes) {
		for(int i=0; i<nodes.getLength(); i++){
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				NamedNodeMap attrs = node.getAttributes();
				for(int j=0; j<attrs.getLength(); j++){
					Node attr = attrs.item(j);
					if((attr.getNodeName().toString().equals("name")) && (attr.getNodeValue().toString().equals(result))){
						String s = node.getTextContent();
						return s;
					}
				}
			}			
		}
		return null;
	}

	/**
	 * get message from action instance after running execute()
	 * @param c
	 * @param o
	 * @return
	 */
	private static String getMessage(Class c, Object o) {
				try {
					Method[] methods = c.getMethods();
					for(Method m : methods){
						if(m.getName().equalsIgnoreCase("getMessage")){
							String message = (String)m.invoke(o);
							return message;
						}
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
		return null;
	}

	/**
	 * load struts.xml, return a Document
	 * @param path
	 * @return
	 */
	public static Document readStruts(String path){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(path);
			return document;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * get the name of actionClass
	 * @param actionname
	 * @param actionList
	 * @return
	 */
	public static String getActionClassName(String actionname, NodeList actionList){
		Node actionNode = getactionNode(actionname, actionList);//this is the node which name = actionName
		NamedNodeMap attrs = actionNode.getAttributes();
		
		for(int i=0; i<attrs.getLength(); i++){
			//find class name
			Node attr = attrs.item(i);
			if(attr.getNodeName().toString().equals("class")){
				String s = attr.getNodeValue().toString();
				return s;
			}
		}
		
		return null;
	}
	
	/**
	 * get a certain action Node according to the action name
	 * @param name
	 * @param actionList
	 * @return
	 */
	public static Node getactionNode(String classname, NodeList actionList){
		for(int i=0; i<actionList.getLength(); i++){
			Node action = actionList.item(i);
			NamedNodeMap attrs = action.getAttributes();
			for(int j=0; j<attrs.getLength(); j++){
				Node attr = attrs.item(j);
				if((attr.getNodeName().toString().equals("name")) && (attr.getNodeValue().toString().equals(classname))){
					return action;
				}
			}
		}
		return null;
	}
	
	/**
	 * run execute() method
	 * @param c
	 * @param o
	 * @param actionClassName
	 * @param parameters
	 * @return
	 */
	public static String executeAction(Class c, Object o, String actionClassName, Map<String, String> parameters){
		try {
			
			setVar(c, o, parameters);
			Method m = c.getMethod("execute");
			String message = (String)m.invoke(o);
			return message;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * set variables of action class instance 
	 * @param c
	 * @param o
	 * @param parameters
	 */
	public static void setVar(Class c, Object o, Map<String, String> parameters){
		Method[] methods = c.getMethods();
		Iterator<Map.Entry<String, String>> entries = parameters.entrySet().iterator();
		while(entries.hasNext()){
			Map.Entry<String, String> entry = entries.next();
			String methodName = "set" + entry.getKey();
			for(Method m : methods){
				if(m.getName().equalsIgnoreCase(methodName)){
					try {
						m.invoke(o, entry.getValue());
						//System.out.println(m.getName());
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

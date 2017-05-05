package com.coderising.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Struts {

    @SuppressWarnings("unchecked")
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
    	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    	View view = null;
    	try {
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			File f = new File("E:/gitProject/coding2017/group15/1515_337959725/src/com/coderising/litestruts/struts.xml");
			Document document = documentBuilder.parse(f);
			NodeList actionList = document.getElementsByTagName("action");
			Node node = null;
			String className="";
			for(int i=0;i<actionList.getLength();i++){
				Node action = actionList.item(i);
				NamedNodeMap attrs = action.getAttributes();
				for(int j=0;j<attrs.getLength();j++){
					String nodeValue = attrs.item(j).getNodeValue();
					if(actionName.equals(nodeValue)){
						node=action;
					}
				}
			}
			NamedNodeMap nodeAtts = node.getAttributes();
			for(int j=0;j<nodeAtts.getLength();j++){
				String nodeName =nodeAtts.item(j).getNodeName();
				if("class".equals(nodeName)){
					className=nodeAtts.item(j).getNodeValue();
				}
			}
			Class clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			String methodName="";
			for (String key : parameters.keySet()) {
				methodName="set"+key.substring(0, 1).toUpperCase()+key.substring(1,key.length());
				Method method= clazz.getMethod(methodName, String.class);
				method.invoke(obj, parameters.get(key));
			}
			Method method1 = clazz.getMethod("execute");
			String rt = (String)method1.invoke(obj);
			Method method2 = clazz.getMethod("getMessage");
			String message = (String)method2.invoke(obj);
			NodeList childNodes = node.getChildNodes();
			Node result = null;
			for(int j=0;j<childNodes.getLength();j++){
				Node child= childNodes.item(j);
				if(child.getNodeType()==Node.ELEMENT_NODE){NamedNodeMap attrs = child.getAttributes();
				for(int k=0;k<attrs.getLength();k++){
					String nodeValue = attrs.item(k).getNodeValue();
					if(rt.equals(nodeValue)){
						result=child;
					}
				}}
			}
			String jsp = result.getNodeValue();
			parameters.put("Message", message);
			view=new View();
			view.setJsp(jsp);
			view.setParameters(parameters);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
    }    

}

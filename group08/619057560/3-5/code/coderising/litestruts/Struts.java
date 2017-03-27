package com.coderising.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
    	
    	File file = new File("src/com/coderising/litestruts/struts.xml");
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = (Document) dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("action");
			
			int nodeLen = nodeList.getLength();
			for (int i = 0; i < nodeLen; i++) {
				Element actionE = (Element) nodeList.item(i);
				if (actionE.getAttribute("name").contentEquals(actionName)) {
					Class <?> c = Class.forName(actionE.getAttribute("class").replace("action", "litestruts"));
					Object ob = c.getConstructor(null).newInstance(null);
					
					Method mSetName = c.getMethod("setName", String.class);
					Method mSetPwd = c.getMethod("setPassword", String.class);
					Method mGetName = c.getMethod("getName", null);
					Method mGetPwd = c.getMethod("getPassword", null);
					Method mExecute = c.getMethod("execute", null);
					Method mGetMsg = c.getMethod("getMessage", null);
					
					mSetName.invoke(ob, parameters.get("name"));
					mSetPwd.invoke(ob, parameters.get("password"));
					String result = (String)mExecute.invoke(ob, null);
					String message = (String)mGetMsg.invoke(ob, null);
					
					NodeList resultList = actionE.getElementsByTagName("result");
					for (int j = 0; j < resultList.getLength(); j++) {
						Element resultE = (Element)resultList.item(j);
						if (resultE.getAttribute("name").contentEquals(result)) {
							HashMap<String, String> params = new HashMap<String, String>();
							View retView = new View();
							retView.setJsp(resultE.getTextContent());
							params.put("message", message);
							retView.setParameters(params);
							
							return retView;
						}
					}
					
					break;
				}
			}
			
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
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
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
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    }    

}

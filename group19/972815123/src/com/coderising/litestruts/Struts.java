package com.coderising.litestruts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.coding.basic.Iterator;



public class Struts {
	private static String url = "C:\\Users\\alioc\\workspace\\Homework\\src\\com\\coderising\\litestruts\\struts.xml";
	private static int SET = 1;
	private static int GET = -1;
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

    public static View runAction(String actionName, Map<String,String> parameters) {
    	HashMap<String, HashMap<String, String>> xml = parseStrutsXml(url);
    	HashMap<String, String> login = xml.get(actionName);
    	String loginClassName = login.get("class");
    	System.out.println(loginClassName);
    	
    	ClassLoader loader = ClassLoader.getSystemClassLoader();
    	try {
			Class<?> clazz =  Class.forName(loginClassName);
			Object obj = clazz.newInstance();
			java.util.Iterator<String> iter = parameters.keySet().iterator();
			while(iter.hasNext()){
				String key = iter.next();
				String val = parameters.get(key);
				Method method = clazz.getDeclaredMethod(getEncapsulateMethodName(SET, key), String.class);
				method.invoke(obj, val);
			}
			Method executeMethod = clazz.getDeclaredMethod("execute");
			String logResult = (String) executeMethod.invoke(obj);
			if("success".equals(logResult)){
				View view = new View();
				view.setJsp(login.get("success"));
				
				Method getMessageMethod = clazz.getDeclaredMethod(getEncapsulateMethodName(GET, "message"));
				String message = (String) getMessageMethod.invoke(obj);
				
//				Field messageField = clazz.getDeclaredField("message");
//				messageField.setAccessible(true);
//				String message = (String) messageField.get(clazz);
				Map map = new HashMap<>();
				map.put("message", message);
				view.setParameters(map);
				
				return view;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    	
    	
    	
    	return null;
    }    
    
    private static HashMap<String, HashMap<String, String>> parseStrutsXml(String url){
    	
    	HashMap<String, HashMap<String, String>> result = new HashMap<String, HashMap<String, String>>();
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
    	try {
			DocumentBuilder builder = dbf.newDocumentBuilder();
			InputStream is = new FileInputStream(url);
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement(); 
			
			NodeList actionNodes = root.getChildNodes(); 
			for(int i = 0; i < actionNodes.getLength(); i ++){
				Node actionNode = actionNodes.item(i);
				if(actionNode.getNodeType() == Node.ELEMENT_NODE){
					if(actionNode.getAttributes() != null){
						HashMap<String, String> action = new HashMap<>();
						String name = actionNode.getAttributes().getNamedItem("name").getNodeValue();
						String clazz = actionNode.getAttributes().getNamedItem("class").getNodeValue();
						result.put(name, action);
						action.put("class", clazz);
						
						
						NodeList resultNodes = actionNode.getChildNodes();
						
						for(int j = 0; j < resultNodes.getLength(); j ++){
							Node resultNode = resultNodes.item(j);
							if(resultNode.getNodeType() == Node.ELEMENT_NODE){
								String fieldName = resultNode.getAttributes().getNamedItem("name").getNodeValue();
								String fieldValue = resultNode.getTextContent();
								action.put(fieldName, fieldValue);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	
    	System.out.println("--------------------");
    	System.out.println(result);
    	return result;
    }

    private static String getEncapsulateMethodName(int type, String methodName){
    	StringBuffer sb = new StringBuffer();       
    	if(type < 0){
    		sb.append("get");       
    	}else{
    		sb.append("set");       
    	}
    	sb.append(methodName.substring(0, 1).toUpperCase());       
    	sb.append(methodName.substring(1)); 
    	return sb.toString();
    }
}

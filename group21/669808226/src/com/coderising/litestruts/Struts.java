package com.coderising.litestruts;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.lang.reflect.*;
import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
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
    	
    	
    	final URL uri = Struts.class.getProtectionDomain().getCodeSource().getLocation();
    	XMLParser xmlParser = new XMLParser();
    	try {
			xmlParser.Parse(uri.getPath() + "/struts.xml");
			//查找拥有属性name=actionName的node，并获取其class属性的value
			String className = xmlParser.GetNodesByName("action")
					.FindNodeByAttribute("name", actionName)
					.getAttributeValue("class");
			//通过反射获取class对象并创建实例
			Class<?> actionClass = Class.forName(className);
			Object actionInstance = actionClass.newInstance();
			//设置参数
			for(Map.Entry<String, String> entry : parameters.entrySet())
			{
				String setter = "set" 
						+ entry.getKey().substring(0, 1).toUpperCase() 
						+ entry.getKey().substring(1);
				Method m = actionClass.getDeclaredMethod(setter, entry.getValue().getClass());
				m.invoke(actionInstance, entry.getValue());
			}
			//execute
			Method executeMethod = actionClass.getDeclaredMethod("execute");
			String executeResult = executeMethod.invoke(actionInstance).toString();
			//存储执行后的结果
			HashMap hashMap = new HashMap();
			//获取所有getter方法，将其值放入HashMap，key为字段名
			Method[] methods = actionClass.getDeclaredMethods();
			for(Method m : methods)
			{
				if(m.getName().startsWith("get"))
				{
					String fieldName = m.getName().substring(3).toLowerCase();
					Object fieldValue = m.invoke(actionInstance);
					hashMap.put(fieldName, fieldValue);
				}
			}
			//查找拥有属性name=actionName的node，并获取其子节点
			//然后查找拥有name=executeResult的node，并获取其文本内容
			String jsp = xmlParser.GetNodesByName("action")
					.FindNodeByAttribute("name", actionName)
					.getChildNodes()
					.FindNodeByAttribute("name", executeResult)
					.getNode()
					.getTextContent();
			View view = new View();
			view.setParameters(hashMap);
			view.setJsp(jsp);
			return view;
		} catch (ParserConfigurationException | SAXException | IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }   
    
    static public void main(String[] args) throws Exception
    {
    	System.out.println("main");
    	//Struts.runAction("", parameters)
    }
    
    

}

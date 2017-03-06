package com.coding.week2.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		File f1 = new File("config/struts.xml");
		
		File f = new File(f1.getAbsolutePath());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc=builder.parse(f);
			Element root =  doc.getDocumentElement();
		
			Map<String,Map<String,String>> actions = new HashMap<String,Map<String,String>>();
			if (root.getNodeType() == Node.ELEMENT_NODE) {  
				NodeList actionList = root.getChildNodes();
				
			     for (int i = 0; i < actionList.getLength(); i++) {  
			    	 Node n = actionList.item(i);
	                 if (n.getNodeType() == Node.ELEMENT_NODE) {  
	                	 NamedNodeMap nnmap = n.getAttributes();  
	                	 Map<String,String> action = new HashMap<String,String>();
	                	 action.put(nnmap.item(0).getNodeValue(),nnmap.item(1).getNodeValue());
	                	 NodeList result= n.getChildNodes();
	                
	                	 for(int j = 0;j<result.getLength();j++){
	                		 Node r = result.item(j);
	                	
	                		 if(r.getNodeType() ==  Node.ELEMENT_NODE){
	                			 NamedNodeMap nn = r.getAttributes();  
	                			
		                		 action.put(nn.item(0).getNodeValue(), r.getFirstChild().toString());
	                		 }
	                		
	                		 
	                	 }
	                	 actions.put(nnmap.item(0).getNodeValue(), action);
	                	
	                 }  
	            }  
			}
			
			Map<String,String> requestAction=null;
			if(actionName!=null){
				requestAction=actions.get(actionName);
			}else{
				System.err.println("没有actionName");
			}
			
			try {
				if(requestAction!=null){
					Class<?>  c = Class.forName(requestAction.get(actionName));
					Object co = c.newInstance();
					if("login".equals(actionName)){
						String name = parameters.get("name");
						String password = parameters.get("password");
						Method m1=c.getMethod("setName", String.class);
						Method m2=c.getMethod("setPassword", String.class);
						Method m3 = c.getMethod("execute");
						m1.invoke(co, name);
						m2.invoke(co, password);
						String rest = (String)m3.invoke(co);
						Method m4 = c.getMethod("getMessage");
						String message = (String)m4.invoke(co);
						Map<String,String> pras =new HashMap<String,String>();
						pras.put("message", message);
						View view = new View();
						view.setJsp(requestAction.get(rest));
						view.setParameters(pras);
						return view;
					}
				}else{
					System.out.println("没有找到对应action");
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
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
		}
    	return null;
    }    
	
}

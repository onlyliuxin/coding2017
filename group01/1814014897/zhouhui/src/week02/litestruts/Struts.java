package week02.litestruts;

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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import week01.BasicDataStructure.LinkedList;
import week01.BasicDataStructure.List;


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
    	
    	//读取配置文件struts.xml
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder;
    	Document doc = null; 
    	View view = new View();
		try {
			builder = factory.newDocumentBuilder();
			File f = new File("src/week02/litestruts/struts.xml");
	    	doc = builder.parse(f);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	////根据actionName找到相对应的class
    	Element root = doc.getDocumentElement();
    	NodeList actionNode = root.getElementsByTagName("action");
    	Element action = null;
    	for(int i=0;i<actionNode.getLength();i++){
    		NamedNodeMap attributes = actionNode.item(i).getAttributes();
    		for(int j=0;j<attributes.getLength();j++){
    			Node attribute = attributes.item(j);
    			String value = attribute.getNodeValue();
    			if(value.equals(actionName)){
    				action = (Element) actionNode.item(i);
    			}
    		}
    	}
    	
    	String actionClass = action.getAttribute("class");
    	try {
			Class<?> cls = Class.forName(actionClass);
			Object obj = cls.newInstance();
			Method setName = cls.getMethod("setName", String.class); 
			Method setPassword = cls.getMethod("setPassword", String.class);
			setName.invoke(obj, parameters.get("name"));
			setPassword.invoke(obj, parameters.get("password"));
			
			//通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
			Method execute = cls.getMethod("execute");
			String exe_val = (String) execute.invoke(obj);
			
			//通过反射找到对象的所有getter方法（例如 getMessage）,  通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  放到View对象的parameters
			Method[] met = cls.getDeclaredMethods();
			List list = new LinkedList();
			for(int i=0;i<met.length;i++){
				if(met[i].getName().startsWith("get")){
					list.add(met[i]);
				}
			}
			
			Method[] getter = new Method[list.size()];
			for(int i=0;i<list.size();i++){
				getter[i] = (Method) list.get(i);
			}
			
			Map<String ,String> param = new HashMap<>();
			for(int i=0;i<getter.length;i++){
				if(getter[i].equals(cls.getMethod("getMessage")))
				param.put("message",(String) getter[i].invoke(obj));
			}
			//View view = new View();
			view.setParameters(param);
			
			//根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  放到View对象的jsp字段中。
			/**NodeList resultNode = action.getElementsByTagName("result");
			for(int i=0;i<resultNode.getLength();i++){
				Element resultElement = (Element) resultNode.item(i);
				if(resultElement.getAttribute("name").equals(exe_val) && exe_val.equals("success"))
					view.setJsp("/jsp/homepage.jsp");
				else view.setJsp("/jsp/showLogin.jsp");
			}**/
			if(exe_val.equals("success"))
				view.setJsp("/jsp/homepage.jsp");
			else view.setJsp("/jsp/showLogin.jsp");
			
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return view;
    }   
    

}

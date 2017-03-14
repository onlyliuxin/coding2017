package week02.litestruts;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import week01.BasicDataStructure.LinkedList;
import week01.BasicDataStructure.List;

/**
 * @author Hui Zhou
 * @version 1.0 2017-02-28
 */


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
    	View view = new View();  //实例化View（后面调用view，存储parameters以及jsp，最后return view）
	try {
		builder = factory.newDocumentBuilder();
		File f = new File("src/week02/litestruts/struts.xml");
	    	doc = builder.parse(f);
	} catch (ParserConfigurationException|SAXException|IOException e) {
		e.printStackTrace();
	} 
    	
    	//根据actionName找到相对应的action
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
    	
    	//根据action找到相对应的class
    	String actionClass = action.getAttribute("class");
    	try {
		Class<?> cls = Class.forName(actionClass);
		Object obj = cls.newInstance();
		Method setName = cls.getMethod("setName", String.class); 
		Method setPassword = cls.getMethod("setPassword", String.class);
		setName.invoke(obj, parameters.get("name"));
		setPassword.invoke(obj, parameters.get("password"));
			
		//通过反射调用对象的exectue 方法,并获得返回值
		Method execute = cls.getMethod("execute");
		String exe_val = (String) execute.invoke(obj);
			
		//通过反射找到对象的所有getter方法,通过反射来调用
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
			
		//通过反射来调用,把值和属性形成一个HashMap,例如 {"message","login successful"} 
		Map<String ,String> param = new HashMap<>();
		for(int i=0;i<getter.length;i++){
			if(getter[i].equals(cls.getMethod("getMessage")))
			param.put("message",(String) getter[i].invoke(obj));
		}
			
		//放到View对象的parameters
		view.setParameters(param);
			
		//根据struts.xml中的 <result> 配置,以及execute的返回值,确定哪一个jsp,放到View对象的jsp字段中
		if(exe_val.equals("success"))
			view.setJsp("/jsp/homepage.jsp");
		else view.setJsp("/jsp/showLogin.jsp");
			
	} catch (ClassNotFoundException|InstantiationException|IllegalAccessException
		|NoSuchMethodException|SecurityException|IllegalArgumentException|InvocationTargetException e) {
			e.printStackTrace();
		} 
    	
    	return view;
    }   
}

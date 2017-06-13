package com.coderising.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hamcrest.core.SubstringMatcher;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {
    	
    	View view = new View();
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
    	
    	
    	//Dom4J读取struts.xml
    	
    	//System.out.println(Struts.class.getResource("").getPath());
    	
    	File file = new File(Struts.class.getResource("").getPath()+"struts.xml");
    	
    	
    	SAXReader rd = new SAXReader();
    	try {
    		Document doc =	rd.read(file);
    		
    		Element root = doc.getRootElement();
    		
    		Element action;
    		
    		for (Iterator iterator = root.elementIterator("action"); iterator.hasNext();) {
    			action = (Element) iterator.next();
    			//System.out.println(action.attributeValue("name")+"===="+action.attributeValue("class"));
    			
    			
    			
    			if (null != actionName &&   actionName.equals(action.attributeValue("name")) ) {
					
    				
    				//System.out.println(action.attributeValue("name")+"========"+action.attributeValue("class"));
    				
    				Class actionclass = Class.forName(action.attributeValue("class"));
    				
    				Object actionObj =   actionclass.newInstance();
    				
    				
    				Method[] methods = actionclass.getMethods();
    				
    				for (int i = 0; i < methods.length; i++) {
    					
    					String methodName = methods[i].getName();
    					
    					if (methodName.startsWith("set")) {
    						
    						if (parameters.get(methodName.substring(3).toLowerCase()) != null) {
    							
    							methods[i].invoke(actionObj, parameters.get(methodName.substring(3).toLowerCase()));
    							
							}
							
						}
					}
    				
    				Method meththod = actionclass.getMethod("execute", null);
    				
    				
    				
    				String result =   (String) meththod.invoke(actionObj);
    				
    				Map resultParameters = new HashMap();
    				
    				for (int i = 0; i < methods.length; i++) {
    					
    					String methodName = methods[i].getName();
    					
    					if (methodName.startsWith("get")) {
    						
    						resultParameters.put(methodName.substring(3).toLowerCase(), methods[i].invoke(actionObj));
							
						}
					}
    				
    				
    				
    				Element resultElement;
        			for (Iterator iterator2 = action.elementIterator(); iterator2.hasNext();) {
        				resultElement = (Element) iterator2.next();
    					if (resultElement.attributeValue("name").equals(result)) {
    						view.setJsp(resultElement.getText());
    						view.setParameters(resultParameters);
						}
    					
    				}
        			
				}
				
			}
    		
    		
		} catch (DocumentException e) {
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
    
    public static void main(String[] args) {
    	
    	Map map = new HashMap();
    	
    	map.put("name","test");
    	map.put("password","1234");
        
    	View view  = runAction("login", map);
    	System.out.println(view.getJsp());
		
	}
}

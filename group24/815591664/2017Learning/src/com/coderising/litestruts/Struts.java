package com.coderising.litestruts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import com.sun.istack.internal.Builder;



public class Struts {

    @SuppressWarnings("deprecation")
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
    	View view = new View();
    	String xmlpath = Struts.class.getResource("struts.xml").getFile();
    	SAXBuilder builder =  null;
    	try {
            xmlpath = URLDecoder.decode(xmlpath, "utf-8");
            builder = new SAXBuilder(false);
			Document doc = builder.build(xmlpath);
			Element root = doc.getRootElement();
			Element action = root.getChild("action");
			String className = action.getAttributeValue("class");
			Class<?> clazz = Class.forName(className);
			Object logAction = clazz.newInstance();
			for(String key :parameters.keySet()){
				String methodName = "set"+ Struts.captureName(key);
				Method method = null;
				try {
					method = clazz.getMethod(methodName, String.class);
				} catch (SecurityException e) {
					e.printStackTrace();
					break;
				} catch (NoSuchMethodException e) {
					break;
				}
				method.invoke(logAction, parameters.get(key));
				
			}
			Method executeMethod = clazz.getMethod("execute", (Class<?>[])null);
			String result = (String)executeMethod.invoke(logAction, (Object[])null);
			Method[] declareMtds = clazz.getDeclaredMethods();
			Map<String,String> paramForView = new HashMap<String, String>();
			for (Method method : declareMtds) {
				String methodName = method.getName();
				if(methodName.startsWith("get")){
					paramForView.put(methodName.substring(3).toLowerCase(), (String)method.invoke(logAction, (Object[])null));
				}
				
			}
			view.setParameters(paramForView);
			List<Element> results = action.getChildren("result");
			for (Element element : results) {
				String resultName = element.getAttributeValue("name");
				if(result.equals(resultName)){
					view.setJsp(element.getText());
				}
			}
	
		} catch (JDOMException e) {
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
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return view;
    }

    
    public static String captureName(String name) {
    	   //     name = name.substring(0, 1).toUpperCase() + name.substring(1);
//    	        return  name;
    	        char[] cs=name.toCharArray();
    	        cs[0]-=32;
    	        return String.valueOf(cs);
 
    	        
    }

}

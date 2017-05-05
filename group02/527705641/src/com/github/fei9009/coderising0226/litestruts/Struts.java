package com.github.fei9009.coderising0226.litestruts;

import java.util.Map;

import java.util.Map;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class Struts {
	
	 private static void setter(Object obj, String att, Object value, Class<?>type){  
	        try {  
	            Method met = obj.getClass().getMethod("set" + initStr(att), type);  
	            met.invoke(obj, value);  
	        }catch (Exception e){  
	            e.printStackTrace();  
	        }  
	    }  
	    
	    private static String getter(Object obj, String att){  
	        try {  
	            Method met = obj.getClass().getMethod("get" + initStr(att)); 
	            return (String)met.invoke(obj);
	        }catch (Exception e){  
	            e.printStackTrace();  
	        }  
	        return null;
	    }  
	    private static String initStr(String name) {  
	        name = name.substring(0, 1).toUpperCase() + name.substring(1);
	        return  name;  
	    }  
	    
	    private static String toLowerString(String name) {  
	        name = name.substring(0, 1).toLowerCase() + name.substring(1);
	        return  name;  
	    }

	
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
    	SAXReader saxReader = new SAXReader();
   	 	try {
   	 		Document document = saxReader.read(new File("struts.xml"));
   		    Element root = document.getRootElement();         
   
           
            boolean flag = false;
            String className = "";
            Element action = null;
            for (Iterator iter = root.elementIterator(); iter.hasNext();)
            {            	 
                Element e = (Element) iter.next();
                if(e.attributeValue("name").equals(actionName)){
               	 flag = true;
               	 className = e.attributeValue("class");
               	 action = e;
               	 break;
                }             
            }
            if(!flag)
           	 	throw new Exception(actionName+"未定义");
            
            Class<?> clz = Class.forName(className);  
            Constructor<?> c = clz.getConstructor();
            Object obj = c.newInstance();
            
            for (String in : parameters.keySet()) {
           	 	setter(obj,in,parameters.get(in), String.class);  
	        }
            Method exc = clz.getDeclaredMethod("execute");
            String res = (String)exc.invoke(obj);
            
            //获取所有getter方法
            //Get the methods
            Method[] methods = clz.getDeclaredMethods();
            //Loop through the methods and print out their names
            Map<String,String> params = new HashMap<String,String>();
            
            for (Method method : methods) {
           	 	String name = method.getName();
           	 	if(name.substring(0,3).equals("get")){
           	 		params.put(toLowerString(name.substring(3)),getter(obj,toLowerString(name.substring(3))));
           	 	}            	                 
            }
            View view = new View();
            view.setParameters(params);             
            //step 4
            flag = false;
            Element result = null;
            List<Element> actionChildList = action.elements("result"); 
            for (Iterator iter = action.elementIterator(); iter.hasNext();){            	 
           	 	Element e = (Element) iter.next();
                if(e.attributeValue("name").equals(res)){
               	 	flag = true;
               	 	result = e; 
               	 	break;
                }             
            }
            if(!flag)
           	 	throw new Exception(res+"undefined");
            view.setJsp(result.getText());
            return view;
		} catch (Exception e) {
			e.printStackTrace();
		}     
   	 	return null;
    }    

}

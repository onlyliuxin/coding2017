package com.sl.test20170304.struts;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;



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
    	
    	SAXReader reader = new SAXReader();
    	Object viewObj = null;
        try {
        
        	Document document = reader.read(new File("E://java开发环境"
        			+ "//java学习资料//java//java program//Coding2017//src//"
        			+ "com//sl//test20170304//struts//struts.xml"));
        	Element node = document.getRootElement();
        	List<Element> actionList = node.elements("action");
        	
        	Element temp = null;
        	
        	for(Element action : actionList){
        		Attribute nameAttr = action.attribute("name");
            	if(nameAttr.getText().equals(actionName)){
            		temp = action;
            		break;
            	}
        	}
        	
        	Class actionClass = Class.forName(temp.attribute("class").getText());
        	Object actionObj = actionClass.newInstance();
        	Method[] actionMethods = actionClass.getDeclaredMethods();
        	Field[] actionFields = actionClass.getDeclaredFields();
        	
        	for(Field field : actionFields){
        		try{
	        		String key = field.getName();
	        		String value = parameters.get(key);
	        		String filedSetName = parSetName(key);
	        		Method fieldSetMethod = actionClass.getMethod(filedSetName, field.getType());
	        		fieldSetMethod.invoke(actionObj, value);
        		}catch(Exception e){
        			continue;
        		}
        	}
        	String tempResult = null;
        	
        	for(Method method : actionMethods){
        		if("execute".equals(method.getName())){
        			tempResult = method.invoke(actionObj).toString();
        		}
        	}
        	System.out.println(tempResult);
        	
        	Map<String,String> params = new HashMap<String,String>();
        	
        	for(Field field : actionFields){
        		try{
	        		String key = field.getName();
	        		String value = null;
	        		String filedGetName = parGetName(key);
	        		Method filedGetMethod = actionClass.getMethod(filedGetName, new Class[] {});
	        		value = filedGetMethod.invoke(actionObj,new Object[] {}).toString();
	        		System.out.println(value);
	        		params.put(key, value);
        		}catch(Exception e){
        			continue;
        		}
        	}
        	
        	List<Element> results = temp.elements("result");
        	String resultVal = null;
        	for(Element result: results){
        		if(tempResult.equals(result.attribute("name").getText())){
        			resultVal = result.getStringValue();
        			break;
        		}
        	}
        	
        	Class viewClass = View.class;
        	viewObj = viewClass.newInstance();
        	Field[] viewFields = viewClass.getDeclaredFields();
        	
        	for(Field field : viewFields){
        		try{
	        		String fieldName = field.getName();
	        		String fieldType = field.getType().getSimpleName();
	        		String filedSetName = parSetName(fieldName);
	        		Method fieldSetMethod = viewClass.getMethod(filedSetName, field.getType());
	        		if("Map".equalsIgnoreCase(fieldType)){
	        			fieldSetMethod.invoke(viewObj, params);
	        		}else if("String".equalsIgnoreCase(fieldType)){
	        			fieldSetMethod.invoke(viewObj, resultVal);
	        		}
	        		
        		}catch(Exception e){
        			continue;
        		}
        	}
        	
        	
        	System.out.println(viewObj);
        	
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return (View)viewObj;
    }    
    
    
    @Test
    public void test(){
    	SAXReader reader = new SAXReader();
        try {
        	Map<String,String> parameters = new HashMap<String,String>();
        	parameters.put("name","test");
        	parameters.put("password","1234");
        	String actionName = "login";
        	Document document = reader.read(new File("E://java开发环境"
        			+ "//java学习资料//java//java program//Coding2017//src//"
        			+ "com//sl//test20170304//struts//struts.xml"));
        	Element node = document.getRootElement();
        	List<Element> actionList = node.elements("action");
        	
        	Element temp = null;
        	
        	for(Element action : actionList){
        		Attribute nameAttr = action.attribute("name");
            	if(nameAttr.getText().equals(actionName)){
            		temp = action;
            		break;
            	}
        	}
        	
        	Class actionClass = Class.forName(temp.attribute("class").getText());
        	Object actionObj = actionClass.newInstance();
        	Method[] actionMethods = actionClass.getDeclaredMethods();
        	Field[] actionFields = actionClass.getDeclaredFields();
        	
        	for(Field field : actionFields){
        		try{
	        		String key = field.getName();
	        		String value = parameters.get(key);
	        		String filedSetName = parSetName(key);
	        		Method fieldSetMethod = actionClass.getMethod(filedSetName, field.getType());
	        		fieldSetMethod.invoke(actionObj, value);
        		}catch(Exception e){
        			continue;
        		}
        	}
        	String tempResult = null;
        	
        	for(Method method : actionMethods){
        		if("execute".equals(method.getName())){
        			tempResult = method.invoke(actionObj).toString();
        		}
        	}
        	System.out.println(tempResult);
        	
        	Map<String,String> params = new HashMap<String,String>();
        	
        	for(Field field : actionFields){
        		try{
	        		String key = field.getName();
	        		String value = null;
	        		String filedGetName = parGetName(key);
	        		Method filedGetMethod = actionClass.getMethod(filedGetName, new Class[] {});
	        		value = filedGetMethod.invoke(actionObj,new Object[] {}).toString();
	        		System.out.println(value);
	        		params.put(key, value);
        		}catch(Exception e){
        			continue;
        		}
        	}
        	
        	List<Element> results = temp.elements("result");
        	String resultVal = null;
        	for(Element result: results){
        		if(tempResult.equals(result.attribute("name").getText())){
        			resultVal = result.getStringValue();
        			break;
        		}
        	}
        	
        	Class viewClass = View.class;
        	Object viewObj = viewClass.newInstance();
        	Field[] viewFields = viewClass.getDeclaredFields();
        	
        	for(Field field : viewFields){
        		try{
	        		String fieldName = field.getName();
	        		String fieldType = field.getType().getSimpleName();
	        		String filedSetName = parSetName(fieldName);
	        		Method fieldSetMethod = viewClass.getMethod(filedSetName, field.getType());
	        		if("Map".equalsIgnoreCase(fieldType)){
	        			fieldSetMethod.invoke(viewObj, params);
	        		}else if("String".equalsIgnoreCase(fieldType)){
	        			fieldSetMethod.invoke(viewObj, resultVal);
	        		}
	        		
        		}catch(Exception e){
        			continue;
        		}
        	}
        	
        	
        	System.out.println(viewObj);
        	
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
    
    /** 
     * 拼接在某属性的 set方法 
     *  
     * @param fieldName 
     * @return String 
     */  
    public static String parSetName(String fieldName) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        int startIndex = 0;  
        if (fieldName.charAt(0) == '_')  
            startIndex = 1;  
        return "set"  
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()  
                + fieldName.substring(startIndex + 1);  
    }  
    
    /** 
     * 拼接在某属性的 get方法 
     *  
     * @param fieldName 
     * @return String 
     */  
    public static String parGetName(String fieldName) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        int startIndex = 0;  
        if (fieldName.charAt(0) == '_')  
            startIndex = 1;  
        return "get"  
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()  
                + fieldName.substring(startIndex + 1);  
    }  
    
}

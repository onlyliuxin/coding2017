package com.coderising.litestruts;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
    	View view = new View();
    	
    	//0.读取配置文件struts.xml，根据actionName找到相对应的class ， 例如LoginAction，
    	Document document = getDocument("src/com/coderising/litestruts/struts.xml");
    	String className = getAttrValue(document,"/struts/action[@name=\""+actionName+"\"]/@class");
//    	System.out.println(className);
    	
    	//1.通过反射实例化（创建对象）
    	Class<?> cla = null;
    	Object obj = null;
    	Method executeMethod = null;
    	try {
			cla = Class.forName(className);
			obj = cla.newInstance();
			//获得该类的所有属性
			Field[] fields = cla.getDeclaredFields();
			
			//2.根据parameters中的数据，调用对象的setter方法
			for(Field field:fields){
				if(parameters.get(field.getName()) == null){
					continue;
				}
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), cla);
				//获得set方法
				Method myset = pd.getWriteMethod();
				myset.invoke(obj, parameters.get(field.getName()));
			}
			
			//3.通过反射调用对象的exectue 方法， 并获得返回值
			executeMethod = cla.getDeclaredMethod("execute");
			String executeReturn = (String) executeMethod.invoke(obj);
			
			//4.通过反射找到对象的所有getter方法，通过反射来调用， 把值和属性形成一个HashMap，放到View对象的parameters
			Map<String,String> viewparameters = new HashMap<String,String>();
			for(Field field:fields){
				//获得get方法
//				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), cla);
//				Method myget = pd.getReadMethod();
//				Object getValue = myget.invoke(obj);
//				System.out.println("field:"+field.getName()+"---getValue:"+getValue);
				
				//因为没有setMessage，所以换一种方式实现
				Method myget = cla.getDeclaredMethod("get"+captureName(field.getName()));
				Object getValue = myget.invoke(obj);
				viewparameters.put(field.getName(), (String) getValue);
//				System.out.println("field:"+field.getName()+"---getValue:"+getValue);
			}
			view.setParameters(viewparameters);
			
			//5.根据struts.xml中的 <result> 配置，以及execute的返回值，  确定哪一个jsp，放到View对象的jsp字段中。
			String viewjsp = getTextValue(document,"/struts/action[@name=\""+actionName+"\"]/result[@name=\""+executeReturn+"\"]");
			view.setJsp(viewjsp);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    	
    	return view;
    }
    
    /*
     * 根据xml文件的路径获取其Document
     */
    public static Document getDocument(String file){
    	SAXReader saxReader = new SAXReader();
    	Document document = null;
    	try {
			document = saxReader.read(new File(file));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	return document;
    }
    
    /*
     * 获取给定属性节点的值
     */
    public static String getAttrValue(Document doc,String attrNode){   	
    	List<?> list = doc.selectNodes(attrNode);
    	Iterator<?> iterator = list.iterator();
    	if(iterator.hasNext()){
    		Attribute attr = (Attribute) iterator.next();
    		return attr.getValue();
    	}
    	
    	//没有找到给定的属性节点则返回null
    	return null;
    }
    
    /*
     * 获取给定xml节点的文本值
     */
    public static String getTextValue(Document doc,String node){   	
	    List<?> list = doc.selectNodes(node);
	    Iterator<?> iterator = list.iterator();
	    if(iterator.hasNext()){
	    	Element element = (Element) iterator.next();
	    	return element.getText();
	    }
	    
	    //没有找到给定的xml节点则返回null
    	return null;
    }
    
    //首字母大写
    public static String captureName(String name) {
//        name = name.substring(0, 1).toUpperCase() + name.substring(1);
//        return  name;
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
    
    public static void main(String[] args){
        String actionName = "login";
        
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");
        
        runAction(actionName,params);
    }

}

package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {
    	View view = new View();
    	/*
    	 * 0. 读取配置文件struts.xml
    	 * */
    	SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new File("src/com/coderising/litestruts/struts.xml"));
			Element root = document.getRootElement();
			Iterator iter = root.elementIterator();
			while(iter.hasNext()){
				Element secondNode = (Element) iter.next();
				String nameStr = secondNode.attributeValue("name");
				String classStr = secondNode.attributeValue("class");
				
				/*
				 * 1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
				据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
				("name"="test" ,  "password"="1234") ,     	
				那就应该调用 setName和setPassword方法
				*/
				if(nameStr.equals(actionName)){
					Class<?> cls = Class.forName(classStr);
					Object obj = cls.newInstance();
					Method mtd1 = cls.getDeclaredMethod("setName", String.class);
					mtd1.invoke(obj, parameters.get("name"));
					
					Method mtd2 = cls.getDeclaredMethod("setPassword", new Class[]{String.class});
					mtd2.invoke(obj, parameters.get("password"));
					
					/*
					 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
					 * */
					Method execute = cls.getDeclaredMethod("execute");
					String runStatus = (String) execute.invoke(obj);
					
					/*
					 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
					通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
					放到View对象的parameters
					*/
					Method mtd3 = cls.getDeclaredMethod("getMessage");
					String getMes = (String) mtd3.invoke(obj);
					Map<String,String> params = new HashMap<String,String>();
			        params.put("message",getMes);
					
					/*
					 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
					放到View对象的jsp字段中。
					*/
			        Iterator iterSecond = secondNode.elementIterator();
			        while(iterSecond.hasNext()){
			        	Element thirdNode = (Element) iterSecond.next();
			        	String resultNameStr = thirdNode.attributeValue("name");
						String pageStr = thirdNode.getText();
						
						if(runStatus.equals(resultNameStr)){
							view.setJsp(pageStr);
							view.setParameters(params);
							break;
						}
			        }	
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
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
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    	return view;
    }
}

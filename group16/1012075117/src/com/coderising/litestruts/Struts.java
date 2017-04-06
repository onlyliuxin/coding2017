package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 读取配置文件 struts.xml - 第二次作业
 * @author stackwei
 * @date 2017/3/20
 * @status ok
 */
public class Struts {
	public static View runAction(String actionName, Map<String, String> parameters) throws Exception {

		/*
		 * 
		 * 0. 读取配置文件struts.xml
		 * 
		 * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
		 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,
		 * "password"="1234") , 那就应该调用 setName和setPassword方法
		 * 
		 * 2. 通过反射调用对象的execute 方法， 并获得返回值，例如"success"
		 * 
		 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如
		 * {"message": "登录成功"} , 放到View对象的parameters
		 * 
		 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
		 * 放到View对象的jsp字段中。
		 * 
		 */
		
		int flag = 0;
		String className;
		String executeResult;
		String jsp;
		Element resultElement;
		List<Element> actionList = new ArrayList<>();
		Map<String,Object> classNameMap = new HashMap<String,Object>();
		Map<String,Object> messagesMap = new HashMap<String,Object>();
		View view = new View();
		
		actionList = getRootElement("src/com/litestruts/struts.xml");// 获取所有<action>节点
		classNameMap = getClassName(actionList, actionName, classNameMap);// 获取action的类名并放到Map中
		
		className = (String) classNameMap.get("className");
		messagesMap = getResult(className, parameters);// messages包含了，调用execute()后的返回值result，和所有getter方法的值和属性
		
		executeResult = (String) messagesMap.get("result");
		messagesMap.remove("result");
		flag = (int) classNameMap.get("flag");
		resultElement = actionList.get(flag);
		jsp = getJSP(executeResult, resultElement);// 获取到<result>里的jsp
		
		view.setJsp(jsp);
		view.setParameters(messagesMap);
		
		return view;
	}

	/**
	 * 获取所有<action>节点
	 * 
	 * @param fileName
	 * @return
	 */
	private static List<Element> getRootElement(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(inputXml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		List<Element> al = new ArrayList<Element>();
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) { // 获取所有action节点
			Element action = (Element) i.next();
			al.add(action);
		}
		return al;
	}

	/**
	 * 根据给定的actionName，获取对应的class名字
	 * 
	 * @param al
	 * @param actionName
	 * @param map 
	 * @return
	 */
	private static Map<String,Object> getClassName(List<Element> al, String actionName, Map<String,Object> map) {
		String className = null;
		for(int i=0;i<al.size();i++) {
			if (al.get(i).attribute("name").getValue().equals(actionName)) {
				className = al.get(i).attribute("class").getValue();
				map.put("flag", i);
				map.put("className", className);
			}
		}
		return map;
	}
	
	/**
	 * 获得调用execute()后返回值result，和所有getter方法的值和属性
	 * @param className
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	private static Map<String,Object> getResult(String className, Map<String, String> parameters) throws Exception {
		Class<?> actionClass = null;
		Constructor<?> constructor = null;
		Object object = null;
		Method method = null;
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			actionClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			constructor = actionClass.getConstructor();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		object = constructor.newInstance();
		Set<String> keySet = parameters.keySet();
		// 据parameters中的数据，调用对象的setter方法
		for (String key : keySet) {
			if (key.equals("name")) {
				method = actionClass.getMethod("setName", String.class);
				method.invoke(object, parameters.get(key));
			}
			if (key.equals("password")) {
				method = actionClass.getMethod("setPassword", String.class);
				method.invoke(object, parameters.get(key));
			}
		}
		// 通过反射调用对象的execute 方法，并获得返回值，例如"success"
		method = actionClass.getMethod("execute");
		String result = (String) method.invoke(object);
		map.put("result", result);
		
		//找到对象的所有getter方法,把值和属性形成一个HashMap
		Method getName = actionClass.getMethod("getName");
		Method getPassword = actionClass.getMethod("getPassword");
		Method getMessage = actionClass.getMethod("getMessage");
		map.put("name", getName.invoke(object));
		map.put("password", getPassword.invoke(object));
		map.put("message", getMessage.invoke(object));
		
		return map;
	}
	
	private static String getJSP(String result, Element actionElement) {
		String jsp = null;
		for (Iterator<?> i = actionElement.elementIterator(); i.hasNext();) { // 获取所有action子节点result
			Element resultElement = (Element) i.next();
			if(resultElement.attribute("name").getValue().equals(result)) {
				jsp = resultElement.getTextTrim();
			}
		}
		return jsp;
	}

}
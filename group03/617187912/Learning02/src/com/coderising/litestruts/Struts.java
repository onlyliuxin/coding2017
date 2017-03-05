package com.coderising.litestruts;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

	public static View runAction(String actionName, Map<String, String> parameters)
			throws ClassNotFoundException, DocumentException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {

		/*
		 * 
		 * 0. 读取配置文件struts.xml
		 * 
		 * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
		 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,
		 * "password"="1234") , 那就应该调用 setName和setPassword方法
		 * 
		 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		 * 
		 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如
		 * {"message": "登录成功"} , 放到View对象的parameters
		 * 
		 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
		 * 放到View对象的jsp字段中。
		 * 
		 */
		String[] methodNames = createSetMethodNames(parameters);
		Struts.class.getResourceAsStream("/struts.xml");
		Element element = getTargetElement(actionName);
		String className = element.attribute(1).getValue();
		Class clz = Class.forName(className);
		Object obj = clz.newInstance();
		invokeObjectSetter(parameters, methodNames, clz, obj);
		View view = new View();
		view.setParameters(createGetterMap(clz, obj));
		setViewJsp(view, element, clz, obj);
		return view;
	}

	private static String[] createSetMethodNames(Map<String, String> parameters) {
		String[] methodNames = new String[parameters.size()];
		int i = 0;
		for (String key : parameters.keySet()) {
			methodNames[i++] = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
		}
		return methodNames;
	}

	private static void setViewJsp(View view, Element element, Class clz, Object obj)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		view.setJsp(getJsp(element, executeToGetResult(clz, obj)));
	}

	private static Map createGetterMap(Class clz, Object obj) throws IllegalAccessException, InvocationTargetException {
		Map map = new HashMap();
		Method[] methods = clz.getMethods();
		for (Method item : methods) {
			if (item.getName().contains("get")) {
				String key = item.getName().substring(3).toLowerCase();
				Object value = item.invoke(obj);
				map.put(key, value);
			}
		}
		return map;
	}

	private static String executeToGetResult(Class clz, Object obj)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Method method = clz.getMethod("execute");
		String result = (String) method.invoke(obj);
		return result;
	}

	private static void invokeObjectSetter(Map<String, String> parameters, 
			String[] methodNames, Class clz, Object obj)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		for (String key : methodNames) {
			Method method = clz.getMethod(key, String.class);
			method.invoke(obj, parameters.get(key));
		}
	}

	private static Element getTargetElement(String actionName) throws DocumentException {
		SAXReader reader = new SAXReader();
		InputStream inputStream =Struts.class.getResourceAsStream("/struts.xml");
		Document document = reader.read(inputStream);
		Element rootNode = document.getRootElement();
		List<Element> elements = rootNode.elements();
		for (Element item : elements) {
			if (actionName.equals(item.attribute(0).getValue())) {
				return item;
			}
		}
		return null;
	}

	private static String getJsp(Element element, String result) {
		List<Element> elements = element.elements();
		for (Element e : elements) {
			if (result.equals(e.attribute(0).getValue())) {
				return e.getTextTrim();
			}
		}
		return null;
	}
}

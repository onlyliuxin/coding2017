package com.coderising.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.omg.PortableInterceptor.ObjectIdHelper;
import org.xml.sax.SAXException;

import com.coding.basic.ArrayList;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class Struts {
	@Test
	public static View runAction(String actionName,
			Map<String, String> parameters) {

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
		 */
		
		// DOM4J方式解析xml
		// 读取文件 转换成Document
		View view = new View();
		if (actionName == null || actionName.isEmpty()) {
			return view;
		}
		try {
			//获取action标签元素
			Element actionEle = getActionElement(actionName);
			
			String qualifiedName = actionEle.attributeValue("class");
			Class<?> clazz = Class.forName(qualifiedName);
			Object instanceOfAction = getParameteredInstance(parameters, clazz);
			Method executeMethod = clazz.getMethod("execute");
			
			//调用execute方法，改变action实例，一定要在执行getResultMap方法之前执行
			String executeResult = (String) executeMethod.invoke(instanceOfAction);
			Method[] methods = clazz.getMethods();
			Map<?, ?> resultMap = getResultMap(instanceOfAction, methods);
			view.setParameters(resultMap);
			
			List<Element> resultEles = actionEle.elements("result");
			String jspPath = getJspPath(executeResult, resultEles);
			view.setJsp(jspPath);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return view;
	}

	private static String getJspPath(String result, List<Element> resultEles) {
		for (int i = 0; i < resultEles.size(); i++) {
			String text = resultEles.get(i).attributeValue("name");
			if (text.equals(result)) {
				String jspString = resultEles.get(i).getText();
				return jspString;
			}
		}
		return null;
	}

	private static Map<String, Object> getResultMap(Object obj, Method[] methodsOfObj)
			throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Method m;
		for (int i = 0; i < methodsOfObj.length; i++) {
			if (methodsOfObj[i].getName().startsWith("get")) {
				m = methodsOfObj[i];
				String methodName = getMethodNameInLowerCase(m);
				Object result = m.invoke(obj);
				resultMap.put(methodName.substring(3), result);
			}
		}
		return resultMap;
	}

	private static String getMethodNameInLowerCase(Method m) {
		String methodName = m.getName();
		StringBuilder sbr = new StringBuilder(methodName);
		sbr.setCharAt(3, Character.toLowerCase(sbr.charAt(3)));
		methodName = sbr.toString();
		return methodName;
	}

	private static Object getParameteredInstance(Map<String, String> parameters,
			Class<?> clazz) {
		Object obj = null;
		try {
			obj = clazz.newInstance();
			Map methodsAndParams = new HashMap();
			Set<String> keySet = parameters.keySet();
			for (String k : keySet) {
				String methodName = getMethodNameByProperty(k);
				methodsAndParams.put(methodName, parameters.get(k));
				Method setter = clazz.getMethod(methodName, parameters.get(k)
						.getClass());
				setter.invoke(obj, parameters.get(k));
			}
		} catch (InstantiationException | IllegalAccessException e) {
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
		return obj;
	}

	private static String getMethodNameByProperty(String k) {
		StringBuilder sb = new StringBuilder(k);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		sb = sb.insert(0, "set");
		String method = sb.toString();// 获得方法名
		return method;
	}

	@SuppressWarnings("unchecked")
	private static Element getActionElement(String actionName) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(
				"src/com/coderising/litestruts/struts.xml"));
		Element root = document.getRootElement();
		List<Element> actNodes = root.elements("action");
		Element e = null;
		String className;
		for (int i = 0; i < actNodes.size(); i++) {
			className = actNodes.get(i).attributeValue("name");
			if (className != null && className.equals(actionName)) {
				e = actNodes.get(i);
				break;
			}
		}
		return e;
	}

}

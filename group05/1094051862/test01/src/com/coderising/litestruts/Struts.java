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
		try {
			Element actNode = getActionNode(actionName);
			
			String qualiName = actNode.attributeValue("class");
			Class<?> clazz = Class.forName(qualiName);
			Object obj = getParameteredObject(parameters, clazz);
			Method method = clazz.getMethod("execute");

			Method[] methods = clazz.getMethods();
			String result = (String) method.invoke(obj);
			Map resultMap = getResultMap(obj, methods);
			View view = new View();
			view.setParameters(resultMap);
			
			
			List<Element> resultEles = actNode.elements("result");
			String jspPath = getJspPath(result, resultEles);
			view.setJsp(jspPath);
			System.out.println();
			return view;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
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

	private static Map getResultMap(Object obj, Method[] methods)
			throws IllegalAccessException, InvocationTargetException {
		Map resultMap = new HashMap();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().startsWith("get")) {
				String str = methods[i].getName();
				StringBuilder sbr = new StringBuilder(str);
				sbr.setCharAt(3, Character.toLowerCase(sbr.charAt(3)));
				str = sbr.toString();
				Class<?> type = methods[i].getReturnType();
				Object res = methods[i].invoke(obj);
				resultMap.put(str.substring(3), res);
			}
		}
		return resultMap;
	}

	private static Object getParameteredObject(Map<String, String> parameters,
			Class<?> clazz) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Object obj = clazz.newInstance();
		Map methodsAndParams = new HashMap();
		Set<String> keySet = parameters.keySet();
		for (String k : keySet) {
			StringBuilder sb = new StringBuilder(k);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			sb = sb.insert(0, "set");
			String method = sb.toString();// 获得方法名
			methodsAndParams.put(method, parameters.get(k));
			Method setter = clazz.getMethod(method, parameters.get(k)
					.getClass());
			setter.invoke(obj, parameters.get(k));
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	private static Element getActionNode(String actionName) throws DocumentException {
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

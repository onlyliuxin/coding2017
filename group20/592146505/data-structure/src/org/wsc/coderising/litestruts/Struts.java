package org.wsc.coderising.litestruts;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wsc.coderising.litestruts.util.DocumentUtil;
import org.xml.sax.SAXException;

public class Struts {
	private static final DocumentUtil DOCUMENT_UTIL;
	private static Document document;
	static{
		/* 0. 读取配置文件struts.xml */
		DOCUMENT_UTIL = DocumentUtil.newInstance();
		try {
			document = DOCUMENT_UTIL.getDocument("src/org/wsc/litestruts/struts.xml");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param actionName
	 * @param parameters
	 * @return
	 */
	public static View runAction(String actionName, Map<String, String> parameters) {
		String className = null;
		String jsp = null;
		Map<String, String> results = new HashMap<String, String>();
		Node struts = document.getDocumentElement();// 获取根节点
		NodeList actions = struts.getChildNodes();// 获取子节点
		for (int i = 0; i < actions.getLength(); i++) {
			// 过滤空节点
			if (actions.item(i).getNodeType() != Node.ELEMENT_NODE)
				continue;
			Element action = (Element) actions.item(i);
			if (!action.getAttribute("name").equals(actionName))
				continue;
			className = action.getAttribute("class");
			/*
			 * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
			 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,
			 * "password"="1234") , 那就应该调用 setName和setPassword方法
			 */
			Class clazz = null;
			Object instance = null;
			try {
				clazz = Class.forName(className);
				instance = clazz.getConstructor().newInstance();
				Set<String> keySet = parameters.keySet();
				for (String key : keySet) {
					Object parameter = parameters.get(key);
					Method method = instance.getClass().getMethod(
							"set" + (key.substring(0, 1).toUpperCase() + key.substring(1)), parameter.getClass());
					method.invoke(instance, parameter);
				}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}

			/* 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success" */
			String rt = null;
			try {
				Method method = clazz.getMethod("execute"); 
				rt = (String) method.invoke(instance);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			/*
			 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap
			 * , 例如 {"message": "登录成功"} , 放到View对象的parameters
			 */
			Field[] fields = instance.getClass().getDeclaredFields();
			Method[] methods = instance.getClass().getMethods();
			for (Field field : fields) {
				String fieldName = field.getName();
				for (Method method : methods) {
					if (method.getName()
							.equals(("get" + (fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1))))) {
						try {
							results.put(fieldName, (String) method.invoke(instance));
							break;
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
			NodeList resultNodes = action.getChildNodes();
			for (int j = 0; j < resultNodes.getLength(); j++) {
				if (resultNodes.item(j).getNodeType() != Node.ELEMENT_NODE)
					continue;
				Element result = (Element) resultNodes.item(j);
				if (!result.getAttribute("name").equals(rt))
					continue;
				jsp = result.getTextContent();
			}

		}
		/*
		 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
		 * 放到View对象的jsp字段中。
		 */
		View view = new View();
		view.setJsp(jsp);
		view.setParameters(results);
		return view;
	}
	
}

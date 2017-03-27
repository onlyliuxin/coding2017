package com.coderising.litestruts;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @ClassName: Struts
 * @Description: TODO
 * @author: msh
 * @date: 2017-3-2 上午9:37:55
 * @version: V1.0
 */
public class Struts {
	public static View runAction(String actionName, Map<String, String> parameters) {
		Map<String, String> xmlDoc = getXMLDOC(actionName);
		Map<String, String> viewMap = new HashMap<String, String>();
		View view = new View();
		view.setParameters(viewMap);
		try {
			Class<?> classz = Class.forName(xmlDoc.get(actionName));
			LoginAction la = (LoginAction) classz.newInstance();
			la.setName(parameters.get("name"));
			la.setPassword(parameters.get("password"));
			Method exectue = classz.getMethod("execute", null);
			Object result = exectue.invoke(la, null);
			Field[] fields = classz.getDeclaredFields();
			for (Field field : fields) {
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), classz);
				Method readMethod = pd.getReadMethod();
				viewMap.put(field.getName(), (String) readMethod.invoke(la, null));
			}
			view.setJsp(xmlDoc.get(result));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 0. 读取配置文件struts.xml 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象），
		 * 据parameters中的数据，调用对象的setter方法 例如parameters中的数据是 ("name"="test" , "password"="1234") ,
		 * 那就应该调用 setName和setPassword方法 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success" 3.
		 * 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message": "登录成功"} ,
		 * 放到View对象的parameters 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
		 * 放到View对象的jsp字段中。
		 */
		return view;
	}
	/**
	 * @MethodName: getXMLDOC
	 * @Description: 解析xml文件
	 * @param actionName
	 * @return
	 * @return: Map<String,String>
	 */
	private static Map<String, String> getXMLDOC(String actionName) {
		Map<String, String> xmldoc = new HashMap<String, String>();
		// 解析struts.xml文件
		// 创建SAXReader的对象reader
		SAXReader reader = new SAXReader();
		try {
			// 通过reader对象的read方法读取struts.xml，得到document对象
			Document document = reader.read(new File("src/com/coderising/litestruts/struts.xml"));
			// 获取根节点<struts></struts>
			Element struts = document.getRootElement();
			// 迭代节点
			Iterator<?> actions = struts.elementIterator();
			while (actions.hasNext()) {
				Element action = (Element) actions.next();
				if (actionName.equals(action.attributeValue("name"))) {
					xmldoc.put(action.attributeValue("name"), action.attributeValue("class"));
					Iterator<?> results = action.elementIterator();
					while (results.hasNext()) {
						Element result = (Element) results.next();
						xmldoc.put(result.attributeValue("name"), result.getStringValue());
					}
					break;
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		return xmldoc;
	}
	
}

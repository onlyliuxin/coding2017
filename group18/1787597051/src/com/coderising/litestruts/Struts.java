package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

	public static View runAction(String actionName, Map<String, String> parameters) {

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

		// 0. 读取配置文件struts.xml
		File inputXml = new File("src/com/coderising/litestruts/struts.xml");
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(inputXml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		// 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
		//根节点
		Element struts = document.getRootElement();
		String className = null;
		Element action = null;
		String attrVal = null;
		View view = new View();
		for (Iterator<?> i = struts.elementIterator(); i.hasNext();) {
			// 获取struts标签下的节点对象
			action = (Element) i.next();
			// 获取该(action)标签对象的属性
			Attribute attr = action.attribute("name");
			Attribute attr2 = action.attribute("class");
			if (null != attr) {
				attrVal = attr.getValue();
				if (attrVal.equals(actionName)) {
					className = attr2.getValue();
					Class<?> c = null;
					Constructor<?> con = null;
					Object obj = null;
					Method m = null;
					String result = null;
					try {
						c = Class.forName(className);
						con = c.getConstructor();
						obj = con.newInstance();
						m = c.getDeclaredMethod("setName", String.class);
						m.setAccessible(true);
						m.invoke(obj, parameters.get("name"));
						m = c.getDeclaredMethod("setPassword", String.class);
						m.setAccessible(true);
						m.invoke(obj, parameters.get("password"));
						// 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
						m = c.getMethod("execute");
						result = (String) m.invoke(obj);

						// 3. 通过反射找到对象的所有getter方法（例如 getMessage）,
						// 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message": "登录成功"} ,
						// 放到View对象的parameters
						m = c.getMethod("getName");
						String s = (String) m.invoke(obj);
						parameters.put("name", s);
						m = c.getMethod("getPassword");
						s = (String) m.invoke(obj);
						parameters.put("password", s);
						m = c.getMethod("getMessage");
						s = (String) m.invoke(obj);
						parameters.put("message", s);
						//放到View对象的parameters中
						view.setParameters(parameters);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					// 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
					// 放到View对象的jsp字段中。
					for (Iterator<?> j = action.elementIterator(); j.hasNext();) {
						Element node = (Element) j.next();
						Attribute attr3 = node.attribute("name");
						String str = attr3.getValue();
						if (null != attr3) {
							if (str.equals(result)) {
								//获取节点的内容
								String text = node.getText();
								view.setJsp(text);
							}
						}
					}
				}
			}
		}
		return view;
	}

}
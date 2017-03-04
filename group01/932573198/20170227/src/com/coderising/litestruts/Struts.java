package com.coderising.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

		View view = new View();
		/*
		 * 0、读取xml文件
		 */
		String path = "src/com/coderising/litestruts/struts.xml";
		Document document = null;
		try {
			document = new SAXReader().read(path);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		/*
		 * 1、 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
		 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,
		 * "password"="1234") , 那就应该调用 setName和setPassword方法
		 */
		// 获取根节点
		Element root = document.getRootElement();
		// 遍历根节点下面的节点
		Iterator<?> actionIt = root.elementIterator("action");
		while (actionIt.hasNext()) {
			Element action = (Element) actionIt.next();
			if (action.attribute("name").getValue().equals(actionName)) {
				String className = action.attribute("class").getValue();
				Class<?> clazz = null;
				try {
					// 通过actionName找到className，并得到类
					clazz = Class.forName(className);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				Object clazzObject = null;
				try {
					// 创建类的一个对象
					clazzObject = clazz.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

				// 遍历放参数的map
				Set<String> keySet = parameters.keySet();
				for (String key : keySet) {
					// 拼接set方法的name
					String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
					// 得到set方法对象
					Method setMethod = null;
					try {
						setMethod = clazz.getMethod(methodName, String.class);
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
					try {
						// 调用set方法，参数为parameters中对应的value
						setMethod.invoke(clazzObject, parameters.get(key));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}

				/*
				 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
				 */

				Method executeMethod = null;
				try {
					// 得到exectue方法
					executeMethod = clazz.getMethod("execute");
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				String string = null;
				try {
					// 调用exectue方法
					string = (String) executeMethod.invoke(clazzObject);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

				/*
				 * 3、通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用，
				 * 把值和属性形成一个HashMap , 例如 {"message": "登录成功"} ,
				 * 放到View对象的parameters
				 */

				Map<String, Object> map = new HashMap<>();
				Method[] methods = clazz.getMethods();
				for (Method method : methods) {
					if (method.getName().substring(0, 3).equals("get") && !method.getName().equals("getClass")) {
						Object str = null;
						try {
							// 调用get方法
							str = method.invoke(clazzObject);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
						map.put(method.getName().substring(3).toLowerCase(), str);
					}
				}
				view.setParameters(map);

				/*
				 * 4、根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
				 * 放到View对象的jsp字段中。
				 */
				@SuppressWarnings("unchecked")
				List<Element> elements = action.elements();
				for (Element element : elements) {
					if (element.attribute("name").getValue().equals(string)) {
						String jspName = element.getText();
						view.setJsp(jspName);
					}
				}
			}
		}
		return view;
	}

}

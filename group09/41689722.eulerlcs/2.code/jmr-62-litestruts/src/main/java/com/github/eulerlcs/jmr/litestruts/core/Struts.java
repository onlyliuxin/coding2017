package com.github.eulerlcs.jmr.litestruts.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.github.eulerlcs.jmr.litestruts.degister.StrutsConfig;
import com.github.eulerlcs.jmr.litestruts.degister.StrutsDigester;

/**
 * <ul>
 * <li>读取配置文件struts.xml</li>
 * <li>根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,
 * "password"="1234") , 那就应该调用 setName和setPassword方法</li>
 * <li>通过反射调用对象的exectue 方法， 并获得返回值，例如"success"</li>
 * <li>通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如
 * {"message": "登录成功"} , 放到View对象的parameters</li>
 * <li>根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中。</li>
 * </ul>
 */
public class Struts {
	private final static Logger log = LoggerFactory.getLogger(Struts.class);
	private static StrutsConfig config = null;

	public static View runAction(String actionName, Map<String, String> parameters) {
		/*
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

		// 0.
		getConfig();

		// 1.1.
		Object object = createInstance(actionName);
		if (object == null) {
			return null;
		}
		// 1.2.
		if (!prepareParameters(object, parameters)) {
			return null;
		}
		// 2.
		String viewId = execute(object);
		if (viewId == null) {
			return null;
		}
		// 3.
		View view = biuldView(object);
		if (view == null) {
			return null;
		}

		// 4.
		String uri = config.getActionMap().get(actionName).getResults().get(viewId).getUrl();
		view.setJsp(uri);

		return view;
	}

	private static StrutsConfig getConfig() {
		if (config != null) {
			return config;
		}

		Digester d = StrutsDigester.newInstance();
		try {
			File file = new File("data", "struts.xml");
			config = (StrutsConfig) d.parse(file);
		} catch (IOException | SAXException e) {
			log.error("getConfig", e);
			System.exit(1);
		}

		return config;
	}

	private static Object createInstance(String actionName) {
		if (actionName == null) {
			return null;
		}

		String className = config.getActionMap().get(actionName).getClazz();
		Object object = null;
		try {
			Class<?> clazz = Class.forName(className);
			object = clazz.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			log.error("createInstance", e);
		}

		return object;
	}

	private static boolean prepareParameters(Object object, Map<String, String> parameters) {
		if (parameters == null || parameters.size() == 0) {
			return true;
		}

		Class<?> clazz = object.getClass();
		Method setter = null;

		try {
			for (String key : parameters.keySet()) {
				setter = clazz.getMethod(biuldSetterName(key), String.class);
				setter.setAccessible(true);
				setter.invoke(object, parameters.get(key));
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			log.error("prepareParameters", e);
			return false;
		}

		return true;
	}

	private static String biuldSetterName(String name) {
		String setterName = "set";
		setterName += name.substring(0, 1).toUpperCase() + name.substring(1);
		return setterName;

	}

	private static String debiuldGetterName(String getterName) {
		if (getterName == null || getterName.length() <= 3) {
			return null;
		}
		if (!getterName.substring(0, 3).equals("get")) {
			return null;
		}

		String name = getterName.substring(3, 4).toLowerCase() + getterName.substring(4);
		return name;
	}

	private static String execute(Object object) {
		final String METHOD_EXECUTE = "execute";
		String viewId = null;

		Class<?> clazz = object.getClass();
		try {
			Method method = clazz.getMethod(METHOD_EXECUTE);
			viewId = (String) method.invoke(object);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			log.error("execute", e);
			return viewId;
		}

		return viewId;
	}

	private static View biuldView(Object object) {
		View view = new View();
		Map<String, Object> parameters = new HashMap<>();

		Class<?> clazz = object.getClass();
		Method[] methods;
		try {
			methods = clazz.getMethods();
			for (Method method : methods) {
				String name = debiuldGetterName(method.getName());
				if (name == null) {
					continue;
				}
				Object value = method.invoke(object);
				parameters.put(name, value);
			}

			view.setParameters(parameters);
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			log.error("biuldResult", e);
			return null;
		}

		return view;
	}
}

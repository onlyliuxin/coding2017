package com.camile._2.litestruts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.commons.digester.Digester;
import org.junit.experimental.theories.Theories;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import com.camile._2.litestruts.bean.Action;
import com.camile._2.litestruts.bean.Result;
import com.camile._2.litestruts.bean.Structs;

/**
 * @author Administrator 0. 读取配置文件struts.xml
 * 
 *         1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
 *         据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,
 *         "password"="1234") , 那就应该调用 setName和setPassword方法
 * 
 *         2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
 * 
 *         3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如
 *         {"message": "登录成功"} , 放到View对象的parameters 4. 根据struts.xml中的 <result>
 *         配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中
 */
public class Struts {

	public View runAction(String actionName, Map<String, String> parameters) {

		Structs structs = makeObjFromXml(readProfiles("struts.xml"));
		for (Action a : structs.getActions()) {
			if (a.getName().equals(actionName)) {
				try {
					Class<?> actionClass = Class.forName(a.getClazz());
					Object instance = actionClass.newInstance();
					Field field;
					// get field
					for (Map.Entry<String, String> entry : parameters.entrySet()) {
						field = actionClass.getDeclaredField(entry.getKey());
						field.setAccessible(true);
						field.set(instance, entry.getValue());
					}
					Method execute = actionClass.getMethod("execute");
					String resultString = (String) execute.invoke(instance);
					Method getMessage = actionClass.getMethod("getMessage");
					String message = (String) getMessage.invoke(instance);
					Map<String, String> map = new HashMap<>();
					map.put("message", message);
					View view = new View();
					String viewPath;
					for (Result path : a.getResults()) {
						if (path.getName().equals(resultString)) {
							viewPath = path.getValue();
							view.setParameters(map);
							view.setJsp(viewPath);
						}
					}
					return view;

				} catch (NoSuchFieldException | SecurityException e) {
					System.out.println("获取参数失败");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.println("并没有在xml中找到相关action");
					e.printStackTrace();
				} catch (InstantiationException | IllegalAccessException e) {
					System.out.println("获取反射对象失败");
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					System.out.println("获取方法");
					e.printStackTrace();
				} catch (IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	private URL readProfiles(String filePath) {
		ClassLoader classLoader = Struts.class.getClassLoader();
		URL resource = classLoader.getResource(filePath);
		if (resource == null)
			throw new RuntimeException("文件不存在");
		return resource;
	}

	private Structs makeObjFromXml(URL resource) {
		Digester digester = new Digester();
		digester.addObjectCreate("struts", Structs.class);
		digester.addObjectCreate("struts/action", Action.class);
		digester.addSetProperties("struts/action", new String[] { "name", "class" }, new String[] { "name", "clazz" });
		digester.addSetNext("struts/action", "addAction");
		digester.addObjectCreate("struts/action/result", Result.class);
		digester.addSetProperties("struts/action/result");
		digester.addBeanPropertySetter("struts/action/result", "value");
		digester.addSetNext("struts/action/result", "addResult");
		try {
			return (Structs) digester.parse(resource);
		} catch (IOException | SAXException e) {
			e.printStackTrace();
			throw new RuntimeException("解析XML文件时发生错误");
		}
	}

	public static void main(String[] args) {

	}
}

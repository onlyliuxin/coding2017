package com.coderising.action;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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
		try {
			SAXReader reader = new SAXReader();
			InputStream in = Struts.class.getResourceAsStream("struts.xml");
			Document document = reader.read(in);
			Element root = document.getRootElement();
			System.out.println(root);
			System.out.println(document);

			// 与actionName匹配的Element
			Element actionElement = null;
			String className = null;

			for (Iterator<Element> iterator = root.elementIterator("action"); iterator.hasNext();) {
				Element e = iterator.next();
				if (e.attributeValue("name").equals(actionName)) {
					actionElement = e;
					className = e.attributeValue("class");
					break;
				}
			}
			Class<?> clazz = Class.forName(className);
			Object instance = clazz.newInstance();
			Set<String> keySet = parameters.keySet();
			for (String key : keySet) {
				String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
				Class<?> type = clazz.getDeclaredField(key).getType();
				Method method = clazz.getDeclaredMethod(methodName, type);
				// 依次调用对应的set方法
				method.invoke(instance, parameters.get(key));
			}
			String result = (String) clazz.getDeclaredMethod("execute").invoke(instance);
			// 反射调用getter方法
            Method[] methods =clazz.getDeclaredMethods();
            Map<String,Object> param  = new HashMap<>();
			for (Method method : methods) {
				 String methodname = method.getName();
				 if(method.getName().startsWith("get")){
					 String fieldName = methodname.substring(3, 4).toLowerCase() + methodname.substring(4);
					 Object fieldValue = method.invoke(instance);
					 param.put(fieldName, fieldValue);
				 }
			}
			View view  = new View();
			view.setParameters(param);
			for (Iterator<Element> iterator = actionElement.elementIterator("result"); iterator.hasNext();) {
				Element resultElement = iterator.next();
				if (resultElement.attributeValue("name").equals(result)) {
					view.setJsp(resultElement.getText());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		Map map = new HashMap<>();
		map.put("name", "test");
		map.put("password", "1234");
		Struts.runAction("login", map);

	}
}

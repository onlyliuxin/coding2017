package com.coderising.action;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

	public static View runAction(String actionName, Map<String, String> parameters) {
		SAXReader saxReader = new SAXReader();
		Document document;
		View view = new View();
		Map<String, String> resultMap = new HashMap<>();
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}
		try {
			document = saxReader.read(new File("src/com/coderising/action/struts.xml"));
			Element root = document.getRootElement();
			@SuppressWarnings("unchecked")
			Iterator<Element> it = root.elementIterator();
			// 遍历根节点的子节点
			while (it.hasNext()) {
				Element e = it.next();
				// 判断action的Name是否和传入的actionName相同
				if (actionName.equals(e.attribute("name").getData())) {
					// 根据反射创建对象实例
					String classPath = e.attribute("class").getData().toString();
					Class<?> class1 = Class.forName(classPath);
					LoginAction loginAction = (LoginAction) class1.newInstance();
					Field[] fields = class1.getDeclaredFields();
					// 对于实例的成员变量进行遍历，并判断是否和Map中提供的参数是否匹配，如果匹配的话就调用其set方法。
					for (Field field : fields) {
						String fieldName = field.getName();
						for (Map.Entry<String, String> entry : parameters.entrySet()) {
							if (entry.getKey().equals(fieldName)) {
								Method method = class1.getDeclaredMethod(
										"set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1),
										String.class);
								method.invoke(loginAction, entry.getValue());
							}
							continue;
						}
					}
					// 执行execute方法并获取其返回值
					Method method = class1.getDeclaredMethod("execute");
					String result = (String) method.invoke(loginAction);
					
					String jsp = null;
					for (@SuppressWarnings("unchecked")
					Iterator<Element> iterator = e.elementIterator(); iterator.hasNext();) {
						Element element = iterator.next();
						if (result.equals(element.attribute("name").getData())) {
							jsp = element.getText();
						}
					}

					for (Field field : fields) {
						String fieldName = field.getName();
						Method method2 = class1.getDeclaredMethod(
								"get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
						resultMap.put(fieldName, (String) method2.invoke(loginAction));
					}

					view.setParameters(resultMap);
					view.setJsp(jsp);
				}
				continue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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

		return view;
	}

}
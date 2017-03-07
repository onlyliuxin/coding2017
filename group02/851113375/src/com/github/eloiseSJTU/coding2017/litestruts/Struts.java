package com.github.eloiseSJTU.coding2017.litestruts;

import java.beans.PropertyDescriptor;
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
		View view = new View();
		SAXReader saxReader = new SAXReader();
		try {
			// 0. 读取配置文件struts.xml
			Document document = saxReader.read(new File(getPackagePath() + "struts.xml"));
			Element struts = document.getRootElement();
			for (Iterator<?> i = struts.elementIterator("action"); i.hasNext();) {
				Element action = (Element) i.next();
				// 1. 根据actionName找到相对应的class，例如LoginAction，通过反射实例化（创建对象）
				if (actionName.equals(action.attributeValue("name"))) {
					String className = action.attributeValue("class");
					Class<?> clazz = Class.forName(className);
					Object object = clazz.newInstance();
					// 根据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
					// ("name"="test", "password"="1234")，那就应该调用
					// setName和setPassword方法
					for (Map.Entry<String, String> entry : parameters.entrySet()) {
						PropertyDescriptor descriptor = new PropertyDescriptor(entry.getKey(), clazz);
						Method method = descriptor.getWriteMethod();
						method.invoke(object, entry.getValue());
					}
					// 2. 通过反射调用对象的execute方法，并获得返回值
					Method excute = clazz.getMethod("execute");
					String result = (String) excute.invoke(object);
					// 3. 通过反射找到对象的所有getter方法（例如 getMessage），通过反射来调用，
					// 把值和属性形成一个HashMap，例如{"message": "登录成功"}，
					// 放到View对象的parameters
					Map<String, Object> map = new HashMap<>();
					Field[] fields = clazz.getDeclaredFields();
					for (Field field : fields) {
						PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), clazz);
						Method method = descriptor.getReadMethod();
						Object value = method.invoke(object);
						map.put(field.getName(), value);
					}
					view.setParameters(map);
					// 4. 根据struts.xml中的 <result>配置,以及execute的返回值，
					// 确定哪一个jsp放到View对象的jsp字段中。
					for (Iterator<?> j = action.elementIterator("result"); j.hasNext();) {
						Element element = (Element) j.next();
						if (result.equals(element.attributeValue("name"))) {
							view.setJsp(element.getText());
							break;
						}
					}
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return view;
	}

	private static String getPackagePath() {
		String path = Struts.class.getResource("").toString().replace("/", File.separator);
		if (path.startsWith("file")) {
			path = path.substring(5);
		}
		return path;
	}

}

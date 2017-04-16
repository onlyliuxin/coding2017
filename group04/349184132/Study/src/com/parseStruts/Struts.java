package com.parseStruts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

	@SuppressWarnings("unchecked")
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

		View view = new View();
		try {
			// 读取struts.xml 使用dom4j
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(
					"src/com/second/struts.xml"));

			String classpath = null;
			Element actionEle = null; // 存放actionName对应的action元素
			// 获取根节点
			Element root = document.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element action = it.next();
				if (actionName.equals(action.attributeValue("name"))) {
					actionEle = action;
					classpath = action.attributeValue("class");

				}
			}

			Class<?> clazz = Class.forName(classpath);

			Object o = clazz.newInstance();

			Set<String> set = parameters.keySet();

			for (Iterator<String> name = set.iterator(); name.hasNext();) {
				String para = name.next();

				Method m = clazz.getDeclaredMethod(
						StringUtil.nameTosetName(para), String.class);

				m.invoke(o, parameters.get(para));
			}

			// 调用execute
			Method exectue = clazz.getDeclaredMethod("execute", null);
			String exResult = (String) exectue.invoke(o, null);

			// 获取所有方法
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				// 正则过滤 获取getter方法
				if (methodName.matches("get[a-zA-Z]+")) {

					// 字符串处理 getName --> name
					parameters.put(StringUtil.getNameToName(methodName),
							(String) method.invoke(o, null));

				}
			}

			for (Iterator<Element> iter = actionEle.elementIterator("result"); iter
					.hasNext();) {
				Element result = iter.next();
				String name = result.attributeValue("name");
				if (name.equals(exResult)) {
					String jsp = result.getText();
					view.setJsp(jsp); // 放入View jsp字段中
				}
			}
			view.setParameters(parameters);

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return view;

	}

}

package com.coderising.litestruts;

//import java.awt.List;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//import javax.print.attribute.standard.Media;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

	@SuppressWarnings("unchecked")
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
		Map<String, String> map = new HashMap<String, String>();
		view.setParameters(map);
		try {

			SAXReader reader = new SAXReader();
			String dir = System.getProperty("user.dir");

			Document document = reader.read(new File(dir + "/src/com/coderising/litestruts/struts.xml"));
			Element struts = document.getRootElement();
			java.util.List<Element> list_action = struts.elements("action");

			Element item = null;
			for (int i = 0; i < list_action.size(); i++) {
				item = list_action.get(i);
				String nm = item.attributeValue("name");
				if (actionName.equals(nm)) {
					break;
				}
			}
			String str_class = item.attributeValue("class");
			// String real_class=dir+"/"+str_class.replace('.', '/');
			// Class<?> cl = Class.forName( dir.replace('\\',
			// '.')+".src."+str_class);
			Class<?> cl = Class.forName(str_class);
			Object instance = cl.newInstance();

			String dNmae = parameters.get("name");
			String dpassword = parameters.get("password");
			Method mName = cl.getMethod("setName", String.class);
			Method mPassword = cl.getMethod("setPassword", String.class);
			mName.invoke(instance, dNmae);
			mPassword.invoke(instance, dpassword);

			Method mExectue = cl.getMethod("execute");
			Object result = mExectue.invoke(instance);

			Method[] methods = cl.getMethods();
			for (Method method : methods) {
				if (isGetter(method)) {
					String mGettername = method.getName().substring(3);
					Object mResult = method.invoke(instance);
					view.getParameters().put(mGettername.toLowerCase(), mResult);
				}
			}

			java.util.List<Element> resulList = item.elements();
			for (Element el : resulList) {
				if (result.toString().equals(el.attributeValue("name"))) {
					view.setJsp(el.getTextTrim());
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	// 判断是否getter方法
	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}

}

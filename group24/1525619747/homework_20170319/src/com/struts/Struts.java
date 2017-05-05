package com.struts;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;

public class Struts
{

	public static View runAction(String actionName,
			Map<String, String> parameters) throws DocumentException,
			ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchFieldException {

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

		DomXmlHelper dx = new DomXmlHelper();
		String action = "login";
		String className = dx.getActionClassByName(action);
		// System.out.println(className);

		Class<?> class1 = null;
		class1 = Class.forName(className);
		// System.out.println("类名称   " + class1.getName());

		if (class1 != null) {
			// 调用class1的setName方法, 待参数
			// 根据.class反射出来的类实例
			Object instance = class1.newInstance();

			Method method = class1.getMethod("setName", String.class);
			Object res1 = method.invoke(instance, "test");

			method = class1.getMethod("setPassword", String.class);
			Object res2 = method.invoke(instance, "1234");

			// set attr
			for (Map.Entry<String, String> entity : parameters.entrySet()) {
				String attrName = entity.getKey();
				String attrValue = entity.getValue();

				Field idF = class1.getDeclaredField(attrName); // 获取属性
				idF.setAccessible(true); // 使用反射机制可以打破封装性，导致了java对象的属性不安全。
				idF.set(instance, attrValue); // set
			}

			View view = new View();

			method = class1.getMethod("execute");
			Object res3 = method.invoke(instance);
			// System.out.println(res3);
			String jsp = dx.getActionView(action, res3.toString());
			view.setJsp(jsp);

			method = class1.getMethod("getMessage");
			Object res4 = method.invoke(instance);
			// System.out.println(res4);

			Map<String, String> map = new HashMap<String, String>();
			map.put("message", res4.toString());

			view.setParameters(map);

			return view;
		}

		return null;
	}

}

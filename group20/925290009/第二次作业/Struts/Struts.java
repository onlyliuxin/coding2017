package com.coderising.litestruts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Struts {

	private static final String NAME = "name";
	private static final String PASSWORD = "password";
	private static String excuteString;
	private static Object object = null;// 返回反射的实例
	private static Class<?> actionClass = null;// 反射获取的类

	@SuppressWarnings("unchecked")
	public static View runAction(String actionName,
			Map<String, String> parameters) {
		// 读取配置文件struts.xml
		View view = new View();
		ReadXml readXml = new ReadXml("E:\\struts.xml");
		String classNameString = readXml.parseXml(actionName);//读取xml
		object = initAction(classNameString);//通过反射初始化类
		
		excuteMethod(parameters);//执行setter和excute方法
		
		view.setParameterMap(setMapParameter());//获取所有的getter方法，执行后将方法名和结果保存到view中
		String jspResult = readXml.getJsp(excuteString);//获取jsp类型
		view.setJsp(jspResult);
		
		return view;
	}

	public static Object initAction(String classNameString) {
		System.out.println(classNameString);
		try {
			actionClass = Class.forName(classNameString);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object newObject = null;
		try {
			newObject = actionClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newObject;
	}

	public static void excuteMethod(Map<String, String> parameters) {

		try {
			Method methodOfName = actionClass
					.getMethod("setName", String.class);
			methodOfName.invoke(object, parameters.get(NAME));
			//
			Method methodOfPassword = actionClass.getMethod("setPassWord",
					String.class);
			methodOfPassword.invoke(object, parameters.get(PASSWORD));

			Method excuteMethod = actionClass.getMethod("execute");
			excuteString = (String) excuteMethod.invoke(object);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static Map<String, String> setMapParameter() {
		
		Method[] getterMethods = actionClass.getMethods();
		HashMap<String, String> hashMap = new HashMap<>();

		for (int i = 0; i < getterMethods.length; i++) {
			String getterName = getterMethods[i].getName();
			if (getterName.startsWith("get")) {
				try {
					String value = (String) getterMethods[i].invoke(object);
					hashMap.put(getterName.substring(3).toLowerCase(), value);
					//System.out.println("----" + getterName.substring(2));
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}
		return hashMap;
	}
}

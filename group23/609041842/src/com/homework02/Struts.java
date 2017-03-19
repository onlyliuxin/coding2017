package com.homework02;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.junit.Test;

public class Struts {

	private static final String ACTIN_NOT_FOUNT = "未找到Action";
	private static final String RESULT_NOT_FOUNT = "未找到Result";

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
			String name = parameters.get("name");
			String password = parameters.get("password");
			String absoluteActionClassPath = getAbsoluteActionClassPath(actionName);
			Class clazz;
			clazz = Class.forName(absoluteActionClassPath);

			Object obj = clazz.newInstance();
			String setName = "name";
			String setPwd = "password";
			String prefix = "set";
			String setterName = prefix + setName.substring(0, 1).toUpperCase() + setName.substring(1, setName.length());
			String setterPwd = prefix + setPwd.substring(0, 1).toUpperCase() + setPwd.substring(1, setPwd.length());
			Method mnd = clazz.getMethod(setterName, String.class);
			Method mtd = clazz.getMethod(setterPwd, String.class);
			mnd.invoke(obj, name);
			mtd.invoke(obj, password);
			// 调取execute获取返回值
			Method md = clazz.getMethod("execute");
			String mdResult = md.invoke(obj).toString();
			Method getMessageMethod = clazz.getMethod("getMessage");
			Object getMessage = getMessageMethod.invoke(obj);
			String message = "";
			if (null != getMessage) {
				message = getMessage.toString();
			} else {
				System.out.println("messge为空");
			}
			// 获取xml中的result值
			String result = getResultValue(actionName, mdResult);
			View view = new View();
			System.out.println(result);
			view.setJsp(result);
			parameters.put("message", message);
			view.setParameters(parameters);
			System.out.println("打印view结果-" + view.toString());
			return view;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 获取请求的actionName所在类绝对路径
	 * 
	 * @param actionName
	 * @return
	 * @throws DocumentException
	 */
	public static String getAbsoluteActionClassPath(String actionName) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read("src/struts.xml");
		List<DefaultElement> getActions = doc.selectNodes("/struts/action[@name='" + actionName + "']");
		for (DefaultElement e : getActions) {
			return e.attributeValue("class");
		}
		return ACTIN_NOT_FOUNT;
	}

	@Test
	public void test() {
		try {
			System.out.println(getAbsoluteActionClassPath("login"));
			// System.out.println(getResultValue("login", "success"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getResultValue(String actionName, String param) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read("src/struts.xml");
		String path = "/struts/action[@name='" + actionName + "']/result[@name='" + param + "']";
		String result = doc.selectSingleNode(path).getText().toString();
		if (null == result) {
			return RESULT_NOT_FOUNT;
		}
		return result;
	}

}

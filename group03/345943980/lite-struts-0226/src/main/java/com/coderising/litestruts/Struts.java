package com.coderising.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {


	@SuppressWarnings("unchecked")
	public static View runAction(String actionName, Map<String, String> parameters) {

		/*
		 * 
		 * 0. 读取配置文件struts.xml
		 * 
		 * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象） 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
		 * ("name"="test" , "password"="1234") , 那就应该调用 setName和setPassword方法
		 * 
		 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		 * 
		 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message": "登录成功"} ,
		 * 放到View对象的parameters
		 * 
		 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中。
		 */
		View view = new View();
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		try {
			Object obj = null;
			Document document = reader.read(Struts.class.getResourceAsStream("/struts.xml"));
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			Class<?> clz = null;
			for (Element element : elements) {
				if (element.attributeValue("name").trim().equalsIgnoreCase(actionName)) {
					String classStr = element.attributeValue("class");
					clz = Class.forName(classStr);
					obj = clz.newInstance();
					for (Map.Entry<String, String> entry : parameters.entrySet()) {
						String key = entry.getKey();
						String setMethod = "set" + key.substring(0, 1).toUpperCase()
								+ key.substring(1);
						Method method = clz.getDeclaredMethod(setMethod, String.class);
						method.invoke(obj, entry.getValue());
					}
					List<Element> listElement = element.elements("result");
					for (Element subElement : listElement) {
						map.put(subElement.attribute("name").getText(), subElement.getTextTrim());
					}
				}
			}
			Method execMethod = clz.getDeclaredMethod("execute");
			String result = execMethod.invoke(obj).toString();
			Method msgMethod = clz.getDeclaredMethod("getMssage");
			String message = msgMethod.invoke(obj).toString();
			Map<String, String> msgs = new HashMap<String, String>();
			msgs.put("message", message);
			view.setJsp(map.get(result));
			view.setParameters(msgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}

	public static void main(String[] args) {
		// Digester digester = new Digester();
		// // 指定它不要用DTD验证XML文档的合法性——这是因为我们没有为XML文档定义DTD
		// digester.setValidating(false);
		// digester.addObjectCreate("struts", Struts.class);
		// digester.addObjectCreate("struts/action", LoginAction.class);
		// digester.addBeanPropertySetter("struts/action/name");
		// //digester.addBeanPropertySetter("struts/action/class");
		// digester.addObjectCreate("struts/action/result", Result.class);
		// digester.addBeanPropertySetter("struts/action/result/name");
		// digester.addBeanPropertySetter("struts/action/result/value");
		// Struts struts = null;
		// try {
		// struts = (Struts)digester.parse(Struts.class.getResourceAsStream("/struts.xml"));
		// System.out.println(struts.actions.get(0).getName());
		// // /System.out.println(struts.actions.get(0).getActionClass());
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (SAXException e) {
		// e.printStackTrace();
		// }
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(Struts.class.getResourceAsStream("/struts.xml"));
			Element root = document.getRootElement();
			// 获取某个节点的子节点
			Element element = root.element("action");
			String classStr = element.attributeValue("class");
			Class<?> clz = Class.forName(classStr);
			Object obj = clz.newInstance();
			Method medName = clz.getDeclaredMethod("setName", String.class);
			medName.invoke(obj, "test");
			Method medPwd = clz.getDeclaredMethod("setPassword", String.class);
			medPwd.invoke(obj, "123456");

			Method medGetName = clz.getDeclaredMethod("getName");
			String username = medGetName.invoke(obj).toString();
			Method medGetPwd = clz.getDeclaredMethod("getPassword");
			String password = medGetPwd.invoke(obj).toString();
			System.out.println(username + "\t" + password);

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

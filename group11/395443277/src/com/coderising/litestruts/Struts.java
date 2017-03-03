package com.coderising.litestruts;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

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

		// create a new DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			// use the factory to create a documentbuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream istream = Struts.class.getResourceAsStream("struts.xml");
			Document doc = builder.parse(istream);

			// find jsp page based on the result from execute result
			Element element = doc.getDocumentElement();
			NodeList actionNodeList = element.getElementsByTagName("action");

			for (int i = 0; i < actionNodeList.getLength(); i++) {
				NamedNodeMap actionNodeAttr = actionNodeList.item(i).getAttributes();

				if (actionNodeAttr.getNamedItem("name").getTextContent().equals(actionName)) {
					String className = actionNodeAttr.getNamedItem("class").getTextContent();

					Class<?> cls = Class.forName(className);
					Object obj = cls.newInstance();

					// set name and password
					Method setName = cls.getDeclaredMethod("setName", String.class);
					Method setPassword = cls.getDeclaredMethod("setPassword", String.class);
					setName.invoke(obj, parameters.get("name"));
					setPassword.invoke(obj, parameters.get("password"));

					// execute
					Method execute = cls.getDeclaredMethod("execute");
					String executeResult = (String) execute.invoke(obj);

					// get message and jsp
					Method getMessage = cls.getDeclaredMethod("getMessage");
					String msg = (String) getMessage.invoke(obj);
					Map<String,String> params = new HashMap<String,String>();
			        params.put("message",msg);

					// check result nodes
					NodeList resultNodes = actionNodeList.item(i).getChildNodes();
					
					String jsp = "";
					for (int j=0; j<resultNodes.getLength(); j++) {
						NamedNodeMap resultNodeAttr = resultNodes.item(j).getAttributes();
						if (resultNodeAttr != null) {
							if (resultNodeAttr.getNamedItem("name").getTextContent().equals(executeResult)) {
								jsp = resultNodes.item(j).getTextContent();
								break;
							}
						}
					}
					
					// enter information into view
					Class<?> viewCls = Class.forName("com.coderising.litestruts.View");
					View viewObj = (View) viewCls.newInstance();
					Method setParameters = viewCls.getDeclaredMethod("setParameters", Map.class);
					setParameters.invoke(viewObj, params);
					Method setJsp = viewCls.getDeclaredMethod("setJsp", String.class);
					setJsp.invoke(viewObj, jsp);
					
					return viewObj;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}

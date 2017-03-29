package com.coderising.litestruts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Struts {

	public static View runAction(String actionName,
			Map<String, String> parameters) throws Exception {
		View view = new View();
		// InputStream inputStream = new FileInputStream(new
		// File("D:/git/coding2017/group03/619224754/src/com/coderising/litestruts/struts.xml"));
		SAXReader saxReader = new SAXReader();
		Document document = saxReader
				.read(new File(
						"D:/git/coding2017/group03/619224754/src/com/coderising/litestruts/struts.xml"));

		Element rootElement = document.getRootElement();
		List<Node> lstAction = rootElement.selectNodes("action");
		Iterator it = lstAction.iterator();
		while (it.hasNext()) {
			Element actionElement = (Element) it.next();
			String name = actionElement.attributeValue("name");
			if (name.equals(actionName)) {
				String actionClass = actionElement.attributeValue("class");
				Class classType = Class.forName(actionClass);
				Object obj = classType.newInstance();
				for (String key : parameters.keySet()) {
					String value = parameters.get(key);
					String strMethod = getFiledSetMethod(key);
					Method setMethod = classType.getMethod(strMethod, String.class);
					setMethod.invoke(obj, value);
				}
				Method excuteMethod = classType.getMethod("execute");
				Object retValue = excuteMethod.invoke(obj);
				Node resultNode = actionElement
						.selectSingleNode("result[@name='" + retValue.toString() + "']");
				view.setJsp(resultNode.getText());
				Map<String, String> result = new HashMap<String, String> ();
				
				Method msessageMethod = classType.getMethod("getMessage");
				Object message = msessageMethod.invoke(obj);
				result.put("message", message.toString());
				view.setParameters(result);
			}
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
		 */

		return view;
	}

	public static String getFiledSetMethod(String filedName) {
		String methodName = "";
		methodName = "set" + filedName.toUpperCase().substring(0, 1)
				+ filedName.substring(1);
		return methodName;
	}
}
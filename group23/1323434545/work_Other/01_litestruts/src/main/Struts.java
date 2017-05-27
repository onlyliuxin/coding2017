package main;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {
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
	 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中。
	 * 
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static View runAction(String actionName, Map<String, String> parameters)
			throws DocumentException, ReflectiveOperationException, SecurityException {
		View view = null;
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("./xml/struts.xml"));
		Element root = document.getRootElement();
		for (Iterator iterator = (Iterator) root.elementIterator("action"); iterator.hasNext();) {
			Element actionElement = (Element) iterator.next();
			String actionElementName = actionElement.attributeValue("name");
			if (actionElementName.equals(actionName)) {
				Class c = Class.forName(actionElement.attributeValue("class"));
				Constructor con = c.getConstructor();
				Object o = con.newInstance();
				Method setName = c.getMethod("setName", String.class);
				setName.invoke(o, parameters.get("name"));
				Method setPassword = c.getMethod("setPassword", String.class);
				setPassword.invoke(o, parameters.get("password"));
				Method execute = c.getMethod("execute");
				String resultStr = execute.invoke(o).toString();

				for (iterator = actionElement.elementIterator("result"); iterator.hasNext();) {
					Element resultElement = (Element) iterator.next();
					if (resultElement.attributeValue("name").equals(resultStr)) {
						view = new View();
						parameters.put("name", c.getMethod("getName").invoke(o).toString());
						parameters.put("password", c.getMethod("getPassword").invoke(o).toString());
						parameters.put("message", c.getMethod("getMessage").invoke(o).toString());
						view.setJsp(resultElement.getStringValue());
						view.setParameters(parameters);
					}
				}
			}

		}
		return view;

	}

}
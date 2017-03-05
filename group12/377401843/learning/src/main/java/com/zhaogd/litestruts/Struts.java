package com.zhaogd.litestruts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

	@SuppressWarnings("unchecked")
	public static View runAction(String actionName, Map<String, String> parameters) throws Exception {

		SAXReader reader = new SAXReader();
		Document document = reader.read(Struts.class.getResourceAsStream("/struts.xml"));
		Element rootElement = document.getRootElement();
		List<Element> elements = rootElement.elements("action");

		HashMap<String, String> hashMap = new HashMap<String, String>();
		for (Element element : elements) {
			String name = element.attributeValue("name");
			if (name != null && !"".equals(name) && actionName.equals(name)) {
				hashMap.put("actionName", name);
				hashMap.put("class", element.attributeValue("class"));
				List<Element> resultElements = element.elements("result");
				for (Element resultElement : resultElements) {
					hashMap.put(resultElement.attributeValue("name"), (String) resultElement.getData());
				}
				break;
			}
		}

		Class<?> c = Class.forName(hashMap.get("class"));
		Object o = c.newInstance();
		Set<Entry<String, String>> entrySet = parameters.entrySet();
		for (Entry<String, String> parameter : entrySet) {
			Method method = o.getClass().getMethod("set" + parameter.getKey().substring(0, 1).toUpperCase()
					+ parameter.getKey().substring(1).toLowerCase(), String.class);
			method.invoke(o, parameter.getValue());
		}

		Method exectue = o.getClass().getMethod("execute");
		String invoke = (String) exectue.invoke(o);

		View view = new View();
		view.setJsp(hashMap.get(invoke));
		HashMap<String, String> p = new HashMap<String, String>();
		p.put("message", (String) o.getClass().getMethod("getMessage").invoke(o));
		view.setParameters(p);

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

		return view;
	}

}

package com.coderising.litestruts;

/**
 * @author patchouli
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {
	private static final String ELEMENT_ACTION = "action";
	private static final String ELEMENT_RESULT = "result";

	/*
	 * 
	 * 0. 读取配置文件struts.xml
	 * 
	 * 1. 根据actionName找到相对应的class ， 例如LoginAction,通过反射实例化（创建对象）
	 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
	 * ("name"="test","password"="1234"), 那就应该调用 setName和setPassword方法
	 * 
	 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
	 * 
	 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如
	 * {"message":"登录成功"} , 放到View对象的parameters
	 * 
	 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中。
	 * 
	 */
	public static View runAction(String actionName, Map<String, String> parameters)
			throws FileNotFoundException, URISyntaxException, DocumentException, ClassNotFoundException {
		SAXReader reader = new SAXReader();
		URL url = ClassLoader.getSystemResource("struts.xml");
		File file = new File(url.toURI());
		if (!file.exists()) {
			throw new FileNotFoundException("struts.xml");
		}
		Document document = reader.read(file);
		Element strutsElement = document.getRootElement();
		Map<String, String> viewParameters = new HashMap<>();
		String jsp = "";
		String result = "";
		for (Element actionElement : (List<Element>) strutsElement.elements(ELEMENT_ACTION)) {
			String name = actionElement.attribute("name").getText();
			if (name.equals(actionName)) {
				String className = actionElement.attribute("class").getText();
				Class cls = Class.forName(className);
				Object actionObject = null;
				try {
					actionObject = cls.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (Map.Entry<String, String> entry : parameters.entrySet()) {

					try {
						String str = entry.getKey();
						str = str.replace(str.substring(0, 1), str.substring(0, 1).toUpperCase());
						Method method = cls.getDeclaredMethod("set" + str, String.class);
						try {
							method.invoke(actionObject, entry.getValue());
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (NoSuchMethodException | SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					Method method = cls.getDeclaredMethod("execute");
					try {
						result = (String) method.invoke(actionObject);
						Method[] methods = cls.getDeclaredMethods();
						for (int i = 0; i < methods.length; i++) {
							String methodName = methods[i].getName();
							if (methodName.startsWith("get")) {
								String value = (String) methods[i].invoke(actionObject);
								String key = methodName.substring(3);
								key = key.replace(key.substring(0, 1), key.substring(0, 1).toLowerCase());
								viewParameters.put(key, value);
							}
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (Element resultElement : (List<Element>) actionElement.elements(ELEMENT_RESULT)) {
					String resultName = resultElement.attribute("name").getText();
					if (resultName.equals(result)) {
						jsp = resultElement.getText();
					}
				}
			}
		}
		View view = new View();
		view.setJsp(jsp);
		view.setParameters(viewParameters);
		return view;
	}
}

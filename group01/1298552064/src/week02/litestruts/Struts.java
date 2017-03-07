package week02.litestruts;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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

		try {

			// 0. 读取配置文件struts.xml
			SAXReader reader = new SAXReader();
			InputStream in = Struts.class.getResourceAsStream("struts.xml");
			Document document = reader.read(in);
			Element root = document.getRootElement();

			// 与actionName匹配的Element
			Element actionElement = null;
			String className = null;

			for (Iterator<Element> iterator = root.elementIterator("action"); iterator.hasNext();) {
				Element e = iterator.next();
				if (e.attributeValue("name").equals(actionName)) {
					actionElement = e;
					className = e.attributeValue("class");
					break;
				}
			}

			Class<?> clazz = Class.forName(className);
			Object action = clazz.newInstance();

			// 1. 反射设置属性
			if (parameters != null) {
				for (Map.Entry<String, String> entry : parameters.entrySet()) {
					String fieldName = entry.getKey();
					String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Class<?> fieldType = clazz.getDeclaredField(fieldName).getType();
					Method method = clazz.getDeclaredMethod(methodName, fieldType);
					method.invoke(action, entry.getValue());
				}
			}

			// 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
			Method execute = clazz.getDeclaredMethod("execute");
			String result = (String) execute.invoke(action);

			// 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap
			Method[] methods = clazz.getDeclaredMethods();
			Map<String, Object> param = new HashMap<String, Object>();
			for (Method method : methods) {
				String methodName = method.getName();
				if (method.getName().startsWith("get")) {
					String fieldName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
					Object fieldValue = method.invoke(action);
					param.put(fieldName, fieldValue);
				}
			}

			View view = new View();
			view.setParameters(param);

			// 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
			// 放到View对象的jsp字段中。
			for (Iterator<Element> iterator = actionElement.elementIterator("result"); iterator.hasNext();) {
				Element resultElement = iterator.next();
				if (resultElement.attributeValue("name").equals(result)) {
					view.setJsp(resultElement.getText());
					break;
				}
			}

			return view;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

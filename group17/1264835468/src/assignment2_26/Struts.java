package assignment2_26;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Struts {

	private static File configFile = new File("./src/struts.xml");;

	public static View runAction(String actionName, Map<String, String> parameters) {

		/*
		 
		0. 读取配置文件struts.xml
		
		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
		
		*/
		// 0
		XmlPhraser phraser = new XmlPhraser(configFile);
		String className = phraser.getClassNameByActionName(actionName);
		View view = new View();
		try {
			// 1
			Class<?> actionClass = Class.forName(className);
			Constructor<?> constructor = actionClass.getConstructor();
			Object instance = constructor.newInstance();
			invokeSetters(actionClass, instance, parameters);

			// 2
			String result = invokeExecute(actionClass, instance);

			// 3
			Map<String, String> getterResult = invokeGetters(actionClass, instance);
			view.setParameters(getterResult);

			// 4
			String resultJsp = phraser.getResultJsp(actionName, result);
			view.setJsp(resultJsp);

		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return view;
	}

	private static Map<String, String> invokeGetters(Class<?> actionClass, Object instance)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, String> result = new HashMap<>();
		for (Method method : actionClass.getDeclaredMethods()) {
			if (method.getName().matches("get.*")) {
				String fieldName = method.getName().substring(3).toLowerCase();
				String value = (String) (method.invoke(instance));
				result.put(fieldName, value);
			}
		}
		return result;
	}

	private static String invokeExecute(Class<?> actionClass, Object instance) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = actionClass.getDeclaredMethod("execute");
		return (String) method.invoke(instance);
	}

	private static void invokeSetters(Class<?> actionClass, Object instance, Map<String, String> parameters)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		for (String fieldName : parameters.keySet()) {
			invokeSetter(actionClass, instance, fieldName, parameters.get(fieldName));
		}
	}

	private static void invokeSetter(Class<?> actionClass, Object instance, String fieldName, String value)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
		Method setter = actionClass.getDeclaredMethod(setterName, String.class);
		setter.invoke(instance, value);
	}
}

package coding.week02;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Struts {

	private static final String NAME = "name";
	private static final String PASSWORD = "password";
	private static String excuteString;
	private static Object object = null;
	private static Class<?> actionClass = null;

	
	

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
	@SuppressWarnings("unchecked")
	public static View runAction(String actionName,
			Map<String, String> parameters) {

		View view = new View();
		ReadXml readXml = new ReadXml("E:\\struts.xml");
		String classNameString = readXml.parseXml(actionName);
		object = initAction(classNameString);
		
		excuteMethod(parameters);
		
		view.setParameterMap(setMapParameter());
		String jspResult = readXml.getJsp(excuteString);
		view.setJsp(jspResult);
		
		return view;
	}

	public static Object initAction(String classNameString) {
//		System.out.println(classNameString);
		try {
			actionClass = Class.forName(classNameString);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Object newObject = null;
		try {
			newObject = actionClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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
				} catch (Exception e) {
				}

			}
		}
		return hashMap;
	}
}

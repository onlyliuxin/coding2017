package com.coding.week2.litestruts;

import org.dom4j.DocumentException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Struts {


    public static View runAction(String actionName, Map<String,String> parameters) {

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
		View view = new View();
		try {
			//0
			//得到action类名
			String actionClassName = StrutsXmlUtil.getActionClassName(actionName);

			//得到action的结果及jsp路径
			Map<String,String> jspMap = StrutsXmlUtil.getResultOfAction(actionName);

			//加载类
			Class clazz = Class.forName(actionClassName);
			//实例化对象
			Object obj = clazz.newInstance();
			Method[] methods = clazz.getDeclaredMethods();

			//1 注入action实例变量
			setFields(obj, methods, parameters);

			//2 执行action
			String result = excute(obj, methods);


			//3 获取返回的参数
			Map<String,Object> viewParams = buildReturnParams(obj, methods);
			view.setParameters(viewParams);

			//4 设置返回的jsp
			String jsp = jspMap.get(result);
			view.setJsp(jsp);

		} catch (DocumentException | ClassNotFoundException
				| InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return view;
    }

	private static String excute(Object obj, Method[] methods )
			throws InvocationTargetException, IllegalAccessException {
		for(Method method : methods){
			String methodName = method.getName();
			if("execute".equals(methodName)){
				return  (String) method.invoke(obj);
			}
		}
		return "";
	}


	private static void setFields(Object obj, Method[] methods, Map<String,String> parameters)
			throws InvocationTargetException, IllegalAccessException {
		Set<Map.Entry<String, String>> entities = parameters.entrySet();
		for(Map.Entry<String, String> entry : entities){
			for(Method method : methods){
				if(method.getName().equals("set" + upperFirstChar(entry.getKey()))){
					method.invoke(obj, entry.getValue());
				}
			}
		}
	}

	private static Map<String, Object> buildReturnParams(Object obj, Method[] methods) throws InvocationTargetException, IllegalAccessException {
		Map<String,Object> viewParams = new HashMap<>();
		for(Method method : methods){
			String methodName = method.getName();
			if(methodName.startsWith("get")){
				Object ret = method.invoke(obj);
				String field = methodName.substring(3);
				viewParams.put(lowerFirstChar(field), ret);
			}
		}
		return viewParams;
	}
	private static String upperFirstChar(String str){
		char[] cs = str.toCharArray();
		cs[0] -= 32;
		return new String(cs);
	}

	private static String lowerFirstChar(String str){
		char[] cs = str.toCharArray();
		cs[0] += 32;
		return new String(cs);
	}
}

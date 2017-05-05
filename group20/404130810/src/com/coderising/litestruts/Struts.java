package com.coderising.litestruts;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coderising.litestruts.utils.StrutsUtil;
import com.coderising.litestruts.view.View;

public class Struts {

	public static View runAction(String actionName, Map<String, String> params) {

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
		View view = new View();
		String className = readActionInConfig(actionName);
		List<String> rtnList = invokeMethod(className, params);
		String result = rtnList.get(0);
		String msg = rtnList.get(1);
		view.setParameters(buildViewParams(msg));
		view.setJsp(buildViewJsp(result,actionName));
		return view;
	}



	private static String readActionInConfig(String actionName) {
		StrutsUtil util = new StrutsUtil();
		return util.invokedAction(actionName);
	}

	private static List<String> invokeMethod(String className, Map<String, String> params) {

		List<String> rtnList = new ArrayList<String>();
		try {
			String name = params.get("name");
			String password = params.get("password");
			// Invoke set method
			Class<?> actionClass = Class.forName(className);
			Method setNameMethod = actionClass.getMethod("setName", String.class);
			Method setPasswordMethod = actionClass.getMethod("setPassword", String.class);
			Object action = actionClass.newInstance();
			setNameMethod.invoke(action, name);
			setPasswordMethod.invoke(action, password);
			// Invoke execute method and add to the return List as first element
			Method executeMethod = actionClass.getMethod("execute");
			rtnList.add(executeMethod.invoke(action).toString());
			// Invoke getMessage method and add to the return List as second element
			Method getMessageMethod = actionClass.getMethod("getMessage");
			rtnList.add(getMessageMethod.invoke(action).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnList;
	}
	
	private static Map buildViewParams(String msg) {
		Map viewParams = new HashMap();
		viewParams.put("message", msg);
		return viewParams;
	}
	
	private static String buildViewJsp(String result, String actionName) {
		StrutsUtil util = new StrutsUtil();
		return util.invokeResult(actionName,result); 
	}

}
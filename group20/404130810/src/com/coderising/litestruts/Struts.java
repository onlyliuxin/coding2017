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
		 * 0. ��ȡ�����ļ�struts.xml
		 * 
		 * 1. ����actionName�ҵ����Ӧ��class �� ����LoginAction, ͨ������ʵ��������������
		 * ��parameters�е����ݣ����ö����setter������ ����parameters�е������� ("name"="test" ,
		 * "password"="1234") , �Ǿ�Ӧ�õ��� setName��setPassword����
		 * 
		 * 2. ͨ��������ö����exectue ������ ����÷���ֵ������"success"
		 * 
		 * 3. ͨ�������ҵ����������getter���������� getMessage��, ͨ�����������ã� ��ֵ�������γ�һ��HashMap , ����
		 * {"message": "��¼�ɹ�"} , �ŵ�View�����parameters
		 * 
		 * 4. ����struts.xml�е� <result> ����,�Լ�execute�ķ���ֵ�� ȷ����һ��jsp��
		 * �ŵ�View�����jsp�ֶ��С�
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
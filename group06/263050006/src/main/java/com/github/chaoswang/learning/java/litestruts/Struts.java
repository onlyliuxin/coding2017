package com.github.chaoswang.learning.java.litestruts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Struts {
	private static Map<String, String> action_class = new HashMap<String, String>();
	private static Map<String, Map<String, String>> action_result = new HashMap<String, Map<String, String>>();
	

	public static View runAction(String actionName,
			Map<String, String> parameters) {

		/*
		 * 0. ��ȡ�����ļ�struts.xml
		 */
		parseConfig();
		
		/*
		 * 
		 * 1. ����actionName�ҵ����Ӧ��class �� ����LoginAction, ͨ������ʵ��������������
		 * ��parameters�е����ݣ����ö����setter������ ����parameters�е������� ("name"="test" ,
		 * "password"="1234") , �Ǿ�Ӧ�õ��� setName��setPassword����
		 */
		Class actionClass = null;
		Object actionInstance = null;
		try {
			actionClass = Class.forName(action_class.get(actionName));
			actionInstance = actionClass.newInstance();
			for (Entry entry : parameters.entrySet()) {
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				actionClass.getMethod(
						"set" + key.substring(0, 1).toUpperCase()
								+ key.substring(1), String.class).invoke(
						actionInstance, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * 2. ͨ��������ö����exectue ������ ����÷���ֵ������"success"
		 */
		
		String returnStr = null;
		try {
			returnStr = (String)actionClass.getMethod("execute", new Class[0]).invoke(actionInstance, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * 3. ͨ�������ҵ����������getter���������� getMessage��, ͨ�����������ã� ��ֵ�������γ�һ��HashMap , ����
		 * {"message": "��¼�ɹ�"} , �ŵ�View�����parameters
		 */
		Map<String, String> params = new HashMap<String, String>();
		Method[] methods = actionClass.getDeclaredMethods();
		try {
			for(Method method : methods){
				if(method.getModifiers() == Modifier.PUBLIC && method.getName().startsWith("get")){
					String key = method.getName().substring(3,4).toLowerCase() + method.getName().substring(4);
					String value = (String)method.invoke(actionInstance, new Object[0]);
					params.put(key, value);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * 4. ����struts.xml�е� <result> ����,�Լ�execute�ķ���ֵ�� ȷ����һ��jsp��
		 * �ŵ�View�����jsp�ֶ��С�
		 */
		View view = new View();
		view.setParameters(params);
		Map<String, String> results = action_result.get(actionName);
		view.setJsp(results.get(returnStr));
		return view;
	}

	private static void parseConfig() {
		String xmlpath = Struts.class.getResource("struts.xml").getFile();
		try {
			xmlpath = URLDecoder.decode(xmlpath, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SAXBuilder builder = new SAXBuilder(false);
		try {
			Document doc = builder.build(xmlpath);
			Element struts = doc.getRootElement();
			List actionlist = struts.getChildren("action");
			for (Iterator iter = actionlist.iterator(); iter.hasNext();) {
				Element action = (Element) iter.next();
				String actionName = action.getAttributeValue("name");
				String actionClass = action.getAttributeValue("class");
				action_class.put(actionName, actionClass);
				List resultlist = action.getChildren("result");
				Map<String,String> result_value = new HashMap<String,String>();
				for (Iterator iter1 = resultlist.iterator(); iter1.hasNext();) {
					Element result = (Element) iter1.next();
					String resultName = result.getAttributeValue("name");
					String resultView = result.getTextTrim();
					result_value.put(resultName, resultView);
				}
				action_result.put(actionName, result_value);
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(action_class);
		System.out.println(action_result);
	}
	
	public static void main(String[] args) {
		Struts.parseConfig();
	}
}
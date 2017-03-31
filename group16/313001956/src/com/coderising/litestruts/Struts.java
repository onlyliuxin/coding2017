package com.coderising.litestruts;

//import java.awt.List;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//import javax.print.attribute.standard.Media;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

	@SuppressWarnings("unchecked")
	public static View runAction(String actionName, Map<String, String> parameters) {

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
		Map<String, String> map = new HashMap<String, String>();
		view.setParameters(map);
		try {

			SAXReader reader = new SAXReader();
			String dir = System.getProperty("user.dir");

			Document document = reader.read(new File(dir + "/src/com/coderising/litestruts/struts.xml"));
			Element struts = document.getRootElement();
			java.util.List<Element> list_action = struts.elements("action");

			Element item = null;
			for (int i = 0; i < list_action.size(); i++) {
				item = list_action.get(i);
				String nm = item.attributeValue("name");
				if (actionName.equals(nm)) {
					break;
				}
			}
			String str_class = item.attributeValue("class");
			// String real_class=dir+"/"+str_class.replace('.', '/');
			// Class<?> cl = Class.forName( dir.replace('\\',
			// '.')+".src."+str_class);
			Class<?> cl = Class.forName(str_class);
			Object instance = cl.newInstance();

			String dNmae = parameters.get("name");
			String dpassword = parameters.get("password");
			Method mName = cl.getMethod("setName", String.class);
			Method mPassword = cl.getMethod("setPassword", String.class);
			mName.invoke(instance, dNmae);
			mPassword.invoke(instance, dpassword);

			Method mExectue = cl.getMethod("execute");
			Object result = mExectue.invoke(instance);

			Method[] methods = cl.getMethods();
			for (Method method : methods) {
				if (isGetter(method)) {
					String mGettername = method.getName().substring(3);
					Object mResult = method.invoke(instance);
					view.getParameters().put(mGettername.toLowerCase(), mResult);
				}
			}

			java.util.List<Element> resulList = item.elements();
			for (Element el : resulList) {
				if (result.toString().equals(el.attributeValue("name"))) {
					view.setJsp(el.getTextTrim());
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	// �ж��Ƿ�getter����
	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}

}

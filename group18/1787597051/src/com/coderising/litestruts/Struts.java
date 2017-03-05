package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

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

		// 0. ��ȡ�����ļ�struts.xml
		File inputXml = new File("src/com/coderising/litestruts/struts.xml");
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(inputXml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		// 1. ����actionName�ҵ����Ӧ��class �� ����LoginAction, ͨ������ʵ��������������
		//���ڵ�
		Element struts = document.getRootElement();
		String className = null;
		Element action = null;
		String attrVal = null;
		View view = new View();
		for (Iterator<?> i = struts.elementIterator(); i.hasNext();) {
			// ��ȡstruts��ǩ�µĽڵ����
			action = (Element) i.next();
			// ��ȡ��(action)��ǩ���������
			Attribute attr = action.attribute("name");
			Attribute attr2 = action.attribute("class");
			if (null != attr) {
				attrVal = attr.getValue();
				if (attrVal.equals(actionName)) {
					className = attr2.getValue();
					Class<?> c = null;
					Constructor<?> con = null;
					Object obj = null;
					Method m = null;
					String result = null;
					try {
						c = Class.forName(className);
						con = c.getConstructor();
						obj = con.newInstance();
						m = c.getDeclaredMethod("setName", String.class);
						m.setAccessible(true);
						m.invoke(obj, parameters.get("name"));
						m = c.getDeclaredMethod("setPassword", String.class);
						m.setAccessible(true);
						m.invoke(obj, parameters.get("password"));
						// 2. ͨ��������ö����exectue ������ ����÷���ֵ������"success"
						m = c.getMethod("execute");
						result = (String) m.invoke(obj);

						// 3. ͨ�������ҵ����������getter���������� getMessage��,
						// ͨ�����������ã� ��ֵ�������γ�һ��HashMap , ���� {"message": "��¼�ɹ�"} ,
						// �ŵ�View�����parameters
						m = c.getMethod("getName");
						String s = (String) m.invoke(obj);
						parameters.put("name", s);
						m = c.getMethod("getPassword");
						s = (String) m.invoke(obj);
						parameters.put("password", s);
						m = c.getMethod("getMessage");
						s = (String) m.invoke(obj);
						parameters.put("message", s);
						//�ŵ�View�����parameters��
						view.setParameters(parameters);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					// 4. ����struts.xml�е� <result> ����,�Լ�execute�ķ���ֵ�� ȷ����һ��jsp��
					// �ŵ�View�����jsp�ֶ��С�
					for (Iterator<?> j = action.elementIterator(); j.hasNext();) {
						Element node = (Element) j.next();
						Attribute attr3 = node.attribute("name");
						String str = attr3.getValue();
						if (null != attr3) {
							if (str.equals(result)) {
								//��ȡ�ڵ������
								String text = node.getText();
								view.setJsp(text);
							}
						}
					}
				}
			}
		}
		return view;
	}

}
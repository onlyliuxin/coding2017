package com.coderising.litestruts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Struts {

	public static View runAction(String actionName,
			Map<String, String> parameters) throws Exception {
		View view = new View();
		// InputStream inputStream = new FileInputStream(new
		// File("D:/git/coding2017/group03/619224754/src/com/coderising/litestruts/struts.xml"));
		SAXReader saxReader = new SAXReader();
		Document document = saxReader
				.read(new File(
						"D:/git/coding2017/group03/619224754/src/com/coderising/litestruts/struts.xml"));

		Element rootElement = document.getRootElement();
		List<Node> lstAction = rootElement.selectNodes("action");
		Iterator it = lstAction.iterator();
		while (it.hasNext()) {
			Element actionElement = (Element) it.next();
			String name = actionElement.attributeValue("name");
			if (name.equals(actionName)) {
				String actionClass = actionElement.attributeValue("class");
				Class classType = Class.forName(actionClass);
				Object obj = classType.newInstance();
				for (String key : parameters.keySet()) {
					String value = parameters.get(key);
					String strMethod = getFiledSetMethod(key);
					Method setMethod = classType.getMethod(strMethod, String.class);
					setMethod.invoke(obj, value);
				}
				Method excuteMethod = classType.getMethod("execute");
				Object retValue = excuteMethod.invoke(obj);
				Node resultNode = actionElement
						.selectSingleNode("result[@name='" + retValue.toString() + "']");
				view.setJsp(resultNode.getText());
				Map<String, String> result = new HashMap<String, String> ();
				
				Method msessageMethod = classType.getMethod("getMessage");
				Object message = msessageMethod.invoke(obj);
				result.put("message", message.toString());
				view.setParameters(result);
			}
		}

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
		 */

		return view;
	}

	public static String getFiledSetMethod(String filedName) {
		String methodName = "";
		methodName = "set" + filedName.toUpperCase().substring(0, 1)
				+ filedName.substring(1);
		return methodName;
	}
}
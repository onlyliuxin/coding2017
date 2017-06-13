package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class Struts {

	public static View runAction(String actionName,
			Map<String, String> parameters) {

		
		View view = new View();
		Map<String,Map<String,String>> array = new HashMap<String, Map<String,String>>();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String> classData = new HashMap<String, String>();
		try {
			analysisXml(classData,array);
			Map<String, String> jspData = array.get(actionName);
			String s = "对不起，您传递过来的actionName并没有对应的class类，所以需要重新传";
			if (!classData.containsKey(actionName)) {
				throw new ClassNotFoundException(s);
			}
			
			Class<?> class1 = Class.forName(classData.get(actionName));
			LoginAction login = (LoginAction) class1.newInstance();
			for (String ss : parameters.keySet()) {
				Method[] methos1 = class1.getMethods();
				for (int i = 0; i < methos1.length; i++) {
					if (("set" + ss.substring(0, 1).toUpperCase() + ss
							.substring(1)).equals(methos1[i].getName())) {
						methos1[i].invoke(login, parameters.get(ss));
						break;

					}
				}
			}
			
			Method method1 = class1.getMethod("execute");
			String result = (String) method1.invoke(login);
			if(null!=result){
				view.setJsp(jspData.get(result));
			}
			Method[] methos2 = class1.getMethods();
			for (int i = 0; i < methos2.length; i++) {
				if(methos2[i].getName().substring(0, 3).equals("get")){
					Object value1 = (Object) (methos2[i].invoke(login));
					String name1 = methos2[i].getName();
					params.put(name1.substring(3, 4).toLowerCase()+name1.substring(4), value1);
				}
			}
			view.setParameters(params);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return view;
	}

	
	public static void analysisXml(Map<String, String> xmlData,Map<String,Map<String,String>> array) {
		try {
			
			String dirpath = System.getProperty("user.dir");
			String xmlFile = dirpath + "/WebContent/WEB-INF/etc/struts.xml";
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File(xmlFile));
			Element xRoot = doc.getRootElement();
			List actions = getChildren(xRoot, "action");
			for (int i = 0; i < actions.size(); i++) {
				Element e = (Element) actions.get(i);
				String actionName1 = getAttributeValue(e, "name");
				String className = getAttributeValue(e, "class");
				xmlData.put(actionName1, className);
				List results = getChildren(e, "result");
				Map<String, String> jspData = new HashMap<String, String>();
				for (int j = 0; j < results.size(); j++) {
					Element result = (Element) results.get(j);
					String jspUrl = getValue(result);
					String resultName = getAttributeValue(result, "name");
					jspData.put(resultName, jspUrl);
					array.put(actionName1, jspData);
				}
			}

			// /StrutsDemo/WebContent/WEB-INF/etc/struts.xml
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static Element getChild(Element element, String sonMark) {
		return element == null ? null : element.getChild(sonMark);
	}

	
	public static List getChildren(Element element, String sonMark) {
		return element == null ? null : element.getChildren(sonMark);
	}

	
	public static String getValue(Element element) {
		return element == null ? "" : element.getValue();
	}

	
	public static String getAttributeValue(Element element, String attribute) {
		return element == null ? null : element.getAttributeValue(attribute);
	}

}
package com.coding.litestruts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class StrutsXMLParser {
	
	public static Map<String, Action> getStrutsXML(){
		String path = System.getProperty("user.dir");
		path = path + "/src/struts.xml";
		return getStrutsXML(path);
	}
	
	public static Map<String, Action> getStrutsXML(String xmlPath){
		if(xmlPath==null){
			throw new IllegalArgumentException();
		}
		Map<String, Action> actions = new HashMap<String, Action>();
		try {
			SAXReader read = new SAXReader();
			Document doc = read.read(xmlPath);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> eles = root.elements("action");
			for (Element element : eles) {
				String name = element.attributeValue("name");
				actions.put(name, getAction(element));
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return actions;
	}
	
	private static Action getAction(Element element) {
		String name = element.attributeValue("name");
		String clazz = element.attributeValue("class");
		String method = element.attributeValue("method");
		method = method==null?Action.DEFAULT_METHOD:method;
		Action action = new Action(name, clazz, method);
		@SuppressWarnings("unchecked")
		List<Element> eles = element.elements("result");
		for (Element ele : eles) {
			String resName = ele.attributeValue("name");
			resName = resName==null?Result.DEFAULT_NAME:resName;
			action.getResults().put(resName, getResult(ele));
		}
		return action;
	}

	private static Result getResult(Element ele) {
		String name = ele.attributeValue("name");
		name = name==null?Result.DEFAULT_NAME:name;
		String type = ele.attributeValue("type");
		String jspPath = ele.getText().trim();
		Result result = new Result(name, type, jspPath);
		return result;
	}

}

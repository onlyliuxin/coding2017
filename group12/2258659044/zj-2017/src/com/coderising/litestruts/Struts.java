package com.coderising.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Struts {

	private final static String ACTION = "action";
	private final static String RESULT = "result";
	private final static String NAME = "name";
	private final static String CLASS = "class";
	private final static String EXECUTE = "execute";

	public static View runAction(String actionName,
			Map<String, String> parameters) {


		View view = new View();
		Map<String,Object> viewMap = new HashMap<String,Object>();
		
		String path = "src/com/coderising/litestruts/struts.xml";
		Map<String, String> xmlMap = readStrutsXml(path, actionName);		

		try {
			String calssPath = xmlMap.get(CLASS);
			Class<?> clazz = Class.forName(calssPath);
			Object action = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			String fieldName;
			String methodName;
			for (int i = 0; i < fields.length; i++) {
				fieldName = fields[i].getName();
				if(parameters.containsKey(fieldName)){
					methodName = "set" + fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
					Method method = clazz.getMethod(methodName, fields[i].getType());
					method.invoke(action, parameters.get(fieldName));
				}				
			}

			Method successMethos = clazz.getMethod(EXECUTE);			
			Object result = successMethos.invoke(action);
			
			for (int i = 0; i < fields.length; i++) {
				fieldName = fields[i].getName();
				methodName = "get" + fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				Method method = clazz.getMethod(methodName);
				Object value = method.invoke(action);
				viewMap.put(fieldName, value);

			}
			view.setParameters(viewMap);

			String jsp = xmlMap.get(result.toString());
			view.setJsp(jsp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}

	/**
	 * 读取struts.xml文件
	 * 
	 * @param filePath
	 *            ：struts.xml路劲
	 * @param actionName
	 * @return
	 */
	private static Map<String, String> readStrutsXml(String filePath,
			String actionName) {

		File xmlFile = new File(filePath);
		Map<String, String> xmlMap = new HashMap<String, String>();
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			// 获取根节点
			Element element = document.getDocumentElement();
			NodeList actionNodes = element.getChildNodes();
			for (int i = 0; i < actionNodes.getLength(); i++) {
				Node actionNode = actionNodes.item(i);
				if (ACTION.equals(actionNode.getNodeName())) {
					NamedNodeMap actionNodeMap = actionNode.getAttributes();
					String atName = actionNodeMap.getNamedItem(NAME)
							.getNodeValue();
					if (atName.equals(actionName)) {
						String classPath = actionNodeMap.getNamedItem(CLASS)
								.getNodeValue();
						xmlMap.put(CLASS, classPath);
						NodeList resultNodes = actionNode.getChildNodes();
						for (int j = 0; j < resultNodes.getLength(); j++) {
							Node resultNode = resultNodes.item(j);
							if (RESULT.equals(resultNode.getNodeName())) {
								NamedNodeMap resultNodeMap = resultNode
										.getAttributes();
								String jspName = resultNodeMap.getNamedItem(
										NAME).getNodeValue();
								String jspPath = resultNode.getTextContent();
								xmlMap.put(jspName, jspPath);
							}
						}

					}
				}

			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlMap;
	}	
}
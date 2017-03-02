package com.coderising.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.coderising.litestruts.bean.Action;
import com.coderising.litestruts.bean.Result;

public class Struts {

	private final static String ACTION = "action";
	private final static String RESULT = "result";
	private final static String NAME = "name";
	private final static String CLASS = "class";
	private final static String TYPE = "type";
	private final static String EXECUTE = "execute";

	private final static List<Action> actions;
	static{
		String path = "src/com/coderising/litestruts/struts.xml";
		actions = readStrutsXml(path);
	}
	
	public static View runAction(String actionName,
			Map<String, String> parameters) {

		View view = new View();
		Map<String,Object> viewMap = new HashMap<String,Object>();
				
		Action actionBean= getCurrentAction(actionName);	

		try {
			String calssPath = actionBean.getClaz();
			Class<?> clazz = Class.forName(calssPath);
			Object instance = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			String fieldName;
			String methodName;
			for (int i = 0; i < fields.length; i++) {
				fieldName = fields[i].getName();
				if(parameters.containsKey(fieldName)){
					methodName = "set" + fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
					Method method = clazz.getMethod(methodName, fields[i].getType());
					method.invoke(instance, parameters.get(fieldName));
				}				
			}

			Method successMethos = clazz.getMethod(EXECUTE);			
			Object result = successMethos.invoke(instance);
			
			for (int i = 0; i < fields.length; i++) {
				fieldName = fields[i].getName();
				methodName = "get" + fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				Method method = clazz.getMethod(methodName);
				Object value = method.invoke(instance);
				viewMap.put(fieldName, value);

			}
			view.setParameters(viewMap);

			List<Result> results = actionBean.getResults();
			for (int i = 0; i < results.size(); i++) {
				if(results.get(i).getName()){
					
				}
			}
			String jsp = actionBean.get(result.toString());
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
	
	private static List<Action> readStrutsXml(String filePath) {

		File xmlFile = new File(filePath);
		Action action = null;
		Result result = null;
		List<Result> results = null;
		List<Action> actions = new ArrayList<Action>();

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
					action = new Action();
					// 解析action标签
					NamedNodeMap actionNodeMap = actionNode.getAttributes();
					String actionName = actionNodeMap.getNamedItem(NAME)
							.getNodeValue();
					String claz = actionNodeMap.getNamedItem(CLASS)
							.getNodeValue();
					action.setName(actionName);
					action.setClaz(claz);
					// 解析result标签
					NodeList resultNodes = actionNode.getChildNodes();
					for (int j = 0; j < resultNodes.getLength(); j++) {
						results = new ArrayList<Result>();
						Node resultNode = resultNodes.item(j);
						if (RESULT.equals(resultNode.getNodeName())) {
							result = new Result();
							NamedNodeMap resultNodeMap = resultNode
									.getAttributes();
							String resultName = resultNodeMap
									.getNamedItem(NAME).getNodeValue();
							String resultType = resultNodeMap
									.getNamedItem(TYPE).getNodeValue();
							String jspPath = resultNode.getTextContent();
							result.setName(resultName);
							result.setType(resultType);
							result.setJspPath(jspPath);
							results.add(result);
						}
					}
					action.setResults(results);
				}
			}

			actions.add(action);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return actions;
	}	
	
	private static Action getCurrentAction(String actionName){
		
		for (int i = 0; i < actions.size(); i++) {
			if(actions.get(i).getName().equals(actionName)){
				return actions.get(i);
			}
		}
		return null;
	}
}
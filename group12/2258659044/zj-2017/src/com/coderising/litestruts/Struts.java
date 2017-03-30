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

	//Struts.xml描述的所有action信息
	private final static List<Action> actions;
	//读取Struts.xml获取所有action相关信息
	static{
		String path = "src/com/coderising/litestruts/struts.xml";
		actions = readStrutsXml(path);
	}
	
	public static View runAction(String actionName,
			Map<String, String> parameters) {

		View view = new View();
		Map<String,Object> viewMap = new HashMap<String,Object>();
		
		//获取当前请求的action信息
		Action actionBean= getCurrentAction(actionName);	
		if(actionBean == null){
			return view;
		}
		try {
			//创建实例获取属性
			String calssPath = actionBean.getClazz();
			Class<?> clazz = Class.forName(calssPath);
			Object instance = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			String fieldName;
			String methodName;
			//调用set方法为属性赋值
			for (int i = 0; i < fields.length; i++) {
				fieldName = fields[i].getName();
				if(parameters.containsKey(fieldName)){
					methodName = "set" + fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);
					Method method = clazz.getMethod(methodName, fields[i].getType());
					if(method != null){
						method.invoke(instance, parameters.get(fieldName));
					}					
				}				
			}

			//调用默认execute方法
			Method successMethos = clazz.getMethod(EXECUTE);			
			Object result = successMethos.invoke(instance);
			// 调用get方法获取属性值
			for (int i = 0; i < fields.length; i++) {
				fieldName = fields[i].getName();
				methodName = "get" + fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				Method method = clazz.getMethod(methodName);
				if(method != null){
					Object value = method.invoke(instance);
					viewMap.put(fieldName, value);
				}				
			}
			//封装view对象所需数据
			view.setParameters(viewMap);
			List<Result> results = actionBean.getResults();
			for (int i = 0; i < results.size(); i++) {
				if(results.get(i).getName().equals(result)){
					view.setJsp(results.get(i).getRedirectUrl());
					break;
				}
			}
			
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
					String actionName = getNodePropertyValue(actionNodeMap.getNamedItem(NAME));
					String claz = getNodePropertyValue(actionNodeMap.getNamedItem(CLASS));
					action.setName(actionName);
					action.setClazz(claz);
					// 解析result标签
					NodeList resultNodes = actionNode.getChildNodes();
					results = new ArrayList<Result>();
					for (int j = 0; j < resultNodes.getLength(); j++) {						
						Node resultNode = resultNodes.item(j);
						if (RESULT.equals(resultNode.getNodeName())) {
							result = new Result();
							NamedNodeMap resultNodeMap = resultNode.getAttributes();									
							String resultName = getNodePropertyValue(resultNodeMap.getNamedItem(NAME));									
							String resultType = getNodePropertyValue(resultNodeMap.getNamedItem(TYPE));									
							String jspPath = resultNode.getTextContent();
							result.setName(resultName);
							result.setType(resultType);
							result.setRedirectUrl(jspPath);
							results.add(result);
						}
						
					}
					action.setResults(results);
					actions.add(action);
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return actions;
	}	
	
	/**
	 * 获取当前action信息
	 * @param actionName
	 * @return
	 */
	private static Action getCurrentAction(String actionName){
		
		for (int i = 0; i < actions.size(); i++) {
			if(actions.get(i).getName().equals(actionName)){
				return actions.get(i);
			}
		}
		return null;
	}
	
	/**
	 * 获取节点属性值
	 * @param node
	 * @return
	 */
	private static String getNodePropertyValue(Node node){
		
		if(node!=null){
			return node.getNodeValue();
		}
		return null;
	}
	
}
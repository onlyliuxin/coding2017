package com.louis.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LYDOMParser {
	
	private static LYDOMParser domParser;
	public static LYDOMParser shareInstance() {
		if (domParser == null) {
			domParser = new LYDOMParser();
		}
		return domParser;
	}

	Map<String, Map<String, String>> actionMap = new HashMap<String, Map<String, String>>(); 
	public LYDOMParser() {
		Document document = this.createDocument("file/struct.xml");
		Element rootElement = document.getDocumentElement();
		
		// <action>
		NodeList nodes = rootElement.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			 Node node = nodes.item(i);
			 if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element child = (Element)node;
				String name = child.getAttribute("name");
				String cls = child.getAttribute("class");
//				System.out.println(name+"    "+cls);
				
				// store
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put("class", cls);
				
				// <result>
				NodeList nodes2 = child.getChildNodes();
				for (int j = 0; j < nodes2.getLength(); j++) {
					 Node node2 = nodes2.item(j);
					 if (node2.getNodeType() == Node.ELEMENT_NODE) {
						Element child2 = (Element)node2;
						String name2 = child2.getAttribute("name");
						String content = child2.getTextContent();
//						System.out.println(name2 + "  " + content);
						
						
						if (name2.equals("success")) {
							parameters.put("success", content);
						} else if (name2.equals("fail") || name2.equals("error")) {
							parameters.put("failed", content);
						}
					}
				}
				
				actionMap.put(name, parameters);
			}
		}
//		System.out.println(actionMap);
	}
	
	public String getClassName(String actionName) {
		Map<String, String> parameters = actionMap.get(actionName);
		return parameters.get("class");
	}
	
	public String getSuccessJSP(String actionName) {
		Map<String, String> parameters = actionMap.get(actionName);
		return parameters.get("success");
	}
	
	public String getFailedJSP(String actionName) {
		Map<String, String> parameters = actionMap.get(actionName);
		return parameters.get("failed");
	}
	
	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	public Document createDocument(String filePath) {
		Document document = null;
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			document = builder.parse(new File(filePath));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}
}

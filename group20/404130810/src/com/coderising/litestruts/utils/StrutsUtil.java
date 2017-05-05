package com.coderising.litestruts.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StrutsUtil {
	
	public final static String CONFIG_PATH = "../struts.xml";
	
	public final static String CONFIG_NODE_ACTION = "action";
	
	public final static String CONFIG_ATTR_NAME = "name";
	
	public final static String CONFIG_ATTR_CLASS = "class";
	
	public String invokedAction(String actionName){
		if(null != actionName && !"".equals(actionName)){
			Document doc = generateDoc();
			NodeList nodeList = doc.getElementsByTagName(CONFIG_NODE_ACTION);
			for (int i = 0; i < nodeList.getLength(); i++) {
				String actionNameConfiged = nodeList.item(i).getAttributes().getNamedItem(CONFIG_ATTR_NAME).getNodeValue();
				if(actionName.equals(actionNameConfiged)){
					return nodeList.item(i).getAttributes().getNamedItem(CONFIG_ATTR_CLASS).getNodeValue();
				}
			}
		}
		throw new RuntimeException("actionName can't be found");
	}
	
	private Document generateDoc(){
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse (lookupConfigFile());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return doc;
	}
	
	private File lookupConfigFile(){
		URL url = getClass().getResource(CONFIG_PATH);
		return new File(url.getPath());
	}


	public String invokeResult(String actionName, String result) {
		Document doc = generateDoc();
		NodeList nodeList = doc.getElementsByTagName("result");
		List<Node> subNodeList = new ArrayList<Node>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node parentNode = nodeList.item(i).getParentNode();
			if(parentNode.getAttributes().getNamedItem(CONFIG_ATTR_NAME).getNodeValue().equals(actionName)){
				subNodeList.add(nodeList.item(i));
			}
		}
		for (int i = 0; i < subNodeList.size(); i++) {
			Node node = subNodeList.get(i);
			if(node.getAttributes().getNamedItem(CONFIG_ATTR_NAME).getNodeValue().equals(result)){
				return node.getTextContent();
			}
		}
		throw new RuntimeException("result can't be found");
	}

}

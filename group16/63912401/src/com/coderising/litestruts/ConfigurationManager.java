package com.coderising.litestruts;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析XML文件
 * ConfigurationManager
 * @author greenhills
 * 2017年2月27日 下午10:18:10
 */
public class ConfigurationManager {
	/**
	 * 读取xml文件的文档对象
	 */
	private static Document document;
	/**
	 * 配置文件路径
	 */
	static  String configureFileName="struts.xml";
	/**
	 * 当前类路径
	 */
	static  String currentPath;	
	static{
		currentPath = ConfigurationManager.class.getResource("").getPath().substring(1);
	}
	
	static{
		try {
			SAXReader sax=new SAXReader();
			document = sax.read(new File(currentPath+configureFileName));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析配置文件
	 * ConfigurationManager.java
	 * @param @return
	 * @author greenhills
	 * 2017年2月27日 下午11:28:59
	 */
	public static Map<String,ActionMapping> loadXml(){
		Map<String,ActionMapping> actionMappings=new HashMap<String, ActionMapping>();
		
		Element root=document.getRootElement();
		List<Element> actionList= root.elements("action");
		
		for(Element actionElemnt:actionList){
			ActionMapping actionMapping=new ActionMapping(
					actionElemnt.attributeValue("name"),
					actionElemnt.attributeValue("class"),
					actionElemnt.attributeValue("method")
				);
			//获取action下的result节点
			List<Element> resultList = actionElemnt.elements("result");
			for(Element resultElemnt:resultList){
				ResultMapping resultMapping=new ResultMapping(
						resultElemnt.attributeValue("name"), 
						resultElemnt.attributeValue("type"), 
						resultElemnt.getTextTrim()
					);
				
				//保存ResultMapping（以result标签的name为key）
				actionMapping.setResultMappings(resultMapping.getName(),resultMapping);
			}
			
			//保存ActionMapping（以action标签的name为key）
			actionMappings.put(actionMapping.getName(), actionMapping);
		}
		
		return actionMappings;
	}
}

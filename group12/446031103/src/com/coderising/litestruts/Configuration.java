package com.coderising.litestruts;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Configuration {
	Map<String,ActionConfig> actions = new HashMap<>();
	
	public Configuration(String fileName){
		String packageName = this.getClass().getPackage().getName();		
		packageName = packageName.replace('.', '/');		
		InputStream is = this.getClass().getResourceAsStream("/" + packageName + "/" + fileName);
		parseXML(is);
	}
	
	

	private void parseXML(InputStream is) {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(is);
			Element struts = document.getRootElement();
			Iterator<?> actions = struts.elementIterator();
			while (actions.hasNext()) {
				Element action = (Element) actions.next();
				String actionName=action.attributeValue("name");
				String actionClass=action.attributeValue("class");
				ActionConfig ac = new ActionConfig(actionName,actionClass);
					Iterator<?> results = action.elementIterator();
					while (results.hasNext()) {
						Element result = (Element) results.next();
						String name = result.attributeValue("name");
						String viewName = result.getStringValue();
						ac.addViewResult(name, viewName);					
				}
					this.actions.put(actionName, ac);
			}
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
		
	}



	public String getClassName(String actionName) {
		ActionConfig actionConfig = actions.get(actionName);
		if(null==actionConfig)
			return null;
		return actionConfig.getClassName();
	}
	
	public String getResultView(String actionName, String resultName) {
		ActionConfig actionConfig =actions.get(actionName);
		if(null==actionConfig)
			return null;
		return actionConfig.getViewName(resultName);
	}
	
	private static class ActionConfig{
		
		String name;
		String clzName;
		Map<String,String> viewResult = new HashMap<>();
		
		
		public ActionConfig(String actionName, String clzName) {
			this.name = actionName;
			this.clzName = clzName;
		}
		public String getClassName(){
			return clzName;
		}
		public void addViewResult(String name, String viewName){
			viewResult.put(name, viewName);
		}
		public String getViewName(String resultName){
			return viewResult.get(resultName);
		}
	}

	
}

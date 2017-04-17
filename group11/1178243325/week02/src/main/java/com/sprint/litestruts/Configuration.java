package com.sprint.litestruts;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
public class Configuration {
	Map<String, ActionConfig> actions = new HashMap<>();
	public Configuration(String fileName) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);	
		parseXML(is);
		try {
			is.close();
		} catch (IOException e) {
			throw new ConfigurationException(e);
		}
	}	

	private void parseXML(InputStream is) {
		SAXBuilder builder = new SAXBuilder();
		try {
			Document doc = builder.build(is);
			Element root = doc.getRootElement();
			for (Element actionElement : root.getChildren("action")) {
				String actionName = actionElement.getAttributeValue("name");
				String clzName = actionElement.getAttributeValue("class");

				ActionConfig ac = new ActionConfig(actionName, clzName);
				for (Element resultElement : actionElement.getChildren("result")) {
					String resultName = resultElement.getAttributeValue("name");
					String viewName = resultElement.getText().trim();
					ac.addViewResult(resultName, viewName);
				}
				this.actions.put(actionName, ac);
			}
		} catch (JDOMException e) {
			throw new ConfigurationException(e);
		} catch (IOException e) {
			throw new ConfigurationException(e);
		}
	}

	public String getClassName(String actionName) {
		ActionConfig ac = this.actions.get(actionName);
		if(ac == null) {
			return null;
		}
		return ac.getClassName();
	}

	public String getResultView(String actionName, String resultName) {
		ActionConfig ac = this.actions.get(actionName);
		if (ac == null) {
			return null;
		}
		return ac.getViewName(resultName);
	}
	private static class ActionConfig {
		String name;
		String clzName;
		Map<String, String> viewResult = new HashMap<>();
		
		public ActionConfig(String actionName, String clzName) {
			this.name = actionName;
			this.clzName = clzName;
		}

		public String getClassName() {
			return clzName;
		}

		public void addViewResult(String name, String viewName) {
			viewResult.put(name, viewName);
		}

		public String getViewName(String resultName) {
			return viewResult.get(resultName);
		}
	}
}

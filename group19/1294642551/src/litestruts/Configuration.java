package litestruts;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * 配置类
 * 提取xml文件中的元素和属性，封装成Java对象，这样方便使用
 * @author 12946
 *
 */

public class Configuration {
	
	//键为actionName,值为ActionConfig对象
	private Map<String, ActionConfig> actions = new HashMap<String, ActionConfig>();

	public Configuration(String filename) {
		//得到输入流
		String packageName = this.getClass().getPackage().getName();
		packageName = packageName.replace(".", "/");
		InputStream input = this.getClass().getResourceAsStream("/"+packageName+"/"+filename);
		parseXML(input);
		try {
			input.close();
		} catch (IOException e) {
			throw new ConfigurationException(e);
		}
		//解析XML

	}

	/**
	 * 解析XML，将XML的元素封装到ActionConfig对象中
	 * @param input
	 */
	@SuppressWarnings("unchecked")
	private void parseXML(InputStream input) {

		try {
	        SAXReader reader = new SAXReader();  
	        Document document = reader.read(input);
	        Element root = document.getRootElement(); 
	        
	        List<Element> actionList = root.elements("action");//得到所有action元素
	        for(Element action : actionList){
	        	String actionName = action.attributeValue("name");
	        	String className = action.attributeValue("class");
	        	// 创建ActionConfig对象
	        	ActionConfig ac = new ActionConfig(actionName, className);
	        	// 给ActionConfig对象增加ViewResult
	        	List<Element> results = action.elements("result");
	        	for(Element result : results){
	        		String resultName = result.attributeValue("name");
	        		String resultText = result.getText();
	        		ac.addViewResult(resultName, resultText);
	        	}
	        	// 将ActionConfig对象加入到actions中
	        	this.actions.put(actionName, ac);
	        }
		} catch (DocumentException e) {
			throw new ConfigurationException(e);
		}

		
	}

	/**
	 * 获取指定action中的class名称
	 * @param actionName
	 * @return
	 */
	public String getClassName(String actionName) {
		ActionConfig ac = this.actions.get(actionName);
		if(ac == null){
			return null;
		}
		return ac.getClassName();
	}
	
	/**
	 * 根据resultName获取resultView
	 * @param string
	 * @return
	 */
	public String getResultView(String actionName, String resultName) {
		ActionConfig ac = this.actions.get(actionName);
		if(ac == null){
			return null;
		}
		return ac.getViewName(resultName);
	}
	
	/**
	 * 内部类，封装struts.xml的元素
	 * @author 12946
	 *
	 */
	@SuppressWarnings("unused")
	private class ActionConfig{
		private String actionName;
		private String className;
		Map<String, String> viewResult = new HashMap<String, String>();
		
		public ActionConfig(String actionName, String className) {
			super();
			this.actionName = actionName;
			this.className = className;
		}
		
		public String getClassName(){
			return className;
		}
		
		public void addViewResult(String name, String viewName){
			viewResult.put(name, viewName);
		}
		
		public String getViewName(String resultName){
			return viewResult.get(resultName);
		}
		
	}




}

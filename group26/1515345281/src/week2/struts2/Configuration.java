package week2.struts2;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


/**
 * 配置解析struts.xml文件
 * @author Administrator
 *
 */
public class Configuration {

	Map<String,ActionConfig> actions=new HashMap<>();
	
	public Configuration(String fileName) {
		
		//获取当前类包名
		String packageName=this.getClass().getPackage().getName();
		
		packageName=packageName.replace(".", "/");
		//获取文件流
		InputStream input=this.getClass().getResourceAsStream("/"+packageName+"/"+fileName);
		
		parseXml(input);
		
		try{
			input.close();
		}catch(IOException e){
			throw new ConfigurationException(e);
		}
	}

	private void parseXml(InputStream input) {
		
		SAXBuilder builder=new SAXBuilder();
		
		try {
			Document document=builder.build(input);
			
			Element root=document.getRootElement();
			
			for(Element actionElement:root.getChildren("action")){
				
				String actionName=actionElement.getAttributeValue("name");
				String clazzName=actionElement.getAttributeValue("class");
				
				ActionConfig ac=new ActionConfig(actionName,clazzName);
				
				for(Element resultElement:actionElement.getChildren("result")){
					
					String resultName=resultElement.getAttributeValue("name");
					String viewName=resultElement.getText().trim();
					
					ac.setViewResult(resultName, viewName);
				}
				
				actions.put(actionName, ac);
			}
			
		} catch (JDOMException e) {
			throw new ConfigurationException(e);
		} catch (IOException e) {
			throw new ConfigurationException(e);
		}
		
	}

	public String getClassName(String actionName) {
		
		ActionConfig ac=this.actions.get(actionName);
		
		if(null == ac){
			return null;
		}
		
		return ac.getClazzName();
	}

	public String getResultView(String actionName, String resultName) {
		
		ActionConfig ac=this.actions.get(actionName);
		
		if(null == ac){
			return null;
		}
		
		return ac.getViewResult(resultName);
	}
	
	private static class ActionConfig{
		
		String actionName;
		String clazzName;
		Map<String,String> viewResults=new HashMap<>();
		
		public ActionConfig(String actionName,String clazzName){
			this.actionName=actionName;
			this.clazzName=clazzName;
		}

		public String getClazzName() {
			return clazzName;
		}

		public void setViewResult(String name,String  viewName){
			viewResults.put(name, viewName);
		}
		
		public String getViewResult(String name){
			String viewName=viewResults.get(name);
			return viewName;
		}
	}
}

package com.coderising.litestruts;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Configureation {
	Map<String,ActionConfig> actions=new HashMap<>();
	
    public Configureation(String fileName)
    {
    	String packageName=this.getClass().getPackage().getName();
    	packageName=packageName.replace('.', '/');
    	InputStream is=this.getClass().getResourceAsStream("/"+packageName+"/"+fileName);
    	parseXML(is);
    	
    	try{
    		is.close();
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    }

	private void parseXML(InputStream is) {
		
		SAXBuilder builder=new SAXBuilder();
		
		try{
			Document doc=builder.build(is);
			Element root =doc.getRootElement();
			for(Element actionElement :root.getChildren("action"))
			{
				String actionName=actionElement.getAttributeValue("name");
				String clzName=actionElement.getAttributeValue("class");
				ActionConfig ac=new ActionConfig(actionName,clzName);
				for(Element resultElement:actionElement.getChildren())
				{
					String resultName=resultElement.getAttributeValue("name");
					String viewName=resultElement.getText().trim();
					
					ac.addViewResult(resultName, viewName);
					
				}
				this.actions.put(actionName, ac);//把actionName以及其下面的resultName和viewName构成一个键值对	
			}
		}
			catch(JDOMException e){
				e.printStackTrace();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
	}

	public String getClassName(String action) {
		ActionConfig ac=this.actions.get(action);
		if(ac==null)
		{
			return null;
		}
		return ac.getClassName();
	}
	
	public String getResultView(String action,String resultName)
	{
		ActionConfig ac=this.actions.get(action);
		if(ac==null){
			return null;
		}
		return ac.getViewName(resultName);
	}
	private static class ActionConfig{
		String name;
		String clzName;
		Map<String,String> viewResult=new HashMap<>();
		
		public ActionConfig(String actionName,String clzName){
			this.name=actionName;
			this.clzName=clzName;
		}
		public String getClassName(){
			return clzName;
		}
		public void addViewResult(String name,String viewName){
			viewResult.put(name, viewName);
		}
		public String getViewName(String resultName)
		{
			return viewResult.get(resultName);
		}
	}
}

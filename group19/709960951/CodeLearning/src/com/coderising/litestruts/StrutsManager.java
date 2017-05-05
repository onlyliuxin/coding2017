package com.coderising.litestruts;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class StrutsManager {
	private static StrutsManager instance;
	private Map<String,StructsAction> actions;
	private StrutsManager(){}
	public static StrutsManager getInstance()
	{
		if(instance==null)
		{
			synchronized (StrutsManager.class) {
				if(instance==null)
				{
					instance=new StrutsManager();
					instance.readStrutsXml();
				}
				
			}
		}
		return instance;
	}
	
	/**
	 * 读取struts配置文件
	 */
	private void readStrutsXml() {
		actions = new HashMap<>();
		String path = StrutsManager.class.getResource("struts.xml").getFile();
		SAXReader reader = new SAXReader();
		Document doc;
		try {
			doc = reader.read(new File(path));
			Element root = doc.getRootElement();
			//遍历action节点
			for(Iterator<?> childIter=root.elementIterator("action");childIter.hasNext();)
			{
				StructsAction action=new StructsAction();
				Element ele=(Element)childIter.next();
				String name=ele.attributeValue("name");
				String className=ele.attributeValue("class");
				//System.out.println(name+":"+className);
				action.setName(name);
				action.setActionClass(className);
				Map<String,String> results=new HashMap<>();
				//遍历result节点
				for(Iterator<?> resultIter=ele.elementIterator("result");resultIter.hasNext();)
				{
					Element resultEle=(Element)resultIter.next();
					String resultName=resultEle.attributeValue("name");
					String resultUrl=resultEle.getText();
					results.put(resultName, resultUrl);
					//System.out.println(resultName+":"+resultUrl);
				}
				action.setResults(results);
				actions.put(action.getName(), action);
			} 
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public StructsAction getAction(String name)
	{
		return actions.get(name);
	}
	
	public static void main(String[] argv) {

		StrutsManager instance=StrutsManager.getInstance();
		StructsAction actionObj=instance.getAction("login");
		System.out.println(actionObj.getName());
		System.out.println(actionObj.getActionClass());
		System.out.println(actionObj.getResults().get("success"));
		System.out.println(actionObj.getResults().get("fail"));
		try {
			Class.forName("com.coderising.litestruts.LoginAction");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

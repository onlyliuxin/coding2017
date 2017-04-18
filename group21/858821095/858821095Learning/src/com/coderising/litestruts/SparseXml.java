package com.coderising.litestruts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SparseXml {
	
	
	Map<String, ActionResult> action = new HashMap<>();
	public SparseXml(String fileName) {
		File file = new File(fileName);
		try {
			InputStream in = new FileInputStream(file);
			parseXml(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void parseXml(InputStream in){
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			Iterator actionIt = root.elementIterator("action");
			while(actionIt.hasNext()){
				Element actionEle = (Element) actionIt.next();
				String actionName = actionEle.attributeValue("name");
				String className = actionEle.attributeValue("class");
				//System.out.println("++++"+actionName+"+++"+className);
				
				ActionResult ar = new ActionResult(actionName, className);
				Iterator  resultIt= actionEle.elementIterator("result");
				while(resultIt.hasNext()){
					Element resultEle = (Element) resultIt.next();
					String resultMessage = resultEle.attributeValue("name");
					String resultView = resultEle.getText();
					ar.addResultView(resultMessage,resultView);
					System.out.println("++++"+resultMessage+"+++++"+resultView);
				}
				
				this.action.put(actionName, ar);
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public String getLoginClass(String actionName) {
		ActionResult ac = action.get(actionName);
		if(ac==null)
			return null;
		return action.get(actionName).getClassName();
	}

	public String getLogoutClass(String actionName) {
		ActionResult ac = action.get(actionName);
		if(ac==null)
			return null;
		return action.get(actionName).getClassName();
	}

	public List<Method> getSetMethods(Class clz) {
		return getMethods(clz, "set");
	}

	public List<Method> getGetMethods(Class clz) {
		return getMethods(clz, "get");
	}
	
	public List<Method> getMethods(Class clz, String startWith){
		List<Method> Methods = new ArrayList<>();
		Method[] methods = clz.getDeclaredMethods();
		
		for(int i=0;i<methods.length;i++){
			if(methods[i].getName().startsWith(startWith)){
				Methods.add(methods[i]);
			}
		}
		return Methods;
	}

	public void setParams(Object o, Map<String, String> params) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	
		List<Method> setMethods = getSetMethods(o.getClass());
		
		for(String name:params.keySet()){
			String methodName = "set"+name;
			for(Method m:setMethods){
				if(m.getName().equalsIgnoreCase(methodName)){
					m.invoke(o, params.get(name));
				}
			}
		}
	}

	public Map<String, Object> getParams(Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Method> getMethods = getGetMethods(o.getClass());
		Map<String, Object> getParams = new HashMap<>();
		for(Method m:getMethods){
			String getName = m.getName().replace("get", "").toLowerCase();
			Object value = m.invoke(o);
			getParams.put(getName, value);
		}
		return getParams;
	}

	public String getResultView(String actionName, String actionResult) {
		ActionResult ac = action.get(actionName);
		if(ac==null)
			return null;
		return ac.getResultView(actionResult);
	}

	public String GetExcute(Object o) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = o.getClass().getDeclaredMethod("execute");
		String result = (String) method.invoke(o);
		return result;
	}
	
	
}

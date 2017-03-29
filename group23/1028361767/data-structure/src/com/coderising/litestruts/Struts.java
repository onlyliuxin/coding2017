package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {
	
	private final static Map<String, Action> actions = new HashMap<String, Action>();
	
    @SuppressWarnings("rawtypes")
	public static View runAction(String actionName, Map<String,String> parameters) throws Exception {

        /*
         
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
        
        */
    	//读取xml，把xml信息放到actions
    	readStrutsXML();
    	
    	Action action = actions.get(actionName);
    	View view = new View();
    	if(action != null){
			Class clazz = Class.forName(action.getClassName());
    		Object obj = clazz.newInstance();
    		
    		//根据parameters调用set方法
    		initClass(clazz, obj, action, parameters);
    		
    		//调用execute方法
    		String result = execute(clazz, obj);
    		//调用所有get方法
    		Map resultParameters = getResultParameters(clazz, obj);
    		
    		view.setJsp(action.getResults().get(result));
    		view.setParameters(resultParameters);
    	}
    
    	return view;
    }

	@SuppressWarnings("rawtypes")
	private static Map getResultParameters(Class clazz, Object obj) throws Exception {
		Map<String, String> resultParameters = new HashMap<String, String>();
		Method[] methods = clazz.getMethods();
		String methodName;
		String propName;
		String propValue;
		for(Method method : methods){
			methodName = method.getName();
			if(methodName.startsWith("get") && !"getClass".equals(methodName)){
				propName = firstLetterLowerCase(methodName.substring(3, methodName.length()));
				propValue = (String) method.invoke(obj);
				resultParameters.put(propName, propValue);
			}
		}
		return resultParameters;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static String execute(Class clazz, Object obj) throws Exception{
		Method method = clazz.getMethod("execute");
		return (String)method.invoke(obj);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void initClass(Class clazz, Object obj, Action action, Map<String, String> parameters) throws Exception {
		String setMethodName;
		Method method = null;
		for (String parameter : parameters.keySet()) {
			setMethodName = "set" + firstLetterUpperCase(parameter);
			method = clazz.getMethod(setMethodName, String.class);
			method.invoke(obj, parameters.get(parameter));
		}
	}
	
	private static String firstLetterLowerCase(String string){
		char[] cs = string.toCharArray();
		cs[0] += 32;
		return String.valueOf(cs);
	}
	
	private static String firstLetterUpperCase(String string){
		char[] cs = string.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

	@SuppressWarnings("unchecked")
	private static void readStrutsXML() {
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = saxReader.read(new File("src/com/coderising/litestruts/struts.xml"));
		} catch (DocumentException e) {
			System.out.println("error:file not found");
			return ;
		}
		Element root = document.getRootElement();
		Iterator<Element> actionItr = root.elementIterator();
		Element action;
		Action act;
		Iterator<Element> resultItr;
		Element result;
		Map<String, String> results;
		while(actionItr.hasNext()){
			action = actionItr.next();
			
			resultItr = action.elementIterator();
			results = new HashMap<String, String>();
			while(resultItr.hasNext()){
				result = resultItr.next();
				results.put(result.attributeValue("name"), result.getStringValue());
			}
			
			act = new Action();
			act.setName(action.attributeValue("name"));
			act.setClassName(action.attributeValue("class"));
			act.setResults(results);
			
			actions.put(act.getName(), act);
		}
	}    
	
	static class Action {
		
		private String name;
		
		private String className;
		
		private Map<String, String> results = new HashMap<>();
		
		public Map<String, String> getResults() {
			return results;
		}

		public void setResults(Map<String, String> results) {
			this.results = results;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

	}

}

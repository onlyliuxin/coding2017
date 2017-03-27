package org.le.b;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {
	private static Map<String,ActionBean> actionMap;

	public static View runAction(String actionName, Map<String,String> parameters) {

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
		if(actionMap == null){
			actionMap = strutsXmlSax("struts2.xml");
		}
		ActionBean actionBean = actionMap.get(actionName);
    	return  processAction(actionBean,parameters);
    }   
	
	
	private static View processAction(ActionBean actionBean, Map<String, String> parameters) {
		String clazzStr = actionBean.getClazz();
		Map<String, String> result = actionBean.getResults();
		try {
			Class<?> clazz=  Class.forName(clazzStr);
			Object obj = clazz.newInstance();
			for(String key : parameters.keySet()){
				String name = "set"+(key.charAt(0)+"").toUpperCase()+key.substring(1);
				Method method = clazz.getMethod(name, String.class);
				method.invoke(obj, parameters.get(key));
			}
			Method execute = clazz.getMethod("execute");
			String resultStr = (String)execute.invoke(obj);
			String resultJsp = result.get(resultStr);
			Map<String,Object> pramMap  = new HashMap<>();
			Method meg = clazz.getMethod("getMessage");
			String resultMsg = (String)meg.invoke(obj);
			pramMap.put("message", resultMsg);
			return new View(resultJsp,pramMap);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static Map<String,ActionBean> strutsXmlSax(String path){
		DocumentFactory documentFactory = DocumentFactory.getInstance();
		SAXReader saxReader = new SAXReader(documentFactory);
		Document doc = null;
		try {
			doc = saxReader.read(path);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element rootElement = doc.getRootElement();
		Element pack = rootElement.element("package");
		List<Element> actions = pack.elements("action");
		Map<String,ActionBean> actionMap  = new HashMap<>();
		for(Element action : actions){
			Attribute name = action.attribute("name");
			Attribute clazz = action.attribute("class");
			List<Element> results = action.elements("result");
			Map<String,String> resMap  = new HashMap<>();
			for(Element result : results){
				String key = "success";
				String value = result.getTextTrim();
				Attribute rname = result.attribute("name");
				if(rname != null){
					key = rname.getValue();
				}
				resMap.put(key, value);
			}
			actionMap.put(name.getValue(), new ActionBean(name.getValue(),clazz.getValue(),resMap));
		}
		return actionMap;
	}
	
	public static class ActionBean{
		private String name;
		private String clazz;
		private Map<String,String> results;

		public ActionBean(String name, String clazz, Map<String,String> results) {
			this.name = name;
			this.clazz = clazz;
			this.results = results;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getClazz() {
			return clazz;
		}

		public void setClazz(String clazz) {
			this.clazz = clazz;
		}
		
		public Map<String,String> getResults() {
			return results;
		}

		public void setResults(Map<String,String> results) {
			this.results = results;
		}
		
	}

}

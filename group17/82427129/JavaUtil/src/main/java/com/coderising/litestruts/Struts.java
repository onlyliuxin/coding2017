package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

	private static SAXReader reader = new SAXReader();
	private static Document document;
	private static Element root;
	private static List<Element> actionList;
	static{
		try {
			document = reader.read(new File("src/main/resources/liteStruts.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		root = document.getRootElement();
		actionList = root.elements("action");
	}
	
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
    public static View runAction(String actionName, Map<String,String> parameters) {

    	if(actionName == null || actionName.equals("")){
    		System.out.println("请求名为空，错误！");
    		return null;
    	}
    	
    	View view = new View();
    	
    	String className = null;
		try {
			Map<String, String> actionInfo = getActionInfo(actionName);
			
			className = actionInfo.get("className");
			Class<?> actionClass = Class.forName(className);
			Object obj =actionClass.newInstance();
			for(Map.Entry<String, String> entry : parameters.entrySet()){
				Method setMethod = actionClass.getDeclaredMethod(("set"+toUpperCaseFirstLetter(entry.getKey())), String.class);
				setMethod.invoke(obj, entry.getValue());
			}
			Method execute = actionClass.getDeclaredMethod("execute");
			String msg = (String) execute.invoke(obj);
			view.setJsp(actionInfo.get(msg));
			
			Method[] m = actionClass.getMethods();
			Map<String,Object> map = new HashMap<String, Object>();
			for (Method method : m) {
				String methodName = method.getName();
				if(methodName.startsWith("get")
						&& !methodName.endsWith("Class")
						&& method.getParameterTypes().length == 0){
					map.put(toSubString_LowerCaseFirstLetter(methodName), method.invoke(obj));
				}
			}
			view.setParameters(map);
			
			return view;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
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
    private static Map<String,String> getActionInfo(String actionName){
    	
    	HashMap<String, String> hashMap = new HashMap<String,String>();
    	
		for (Element action : actionList) {
			Attribute attr = action.attribute("name");
			if(actionName.equals(attr.getStringValue())){
				Attribute attrClass = action.attribute("class");
				String className = attrClass.getStringValue();
				hashMap.put("className", className);
				
				@SuppressWarnings("unchecked")
				List<Element> resultList = action.elements("result");
				for (Element result : resultList) {
					Attribute attrResult = result.attribute("name");
					hashMap.put(attrResult.getStringValue(), result.getText());
				}
				break;
			}
		}
		return hashMap;
    }
    
    /**
     * return a String upcased the first letter
     * @param param
     * @return
     */
    private static String toUpperCaseFirstLetter(String param){
    	if(param == null || param.length() == 0)
    		return "";
    	if(param.length() == 1)
    		return param.substring(0, 1).toUpperCase();
    	
    	return param.substring(0, 1).toUpperCase()+param.substring(1);
    }
    private static String toSubString_LowerCaseFirstLetter(String param){
    	if(!param.startsWith("get"))
    		return null;
    	int length = param.length();
    	if(length < 4)
    		return "";
    	
    	param = param.substring(3);
    	return param.substring(0, 1).toLowerCase()+param.substring(1);
    }

}

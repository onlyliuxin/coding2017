package com.coderising.iterstruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {
	@SuppressWarnings("unchecked")
	public static View runAction(String actionName, Map<String,String> parameters) {
		View view = new View();
		Map<String, String> map = new HashMap<>();
//		view.setParameters(parameters);
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read("src/com/coderising/iterstruts/struts.xml");
			Element rootElement = document.getRootElement();
			for(Iterator<Element> iter = rootElement.elementIterator(); iter.hasNext();){
				Element element = iter.next();
				if ("action".equals(element.getName()) && actionName.equals(element.attributeValue("name"))) {// 找到actionName对应的element
					String className = element.attributeValue("class");
					Class<?> targetClass = Class.forName(className);
					Object instance = targetClass.newInstance();
					Set<String> keySet = parameters.keySet();
					//setter
					for (String key : keySet) {
						Character firstLit =Character.toUpperCase(key.charAt(0));
						String leftParts = key.substring(1);
						String methodName ="set" + firstLit + leftParts;
						Method method = targetClass.getMethod(methodName, String.class);
						method.invoke(instance, parameters.get(key));
					}
					
					Method execute = targetClass.getMethod("execute");
					String result = (String) execute.invoke(instance);
					Map<String, String> resultMap = new HashMap<>();
					for(Iterator<Element> innerIter = element.elementIterator(); innerIter.hasNext() ;){
						Element resultElement = innerIter.next();
						if(resultElement.getName().equals("result")){
							String resultValue = resultElement.attributeValue("name");
							String jspPath = resultElement.getStringValue();
							resultMap.put(resultValue, jspPath);
						}
					}
					String targetJspPath = resultMap.get(result);
					view.setJsp(targetJspPath);
					//getter
					for (Method method : targetClass.getMethods()) {
						if(method.getName().startsWith("get") && method.getReturnType()==String.class) {
							System.out.println(method.getName());
							String key = method.getName().substring(3);
							key = Character.toLowerCase(key.charAt(0)) + key.substring(1);
							String value = (String) method.invoke(instance);
							map.put(key, value);
						}
					}
					view.setParameters(map);
					return view;
				}
			}
		} catch (DocumentException | ClassNotFoundException 
				| NoSuchMethodException | SecurityException | InstantiationException 
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
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
    	
    	return view;
    } 
	public static void main(String[] args) {
		runAction(null, null);
	}
}

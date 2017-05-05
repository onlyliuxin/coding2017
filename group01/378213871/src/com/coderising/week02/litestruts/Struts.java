package com.coderising.week02.litestruts;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {

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
    	try {
    		//将xml转换为输出流
    		InputStream inputStream = Struts.class.getResourceAsStream("struts.xml");
    		//创建SAXReader读取器，专门用于读取xml
    		SAXReader saxReader = new SAXReader();
    		//读取xml文件，获得Document对象
    		Document document = saxReader.read(inputStream);
    		//actionName对应的类名
    		String className = "";
    		//获取文档的根节点
    		Element rootElement = document.getRootElement();
    		Iterator iterator = rootElement.elementIterator("action");
    		//actionName对应的action
    		Element targetAction = null;
    		//对action节点下的所有子节点进行遍历  
    		while (iterator.hasNext()) {
    			Element element = (Element) iterator.next();
    			String name = element.attributeValue("name");
    			if(name.equals(actionName)) {
    				className = element.attributeValue("class");
    				targetAction = element;
    				break;
    			}
    		}
    		
    		Class<?> clazz = Class.forName(className);
    		Object instance = clazz.newInstance();
    		
    		Set<String> keySet = parameters.keySet();
    		for(String key : keySet) {
    			// 将变量名称拼成set方法名
    			String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
    			Class<?> type = clazz.getDeclaredField(key).getType();
    			Method method = clazz.getDeclaredMethod(methodName, type);
    			//依次调用相应的set方法
    			method.invoke(instance, parameters.get(key));
    		}
    		//通过反射调用对象的exectue方法,并获得返回值
    		String result = (String)clazz.getDeclaredMethod("execute").invoke(instance);
    		
    		Method[] declaredMethods = clazz.getDeclaredMethods();
    		HashMap<String, String> map = new HashMap<>();
    		for (int i = 0; i < declaredMethods.length; i++) {
    			if (declaredMethods[i].getName().startsWith("get")) {
    				String fieldValue = (String) declaredMethods[i].invoke(instance);
    				String fieldName = declaredMethods[i].getName().substring(3, 4).toLowerCase() 
    								+ declaredMethods[i].getName().substring(4);
    				map.put(fieldName, fieldValue);
    			}
    		}
    		
    		View view = new View();
    		view.setParameters(map);
    		
    		Iterator elementIterator = targetAction.elementIterator("result");
    		while(elementIterator.hasNext()) {
    			Element element = (Element) elementIterator.next();
    			if (result.equals(element.attributeValue("name"))) {
    				view.setJsp(element.getText());
    			}
    		}
    		
    		return view;
    		
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }    

}

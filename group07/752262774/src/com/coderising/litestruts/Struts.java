package com.coderising.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Struts {

	private static Map<String, String> targetMap = null;

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

		targetMap = getTargetMap(actionName);
		if(!targetMap.containsKey(actionName)) {
			throw new Exception("this xml file not found" + actionName + "action");
		}
		String className = targetMap.get(actionName);
		Class<?> classEntity = Class.forName(className);
		Object obj = classEntity.newInstance();
		Field[] fields = classEntity.getDeclaredFields();

		for(Field field: fields) {
			field.setAccessible(true);
			if(parameters.containsKey(field.getName())) {
				field.set(obj, parameters.get(field.getName()));
			}
		}

		Method execute = classEntity.getMethod("execute");
		String result = (String)execute.invoke(obj);
		String jsp = targetMap.get(result);
		Field message = classEntity.getDeclaredField("message");
		message.setAccessible(true);
		Map<String, String> parats = new HashMap<String, String>();
		parats.put("message", (String)message.get(obj));

		View view = new View();
		view.setJsp(jsp);
		view.setParameters(parats);

    	return view;
    }

	private static Map<String, String> getTargetMap(String actionName) throws DocumentException {
		Map<String,String> targetMap = new HashMap<String, String>();
		//创建SAXReader对象
		SAXReader reader = new SAXReader();
		//读取文件 转换成Document
		Document document = reader.read(new File("src/com/coderising/litestruts/struts.xml"));
		//获取根节点元素对象
		Element root = document.getRootElement();
		List<Element> actionList = root.elements("action");
		for(Element action: actionList) {
			org.dom4j.Attribute attribute = action.attribute("name");
			if(attribute.getValue().equals("login")){
				targetMap.put(attribute.getValue(), action.attribute("class").getValue());
				List<Element> resultList = action.elements("result");
				for(Element result: resultList) {
					targetMap.put(result.attribute("name").getValue(),""+result.getData());
				}
				break;
			}
		}
		return targetMap;
	}

}

package com.github.FelixCJF.coding2017.coderising.litestruts;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;




public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

        View view = new View();
         
    	try {
		//		0. 读取配置文件struts.xml
		    	//0.1将xml加载进内存中
		    	SAXReader reader = new SAXReader();
		    	Document document = null;
		    	try{
		    		document = reader.read("src/com/github/FelixCJF/coding2017/coderising/litestruts/struts.xml");
		    	}catch (Exception e) {
					e.printStackTrace();
				}
		    	//0.2读取根元素
		    	Element rootElement = document.getRootElement();
		    	//0.3根据根元素获取其子元素
		    	List<Element> actionElements = rootElement.elements("action");
		    	for (int i = 0; i < actionElements.size(); i++) {
		    		Element actionElement = actionElements.get(i);
		    		if (actionName.equals(actionElement.attributeValue("name"))) {
		// 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		    			String className = actionElement.attributeValue("class");
		    			Class clazz = Class.forName(className);
						Object object = clazz.newInstance();
		//		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		//		("name"="test" ,  "password"="1234") ,     	
		//		那就应该调用 setName和setPassword方法
						for (Map.Entry<String, String> entry : parameters.entrySet()) {
							PropertyDescriptor descriptor = new PropertyDescriptor(entry.getKey(), clazz);
							Method method = descriptor.getWriteMethod();
							method.invoke(object, entry.getValue());
						}
						//		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
						Method execute = clazz.getMethod("execute");
						String result = (String) execute.invoke(object);
		//		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		//		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
						Field[] fields = clazz.getDeclaredFields();
						HashMap<String, Object> map = new HashMap<>();
						for (Field field : fields) {
							PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), clazz);
							Method method = descriptor.getReadMethod();
							Object value = method.invoke(object);
							map.put(field.getName(), value);
						}
						//		放到View对象的parameters
						view.setParameters(map);
		//		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		//		放到View对象的jsp字段中。
						List<Element> resultElements = actionElement.elements("result");
						for (int j = 0; j < resultElements.size(); j++) {
							Element resultElement = resultElements.get(j);
							if (result.equals(resultElement.attributeValue("name"))) {
								view.setJsp(resultElement.getText());
								return view;
							}
						}	
					}
		    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }    
}
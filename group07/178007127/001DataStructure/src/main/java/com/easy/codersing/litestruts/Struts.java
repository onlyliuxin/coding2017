package com.easy.codersing.litestruts;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.easy.core.AppUtils;


/**
 * 下面是一段糟糕的代码，有时间在重构下！！！
 * TODO：抽离三个方法，actin解析方法，view解析方法，jsp映射方法
 * @author 1744
 *
 */
public class Struts {

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
    	SAXReader reader = new SAXReader();
    	File file =new File("src/main/java/com/easy/codersing/litestruts/struts.xml");
		Document doc=reader.read(file);
		Element el_root=doc.getRootElement();
	 	Iterator it= el_root.elementIterator();
	 	while(it.hasNext()){
	 		Object obj=it.next();
	 		Element el_action = (Element)obj;
	 		//System.out.println(el_action.attributeValue("name"));
	 		String el_action_name=el_action.attributeValue("name");
	 		
	 		
	 		if(actionName.equals(el_action_name)){
	 			String el_action_class=el_action.attributeValue("class");
	 			Class clazz = Class.forName(el_action_class);
	 			Object actionObj = clazz.newInstance();
	 			
	 			for(String map_key:parameters.keySet()){
	 				String set_method_name = "set"+AppUtils.fistLetterToUpper(map_key);
	 				Method set_method =clazz.getMethod(set_method_name, String.class);
	 				set_method.invoke(actionObj,parameters.get(map_key));
	 			}
	 			
	 			Method execute_method = clazz.getMethod("execute");
	 			String execute_result = (String)execute_method.invoke(actionObj);
	 			
	 			Method get_message_method = clazz.getMethod("getMessage");
	 			String get_message_result=(String)get_message_method.invoke(actionObj);
	 			
	 			Map<String, String> map=new HashMap<String,String>();
	 			map.put("message", get_message_result);
	 			
	 			View view =new View();
	 			view.setParameters(map);
	 			
	 			Iterator it_result = el_action.elementIterator();
	 			while(it_result.hasNext()){
	 				Element el_result =(Element)it_result.next();
	 				if(el_result.attributeValue("name").equals(execute_result)){
	 					view.setJsp(el_result.getText());
	 				}
	 			}
	 			
	 			return view;
	 			
	 			
	 		}
	 	}
    	
    	return null;
    }    
    
  
    

}

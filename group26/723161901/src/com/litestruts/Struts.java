package com.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.litestruts.strutsBean.Action;



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
    	StrutsXmlReader strutsXml = new StrutsXmlReader(new File("src/com/litestruts/struts.xml"));
		View view = new View();
    	try {
			HashMap actMap = (HashMap) strutsXml.loadXml();
			Action act = (Action) actMap.get(actionName);
			Class clazz = Class.forName(act.getClazz());
			Object obj = clazz.newInstance();
			HashMap paraMap = act.getParameters();
			Iterator<Map.Entry<String, String>> iteraPara = parameters.entrySet().iterator();
			
			while(iteraPara.hasNext()){
				Entry<String, String> itera = iteraPara.next();
				Field field = clazz.getDeclaredField(itera.getKey());
				field.setAccessible(true);
				field.set(obj, itera.getValue());
			}
			
			Method method = clazz.getMethod("execute", null);
			String results = (String)method.invoke(obj, null);
			Field[] getFields = clazz.getDeclaredFields();
			HashMap getMapPara = new HashMap();
			for (Field field : getFields) {
				field.setAccessible(true);
				String getFiledName = field.getName();
				Object objValue = field.get(obj);
				getMapPara.put(getFiledName, objValue);
			}
	
			view.setParameters(getMapPara);
			view.setJsp((String)paraMap.get(results));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    
    	return view;
    }    
   

}

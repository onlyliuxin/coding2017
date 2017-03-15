package com.coderising.litestruts;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;



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
    	String path = Struts.class.getResource("").getPath()+"struts.xml";
		Element element = Dom4jUtil.parseXml(path);
		Map<String, String> attribute = Dom4jUtil.getAttribute(element);
		String className = attribute.get(actionName);
		try {
			Class clazz = Class.forName(className);
			Object o = clazz.newInstance();
			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				Field field = clazz.getDeclaredField(key);
				Method method = clazz.getDeclaredMethod(BeanUtil.setter(field.getName()),String.class);
				method.invoke(o, value);
			}
			Method method = clazz.getDeclaredMethod("execute",null);
			String str = (String) method.invoke(o);
			Field[] fields = clazz.getDeclaredFields();
			Map<String, String> map = new HashMap<String, String>();
			for (Field field : fields) {
				String fieldName = field.getName();
				Method method2 =  clazz.getDeclaredMethod(BeanUtil.getter(fieldName),null);
				String ret = (String) method2.invoke(o, null);
				map.put(fieldName, ret);
			}
			View view = new View();
			view.setParameters(map);
			Map<String, String> result = Dom4jUtil.getJspMap(element, actionName);
			view.setJsp(result.get(str));
			return view;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }    
    
   public static void main(String[] args) {
	   String actionName = "login";
	   Map<String,String> params = new HashMap<String,String>();
       params.put("name","test");
       params.put("password","1234");
       runAction(actionName, params);
   }
}

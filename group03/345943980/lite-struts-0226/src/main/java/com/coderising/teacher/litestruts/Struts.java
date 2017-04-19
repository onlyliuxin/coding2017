package com.coderising.teacher.litestruts;

import java.lang.reflect.Method;
import java.util.Map;

public class Struts {
	
	static Configuration config = new Configuration("struts1.xml");
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
		String clzName = config.getClassName(actionName);
		if(clzName == null){
			return null;
		}
    	try {
    		Class<?> clz = Class.forName(clzName);
    		Object obj = clz.newInstance();
			ReflectionUtil.setParameters(obj, parameters);
			Method method = clz.getDeclaredMethod("execute");
    		String resultName = (String)method.invoke(obj);
			Map<String,Object> params = ReflectionUtil.getParamterMap(obj);
			String resultView = config.getResultView(actionName, resultName);
    		View view = new View();
    		view.setJsp(resultView);
    		view.setParameters(params);
    		return view;
 		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return null;
    }    
}

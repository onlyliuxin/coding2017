package cn.mark.work0226.litestruts;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {
	
	private static final Configuration CFG = new Configuration("struts.xml");

    public static View runAction(String actionName, Map<String,String> parameters) {
	

        /*
         
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success
		
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
        
        */
    	String clzName = CFG.getClassName(actionName);
    	
    	if (clzName == null){
    		return null;
    	}
    	
    	try {
			Class<?> clz = Class.forName(clzName);
			Object action = clz.newInstance();
			
			ReflectionUtil.setParameters(action, parameters);
			
			Method m = clz.getDeclaredMethod("execute");
			String resultName = (String)m.invoke(action);
			
			Map<String,Object> params = ReflectionUtil.getParameterMap(clz);
			String resultView = CFG.getResultView(actionName, resultName);
			View view = new View();
			view.setParameters(params);
			view.setJsp(resultName);
			return view;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    	
    	
    	return null;
    }
}

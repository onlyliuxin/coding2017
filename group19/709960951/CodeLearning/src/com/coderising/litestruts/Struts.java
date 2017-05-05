package com.coderising.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



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
    	
    	StrutsManager sm=StrutsManager.getInstance();
    	StructsAction actionConfig=sm.getAction(actionName);
    	Class<?> actionClz;
		try {
			actionClz = Class.forName(actionConfig.getActionClass());
			Object actionObj=actionClz.newInstance();
			//设置参数值
			for(String key:parameters.keySet())
			{
				if(key!=null && key.length()>0)
				{
					String value=parameters.get(key);
					String methodName="set"+key.substring(0, 1).toUpperCase()+key.substring(1);
					Method method=actionClz.getMethod(methodName, String.class);
					method.invoke(actionObj, value);
				}
			}
			//执行
			Method executeMethod=actionClz.getMethod("execute");
			String result=(String)executeMethod.invoke(actionObj);
			
			//生成View对象
			View view=new View();
			String jsp=actionConfig.getResults().get(result);
			view.setJsp(jsp);
			//设置View的parameters
			Map<String, Object> vParams=new HashMap<>();
			for(Method method:actionClz.getMethods())
			{
				String methodName=method.getName();
				if(methodName.startsWith("get"))
				{
					Object paramValue=method.invoke(actionObj);
					String paramKey=methodName.substring(3, 4).toLowerCase()+methodName.substring(4);
					vParams.put(paramKey, paramValue);
				}
			}
			view.setParameters(vParams);
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
    	return new View();
    }    

}

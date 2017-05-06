package com.coding.litestruts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;



public class Struts {



    public static View runAction(String actionName, Map<String,String> parameters) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {

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
            Configuration cfg = new Configuration("struts.xml");

            String clzName = cfg.getClassName(actionName);
            if (clzName == null) {
                return null;
            }
            String classPath = Struts.class.getClassLoader().getResource("").getPath();

            System.out.println("clzName = " + clzName);
            System.out.println("classPath = " + classPath);
            Class<?> clz = Class.forName(clzName);

            Object o = clz.newInstance();
            ReflectionUtil.setParameters(o, parameters);  //将参数注入属性
            Method m = clz.getDeclaredMethod("execute");
            String resultName = (String) m.invoke(o);    //执行execute方法

            Map<String, Object> params = ReflectionUtil.getParamterMap(o); //根据action获取model参数

            String jsp = cfg.getResultView(actionName, resultName); //通过返回结果去拿视图名

            View view = new View();
            view.setJsp(jsp);
            view.setParameters(params);
            return view;
        } catch (IOException | InstantiationException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

}

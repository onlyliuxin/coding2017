package cn.net.pikachu.litestruts;

import cn.net.pikachu.litestruts.xstream.Action;
import cn.net.pikachu.litestruts.xstream.Result;
import com.thoughtworks.xstream.XStream;

import java.io.*;
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
        try {
            // 目标文件
//            File file = new File("/home/pikachu/Documents/2017编程提高/coding2017/group13/2931408816/lesson2/src/main/java/cn/net/pikachu/litestruts/struts.xml");

            File file = new File("D:\\src\\java\\coding2017\\group13\\2931408816\\lesson2\\src\\main\\java\\cn\\net\\pikachu\\litestruts\\struts.xml");
            XStream xStream = new XStream();
            // 扫描注解
            xStream.processAnnotations(new Class[]{
                    cn.net.pikachu.litestruts.xstream.Struts.class,Result.class,Action.class
            });
            // struts.xml映射的类
            cn.net.pikachu.litestruts.xstream.Struts struts = (cn.net.pikachu.litestruts.xstream.Struts) xStream.fromXML(file);
            Class clazz = null;
            Action action = null;
            // 找到执行action的类
            for (Action a :
                    struts.getActions()) {
                if (a.getName().equals(actionName)){
                    System.out.println("执行 "+actionName);
                    clazz = Class.forName(a.getClazz());
                    action = a;
                    break;
                }
            }
            if (clazz == null){
                throw new ClassNotFoundException("执行action "+actionName+" 所对应的类");
            }
            // 参数注入
            Object o = clazz.newInstance();
            // java可怜的lambda表达式
            final Class c = clazz;
            parameters.forEach((k,v)->{
                try {
                    String methodName = "set"+k.substring(0,1).toUpperCase()+k.substring(1);
                    System.out.println("调用"+methodName);
                    Method method = c.getMethod(methodName,String.class);
                    method.invoke(o,v);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            // 执行
            Method method = clazz.getMethod("execute");
            String name = (String) method.invoke(o);
            // 可怜又可恶的Java lambda表达式
            final String[] results = new String[]{null};
            action.getResults().forEach(it -> {
                if (it.getName().equals(name)){
                    results[0]=it.getResult();
                }
            });
            if (results[0]==null){
                throw new Exception("未找到与action匹配的结果");
            }
            // 获取最后的结果
            View view = new View();
            view.setJsp(results[0]);
            /**
             * 没有正确理解老师的意思
             */
            /*
            String message = (String) clazz.getMethod("getMessage").invoke(o);
            parameters.put("message",message);
            view.setParameters(parameters);
            */
            Map<String,Object> map = new HashMap<String,Object>();
            Method[] methods = clazz.getMethods();
            for (Method m : methods) {
                String methodName = m.getName();
                if (methodName.startsWith("get")){
                    String attr = methodName.substring(3);
                    attr=attr.substring(0,1).toLowerCase()+attr.substring(1);
                    Object value = m.invoke(o);
                    map.put(attr,value);
                }
            }
            view.setParameters(map);
            return view;
        }catch (Exception e){
            e.printStackTrace();
        }
    	return null;
    }    

}

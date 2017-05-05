package com.aaront.exercise;

import com.aaront.exercise.pojo.Action;
import com.aaront.exercise.pojo.Result;
import com.aaront.exercise.pojo.Structs;
import org.apache.commons.digester.Digester;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class LiteStruts {

    private DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

    public View runAction(String actionName, Map<String, String> parameters) {

        /*
         
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的execute 方法， 并获得返回值，例如"success"
		
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
        
        */
        URL resource = readProfiles("struts.xml");
        Structs structs = parseXML(resource);
        Action action = structs.getActions().stream().filter(a -> StringUtils.equals(a.getName(), actionName)).findAny().orElseThrow(() -> new RuntimeException("Action不存在"));

        try {
            Class<?> actionClass = Class.forName(action.getClazz());
            Object instance = actionClass.newInstance();
            parameters.entrySet().stream().filter(entry -> StringUtils.isNotBlank(entry.getKey())).forEach(entry -> {
                String propertyName = entry.getKey();
                String propertyValue = entry.getValue();
                String setterMethodName = StringUtils.prependIfMissing(StringUtils.capitalize(propertyName), "set");
                try {
                    Class<?> propertyType = actionClass.getDeclaredField(propertyName).getType();
                    Method method = actionClass.getMethod(setterMethodName, propertyType);
                    method.invoke(instance, propertyValue);
                } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            Method execute = actionClass.getMethod("execute");

            Object methodReturnValue = execute.invoke(instance);
            String viewPath = action.getResults().stream().filter(result -> result.getName().equals(methodReturnValue)).map(Result::getValue).findAny().orElseThrow(() -> new RuntimeException("没有对应的view"));
            Map map = new HashMap();
            Arrays.stream(actionClass.getDeclaredMethods()).filter(method -> method.getName().startsWith("get")).forEach(method -> {
                try {
                    Object result = method.invoke(instance);
                    String propertyName = StringUtils.uncapitalize(method.getName().substring(3));
                    map.put(propertyName, result);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            View view = new View();
            view.setJsp(viewPath);
            view.setParameters(map);
            return view;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("处理Action的类不存在");
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO: 17/2/27 这里可以优化成遍历所有的构造函数之后再抛错
            e.printStackTrace();
            throw new RuntimeException("处理Action的类没有默认的构造函数");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("处理Action的类没有execute函数");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("调用execute方法出错");
        }
    }

    private URL readProfiles(String filePath) {
        ClassLoader classLoader = LiteStruts.class.getClassLoader();
        URL resource = classLoader.getResource(filePath);
        if (resource == null) throw new RuntimeException("文件不存在");
        return resource;
    }

    private Structs parseXML(URL resource) {
        Digester digester = new Digester();
        digester.addObjectCreate("struts", Structs.class);
        digester.addObjectCreate("struts/action", Action.class);
        digester.addSetProperties("struts/action", new String[]{"name", "class" }, new String[]{"name", "clazz" });
        digester.addSetNext("struts/action", "addAction");
        digester.addObjectCreate("struts/action/result", Result.class);
        digester.addSetProperties("struts/action/result");
        digester.addBeanPropertySetter("struts/action/result", "value");
        digester.addSetNext("struts/action/result", "addResult");
        try {
            return (Structs) digester.parse(resource);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
            throw new RuntimeException("解析XML文件出错");
        }
    }
}

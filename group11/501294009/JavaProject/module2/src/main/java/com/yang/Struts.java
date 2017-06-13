package com.yang;

import com.yang.bean.Action;
import com.yang.bean.Result;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Struts {

    private static View view;

    public static View runAction(String actionName, Map<String, String> parameters) {
        view = null;

        try {
            com.yang.bean.Struts struts = (com.yang.bean.Struts) getBean(com.yang.bean.Struts.class, "struts.xml");

            List<Action> actions = struts.getActions();
            actions.forEach((Action action) -> {
                try {
                    renderView(actionName, parameters, action);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private static void renderView(String actionName, Map<String, String> parameters, Action action) throws Exception {
        if (!action.getName().equalsIgnoreCase(actionName)) {
            return;//same as continue
        }
        String packageName = action.getPackageName();
        Class<?> aClass;
        aClass = Class.forName(packageName);
        Object instance = aClass.newInstance();

        Method[] methods = aClass.getDeclaredMethods();
        Map<String, Method> methodMap = new HashMap();
        for (Method method : methods) {
            method.setAccessible(true);
            methodMap.put(method.getName(), method);
        }

        invokeMethod("setName", parameters.get("name"), instance, methodMap);
        invokeMethod("setPassword", parameters.get("password"), instance, methodMap);
        String jsp = invokeMethod("execute", null, instance, methodMap);

        String message = invokeMethod("getMessage", null, instance, methodMap);

        Map params = new HashMap();
        params.put("message", message);
        view = new View();
        view.setParameters(params);
        List<Result> results = action.getResults();
        results.forEach(temp -> {
            if (temp.getName().equalsIgnoreCase(jsp)) {
                view.setJsp(temp.getValue());
            }

        });
    }



    private static Object getBean(Class clz, String fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream inputStream = Struts.class.getClassLoader().getResourceAsStream(fileName);
        return unmarshaller.unmarshal(inputStream);
    }


    private static String invokeMethod(String methodName, String param, Object instance, Map<String, Method> map) throws IllegalAccessException, InvocationTargetException {
        Method setNameMethod = map.get(methodName);
        String invoke = null;
        if (setNameMethod != null) {
            if (param == null) {
                invoke = (String) setNameMethod.invoke(instance);
                return invoke;
            }
            invoke = (String) setNameMethod.invoke(instance, param);
        }


        return invoke;
    }

}
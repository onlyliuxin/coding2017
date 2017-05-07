package com.bruce.homework0305.demostruts;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Struts {

    private static final Configuration cfg = new Configuration("struts.xml");

    public static View runAction(String actionName, Map<String,String> parameters) throws DocumentException,
            ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

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

        String className = cfg.getClassName(actionName);
        if(className == null){
            return null;
        }
        Class<?> clz = Class.forName(className);
        Object action = clz.newInstance();
        ReflectUtil.setParameters(action, parameters);
        Method execute = clz.getDeclaredMethod("execute");
        String resultName = (String) execute.invoke(action);
        String resultView = cfg.getResultView(actionName,resultName);
        Map<String, Object> params = ReflectUtil.getParameters(action);
        View view = new View();
        view.setJsp(resultView);
        view.setParameters(params);
        return view;
    }
}

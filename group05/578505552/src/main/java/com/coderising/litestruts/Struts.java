package com.coderising.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
        Action action = matchAction(parseXml("/struts.xml"), actionName);
        System.out.println();
        try {
            Class<?> clazz = Class.forName(action.getClassName());
            Object instance = clazz.newInstance();
            for (String key : parameters.keySet()){
                String methodName = "set" + key.substring(0,1).toUpperCase() + key.substring(1).toLowerCase();
                Method setMethod = clazz.getMethod(methodName, String.class);
                setMethod.invoke(instance, parameters.get(key));
            }
            Method exectueMethod = clazz.getMethod("execute");
            String result = (String)exectueMethod.invoke(instance);

            Map<String, Object> hashMap = new HashMap<String, Object>();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields){
                String name = field.getName();
                String methodName = "get" + name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
                Method method = clazz.getMethod(methodName);
                if (method != null){
                    Object res = method.invoke(instance);
                    hashMap.put(name, res);
                }
            }

            View view = new View();
            view.setJsp((String)action.getResults().get(result));
            view.setParameters(hashMap);
            return view;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "yangsongbao");
        Struts.runAction("login", map);
    }

    private static Element parseXml(String resourcePath){

        InputStream resourceAsStream = Struts.class.getResourceAsStream(resourcePath);
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            return rootElement;
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("fail to parse xml");
    }

    private static Action matchAction(Element rootElement, String actionName){

        List actions = rootElement.elements("action");
        Iterator iterator = actions.iterator();
        Action action = new Action();
        while (iterator.hasNext()){
            Element actionElement = (Element) iterator.next();
            String nameAttributeValue = actionElement.attributeValue("name");
            if (actionName.equals(nameAttributeValue)){
                action.setName(nameAttributeValue);
                action.setClassName(actionElement.attributeValue("class"));
                List results = actionElement.elements("result");
                Map<String, String> resultMap = new HashMap<String, String>();
                Iterator it = results.iterator();
                while (it.hasNext()){
                    Element resultElement = (Element)it.next();
                    resultMap.put(resultElement.attributeValue("name"), (String)resultElement.getData());
                }
                action.setResults(resultMap);
            }
        }

        return action;
    }




}

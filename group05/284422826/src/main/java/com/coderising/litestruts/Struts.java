package com.coderising.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Struts {
    private static String className = null;

    /*  0. 读取配置文件struts.xml

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
    public static View runAction(String actionName, Map<String, String> parameters) {
        View view = new View();
        Element element = null;

        element = parseXml(actionName);
        Class clazz = null;
        Object obj = null;
        try {
            clazz = Class.forName(className);
            obj = clazz.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        if (setValue(parameters, clazz, obj)) return null;

        String result = null;

        try {
            assert clazz != null;
            Method method = clazz.getDeclaredMethod("execute");
            result = (String) method.invoke(obj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        Map map = new HashMap();
        Field[] field = clazz.getDeclaredFields();
        try {
            for (Field f : field) {
                f.setAccessible(true);
                String fieldName = f.toString().substring(f.toString().lastIndexOf(".")+1);
                map.put(fieldName, f.get(obj));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        view.setParameters(map);
        view.setJsp(getResultString(result, element));

        return view;
    }

    private static boolean setValue(Map<String, String> parameters, Class clazz, Object obj) {
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();

            if (name == null || "".equals(name)) {
                System.out.println("属性名称不能为空！");
                return true;
            }
            System.out.println("Key = " + name + ", Value = " + value);
            try {
                Field field = clazz.getDeclaredField(name);
                field.setAccessible(true);
                field.set(obj, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static Element parseXml(String name) {
        Element el = null;
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File("src/com/coderising/litestruts/struts.xml"));
            Element root = document.getRootElement();
            Iterator it = root.elementIterator();
            while (it.hasNext()) {
                Element element = ((Element) it.next());
                if (name.equals(element.attributeValue("name"))) {
                    className = element.attributeValue("class");
                    el = element;
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return el;
    }

    private static String getResultString(String name, Element element) {
        String result = null;
        Iterator iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element e = ((Element) iterator.next());
            if (name.equals(e.attributeValue("name"))) {
                result = e.getText();
            }
        }
        return result;
    }

}

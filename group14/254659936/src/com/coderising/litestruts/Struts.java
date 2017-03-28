package com.coderising.litestruts;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters) {
        // 0. 读取配置文件struts.xml
        Element rootXmlElement = getRootXmlElement();

        String className = getClassFromAction(actionName, rootXmlElement);

        View view = new View();

        try {
            Class actionClass = null;
            actionClass = Class.forName(className);
            Object actionIns = null;
            actionIns = actionClass.newInstance();

            Method[] methods = actionClass.getMethods();
            Set<Map.Entry<String, String>> entries = parameters.entrySet();
            // 遍历map，调用set方法
            for (Map.Entry<String, String> map : entries) {
                String key = "set" + map.getKey();
                String value = map.getValue();
                Method method = null;
                for (int i = 0; i < methods.length; i++) {
                    if (key.equalsIgnoreCase(methods[i].getName())) {
                        method = methods[i];
                        break;
                    }
                }
                method.invoke(actionIns, value);
                System.out.println("execute set method：" + key + " " + value);
            }

            // 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
            Method method = actionClass.getMethod("execute");
            Object executeResult = method.invoke(actionIns);
            System.out.println("execute method result：" + executeResult.toString());

            // 3. 通过反射找到对象的所有getter方法（例如 getMessage）,
            // 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
            // 放到View对象的parameters
            HashMap<String, Object> viewParametersMap = new HashMap<>();
            String methodName;
            Object methodResult;
            for (int i = 0; i < methods.length; i++) {
                methodName = methods[i].getName();
                if (methodName.startsWith("get")) {
                    methodResult = methods[i].invoke(actionIns);
                    viewParametersMap.put((char) (methodName.charAt(3) - 32)
                                    + methodName.substring(3, methodName.length() - 1),
                            methodResult);

                    System.out.println("execute get method：" + methodName + " " + methodResult);
                }
            }
            view.setParameters(viewParametersMap);

            // 4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
            // 放到View对象的jsp字段中。
            view.setJsp("" + executeResult);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return view;
    }

    private static Element getRootXmlElement() {
        SAXReader sax = new SAXReader();
        File xmlFile = new File("src/com/coderising/litestruts/struts.xml");
        Document document = null;
        try {
            document = sax.read(xmlFile);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        System.out.println("root：" + root.getName());
        return root;
    }

    private static String getClassFromAction(String actionName, Element root) {
        if (null == actionName || actionName.isEmpty() || null == root) {
            return null;
        }
        List<Element> elements = root.elements();
        for (Element element : elements) {
            Attribute actionAttribute = element.attribute("name");
            if (actionName.equals(actionAttribute.getValue())) {
                Attribute classAttribute = element.attribute("class");


                List<Element> elements1 = element.elements();
                elements1.get(0).attribute("name").getValue();
                elements1.get(0).getData();


                return classAttribute.getValue();
            }
        }
        return null;
    }

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


}

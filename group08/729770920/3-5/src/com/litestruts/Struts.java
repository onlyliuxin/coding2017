package com.litestruts;

import com.view.View;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Struts {

    @SuppressWarnings("TryWithIdenticalCatches")
    private static Document init() {
        try {
            File file = new File("res/struts.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Element findStrutsElement(Document document) {
        NodeList nodeList = document.getElementsByTagName("struts");
        if (nodeList.getLength() == 0) {
            throw new NoSuchElementException("No tag named struts");
        }
        return (Element) nodeList.item(0);
    }

    private static Element findElementByName(String actionName) {
        Document d = init();
        Element element = findStrutsElement(d);
        NodeList nodeList = element.getElementsByTagName("action");
        if (nodeList.getLength() == 0) {
            throw new NoSuchElementException("No tag named action");
        }
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Element e = (Element) nodeList.item(i);
            String tmp = e.getAttribute("name");
            if (tmp.equals(actionName)) {
                return e;
            }
        }
        return null;
    }

    private static String findClassNameByElement(Element actionElement) {
        return actionElement.getAttribute("class");
    }

    private static Class findClassByName(String actionClassName) {
        try {
            return Class.forName(actionClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked TryWithIdenticalCatches")
    private static Object createActionObject(Class actionClass) {
        try {
            Constructor actionClassConstructor = actionClass.getConstructor();
            return actionClassConstructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    @SuppressWarnings("unchecked")
    private static Method findSetterFromClass(String setterName, Class actionClass) {
        try {
            Class[] paramTypes = {String.class};
            return actionClass.getDeclaredMethod("set" + capitalize(setterName), paramTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    private static void invokeSetter(Object o, Method setter, String param) {
        try {
            setter.invoke(o, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void invokeSetters(Object o, Class c, Map<String, String> p) {
        for (Map.Entry<String, String> pair : p.entrySet()) {
            Method setter = findSetterFromClass(pair.getKey(), c);
            invokeSetter(o, setter, pair.getValue());
        }
    }

    @SuppressWarnings("unchecked TryWithIdenticalCatches")
    private static String invokeExecute(Object o, Class c) {
        try {
            Method execute = c.getDeclaredMethod("execute");
            return (String) execute.invoke(o);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    private static String invokeGetter(Object o, Method getter) {
        try {
            return (String) getter.invoke(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, String> invokeGetters(Object o, Class c) {
        Map<String, String> map = new HashMap<>();
        for (Method method : c.getDeclaredMethods()) {
            String s = method.getName();
            if (s.substring(0, 3).equals("get")) {
                String dataMember = s.substring(3).toLowerCase();
                map.put(dataMember, invokeGetter(o, method));
            }
        }
        return map;
    }

    private static String findJspByResult(String result, Element actionElement) {
        NodeList nodeList = actionElement.getElementsByTagName("result");
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Element element = (Element) nodeList.item(i);
            if (element.getAttribute("name").equals(result)) {
                return element.getTextContent();
            }
        }
        return "";
    }


    public static View runAction(String actionName, Map<String, String> parameters) {

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

        Element actionElement = findElementByName(actionName);
        String actionClassName = findClassNameByElement(actionElement);
        Class actionClass = findClassByName(actionClassName);

        Object actionObject = createActionObject(actionClass);

        invokeSetters(actionObject, actionClass, parameters);

        String result = invokeExecute(actionObject, actionClass);

        Map<String, String> viewParams = invokeGetters(actionObject, actionClass);

        View view = new View();
        view.setParameters(viewParams);
        view.setJsp(findJspByResult(result, actionElement));

    	return view;
    }

}

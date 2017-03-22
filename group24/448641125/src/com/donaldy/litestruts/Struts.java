package com.donaldy.litestruts;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class Struts {


    private final static Configuration cfg = new Configuration("struts.xml");

    public static View runAction(String actionName, Map<String,String> parameters) {

        String clzName = cfg.getClassName(actionName);

        if (clzName == null) {
            return null;
        }

        try {
            Class<?> clz = Class.forName(clzName);
            Object action = clz.newInstance();

            ReflectionUtil.setParameters(action, parameters);

            Method m = clz.getDeclaredMethod("execute");

            String resultName = (String) m.invoke(action);

            String jsp = cfg.getResultView(actionName, resultName);

            Map<String, Object> params = ReflectionUtil.getParamterMap(action);

            View view = new View();
            view.setJsp(jsp);
            view.setParameters(params);

            return view;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return null;
    }

    /*public static View runAction(String actionName, Map<String,String> parameters) {
        Element  rootElement = null;
        try {
            *//**
             * 0.读取配置文件
             *//*
            rootElement = readStrutsXml().getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        *//**
         * 1.根据actionName找到class
         * 并设置
         *//*
        String classPath = findClass(actionName, rootElement);

        return handle(classPath, parameters, rootElement);
    }

    private static Document readStrutsXml() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(
            new File("D:\\tools\\Code\\Y_Repository\\coding2017\\group24\\448641125\\src\\com\\donaldy" +
                "\\litestruts\\struts.xml"));

        return document;
    }

    private static String findClass(String actionName, Element root) {
        String classPath = null;
        for (Iterator i = root.elementIterator(); i.hasNext(); ) {
            Element action = (Element) i.next();
            if (actionName.equals(action.attribute("name").getText())) {
                classPath = action.attribute("class").getText();
                break;
            }
        }
        return classPath;
    }

    private static View handle(String classPath, Map<String, String> parameters
                , Element rootElement) {
        View view = new View();

        Class newClass = getClass(classPath);
        Object action = getObject(newClass);
        Element element = rootElement.element("action");


        if (action instanceof LoginAction) {
            LoginAction loginAction = (LoginAction) getAction(action, parameters);
            String answer = loginAction.execute();
            String page = getPage(element, answer);

            view.setJsp(page);
            view.setParameters(getMap(newClass, action));
        }

        return view;
    }

    private static Class getClass(String classPath) {
        try {
            return Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getObject(Class newClass) {
        try {
            return newClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object getAction(Object action, Map<String, String> parameters) {
        LoginAction loginAction = (LoginAction) action;
        loginAction.setName(parameters.get("name"));
        loginAction.setPassword(parameters.get("password"));
        return loginAction;
    }

    private static String  getPage(Element element, String answer) {
        for (Iterator i = element.elementIterator(); i.hasNext(); ) {
            Element result = (Element) i.next();
            if (answer.equals(result.attribute("name").getText())) {
                return result.getText();
            }
        }
        return "";
    }

    private static Map<String, String> getMap(Class newClass, Object action) {
        Map<String, String> map = new HashMap<>();
        Method[] methods = newClass.getDeclaredMethods();
        String getterMethod;
        for (Method method : methods) {
            getterMethod = method.getName();
            if (Pattern.matches("get(\\w+)", getterMethod)) {
                try {
                    map.put(getterMethod.substring(3).toLowerCase(),
                        method.invoke(action).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
        return map;
    }*/

}

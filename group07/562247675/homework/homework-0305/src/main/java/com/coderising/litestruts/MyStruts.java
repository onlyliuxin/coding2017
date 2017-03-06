package com.coderising.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MyStruts {

    public static View runAction(String actionName, Map<String, String> parameters) {
        MyStrutsActioNode actioNode = strutsActions.get(actionName);
        String actionString = actioNode.actionClass;
        Class actionClass = null;
        Object actionBean = null;
        try {
            actionClass = Class.forName(actionString);
            actionBean = actionClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (actionBean == null) return null; // 没有默认构造或没有找到字节码的情况
        Map<String, Method> setterMethods = getSetterMethods(actionClass);
        // #1.调用action中的set方法设置值
        if (parameters != null && !parameters.isEmpty()) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String fieldName = entry.getKey();
                String fieldValue = entry.getValue();
                Method setterMethod = setterMethods.get(fieldName);
                if (setterMethod != null) {
                    try {
                        setterMethod.invoke(actionBean, fieldValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // #2.调用action的execute方法
        Method method = getExecuteMethod(actionClass);
        if (method == null) return null; // 未声明execute方法
        String jspName = "";
        try {
            Object object = method.invoke(actionBean); // 获取execute方法返回值
            if (object instanceof String) {
                jspName = (String) object;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // #3.查找execute方法返回值对应的jsp路径
        View view = new View();
        String jspValue = actioNode.actionResults.get(jspName).value;
        view.setJsp(jspValue);

        // #4.获取action中的get方法返回值封装到view对象中
        Map<String, Method> getterMethods = getGetterMethods(actionClass);
        if (getterMethods != null && !getterMethods.isEmpty()) {
            HashMap<String, Object> resultMap = new HashMap<String, Object>(getterMethods.size());
            for (Map.Entry<String, Method> entry : getterMethods.entrySet()) {
                String fieldName = entry.getKey();
                Method getterMethod = entry.getValue();
                try {
                    Object object = getterMethod.invoke(actionBean);
                    resultMap.put(fieldName, object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            view.setParameters(resultMap);
        }
        return view;
    }

    private static Method getExecuteMethod(Class<?> beanClass) {
        Method method = null;
        try {
            method = beanClass.getMethod("execute");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    private static Map<String, Method> getGetterMethods(Class<?> beanClass) {
        Map<String, Method> resultMap = Collections.emptyMap();
        Map<String, PropertyDescriptor> descriptorMap = getPropertyDescriptors(beanClass);
        if (descriptorMap != null && !descriptorMap.isEmpty()) {
            resultMap = new HashMap<String, Method>(descriptorMap.size() / 2);
            for (PropertyDescriptor descriptor : descriptorMap.values()) {
                String fieldName = descriptor.getName();
                Method getterMethod = descriptor.getReadMethod();
                resultMap.put(fieldName, getterMethod);
            }
        }
        return resultMap;
    }

    private static Map<String, Method> getSetterMethods(Class<?> beanClass) {
        Map<String, Method> resultMap = Collections.emptyMap();
        Map<String, PropertyDescriptor> descriptorMap = getPropertyDescriptors(beanClass);
        if (descriptorMap != null && !descriptorMap.isEmpty()) {
            resultMap = new HashMap<String, Method>(descriptorMap.size() / 2);
            for (PropertyDescriptor descriptor : descriptorMap.values()) {
                String fieldName = descriptor.getName();
                Method setterMethod = descriptor.getWriteMethod();
                resultMap.put(fieldName, setterMethod);
            }
        }
        return resultMap;
    }

    private static Map<String, PropertyDescriptor> getPropertyDescriptors(Class<?> beanClass) {
        Map<String, PropertyDescriptor> resultMap = Collections.emptyMap();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            resultMap = new HashMap<String, PropertyDescriptor>(propertyDescriptors.length);
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String fieldName = propertyDescriptor.getName();
                resultMap.put(fieldName, propertyDescriptor);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    private static Map<String, MyStrutsActioNode> strutsActions;

    static {
        InputStream input = null;
        try {
            // dom4j解析xml文件封装到JavaBean
            input = MyStruts.class.getClassLoader().getResourceAsStream("struts.xml");
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(input);
            strutsActions = new HashMap<String, MyStrutsActioNode>();
            Element root = document.getRootElement();
            // <action name="*"></action> 标签
            for (Iterator i = root.elementIterator("action"); i.hasNext(); ) {
                Element actionXmlNode = (Element) i.next();
                MyStrutsActioNode actionNode = new MyStrutsActioNode();
                actionNode.actionName = actionXmlNode.attributeValue("name");
                actionNode.actionClass = actionXmlNode.attributeValue("class");
                strutsActions.put(actionNode.actionName, actionNode);
                actionNode.actionResults = new HashMap<String, MyActionResultNode>();
                // <result name="*"></result> 标签
                for (Iterator j = actionXmlNode.elementIterator("result"); j.hasNext(); ) {
                    Element resultXmlNode = (Element) j.next();
                    MyActionResultNode resultNode = new MyActionResultNode();
                    resultNode.name = resultXmlNode.attributeValue("name");
                    resultNode.value = resultXmlNode.getStringValue();
                    actionNode.actionResults.put(resultNode.name, resultNode);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    // close quietly..
                }
            }
        }
    }

    public static void main(String[] args) {

    }

    private static class MyStrutsActioNode {
        private String actionName;
        private String actionClass;
        private Map<String, MyActionResultNode> actionResults;

    }

    private static class MyActionResultNode {
        private String name;
        private String value;

    }

}

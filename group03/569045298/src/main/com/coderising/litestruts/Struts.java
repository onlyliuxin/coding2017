package com.coderising.litestruts;

import com.coderising.litestruts.StrutsBean.Action;
import com.coderising.litestruts.StrutsBean.Result;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 模拟struts执行
 * 读取类似struts.xml文件，根据xml的定义创建相关的Action类来执行
 */
public class Struts {

    private static final String FILE_PATH = "src/main/com/coderising/litestruts/struts.xml";

    public View runAction(String actionName, Map<String, String> parameters) {
        // 视图
        View view = new View();
        // 读取配置文件struts.xml
        Map<String, Action> actions = this.xmlToList();
        if (null == actions || actions.size() == 0) {
            return null;
        }
        try {
            // 根据actionName找到相对应的class
            Action action = actions.get(actionName);
            Class clazz = Class.forName(action.getClassName());
            // 通过反射实例化
            Object newInstance = clazz.newInstance();
            // 获得所有方法
            Map<String, Method> methodMap = new HashMap<>();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                methodMap.put(method.getName(), method);
            }
            // 根据parameters中的数据，调用对象的setter方法
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Method setterMethod = methodMap.get(settterMethodName(key));
                setterMethod.invoke(newInstance, value);
            }
            // 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
            Method executeMethod = clazz.getMethod("execute");
            Object object = executeMethod.invoke(newInstance);
            // 通过反射找到对象的所有getter方法
            Map<Object, Object> map = new HashMap<>();
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                // 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    String fieldName = field.getName();
                    Method getterMethod = methodMap.get(gettterMethodName(fieldName));
                    Object value = getterMethod.invoke(newInstance);
                    map.put(fieldName, value);
                }
            }
            // 放到View对象的parameters中
            view.setParameters(map);
            // 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，放到View对象的jsp字段中
            List<Result> resultList = action.getResult();
            for (Result result : resultList) {
                if (result.getName().equals(object)) {
                    view.setJsp(result.getValue());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return view;
    }

    private Map<String, Action> xmlToList() {
        Map<String, Action> map = new HashMap<>();
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(new File(FILE_PATH));
            Element rootElement = document.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator("action");
            while (iterator.hasNext()) {
                Action action = new Action();
                List<Result> results = new ArrayList<>();
                action.setResult(results);
                Element element = iterator.next();
                List<Attribute> attributes = element.attributes();
                for (Attribute attribute : attributes) {
                    String attributeName = attribute.getName();
                    if (attributeName.equals("name")) {
                        action.setName(attribute.getStringValue());
                    } else if (attributeName.equals("class")) {
                        action.setClassName(attribute.getStringValue());
                    }
                }
                Iterator<Element> iterator1 = element.elementIterator();
                while (iterator1.hasNext()) {
                    Result result = new Result();
                    Element element1 = iterator1.next();
                    List<Attribute> attributes1 = element1.attributes();
                    for (Attribute attribute : attributes1) {
                        String attributeName = attribute.getName();
                        if (attributeName.equals("name")) {
                            result.setName(attribute.getStringValue());
                        }
                    }
                    result.setValue(element1.getStringValue());
                    results.add(result);
                }
                map.put(action.getName(), action);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    private String gettterMethodName(String fieldName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("get");
        stringBuilder.append(fieldName.substring(0, 1).toUpperCase());
        stringBuilder.append(fieldName.substring(1));
        return stringBuilder.toString();
    }

    private String settterMethodName(String fieldName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("set");
        stringBuilder.append(fieldName.substring(0, 1).toUpperCase());
        stringBuilder.append(fieldName.substring(1));
        return stringBuilder.toString();
    }

}

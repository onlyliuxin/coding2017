package com.coderising.litestruts;

import com.coderising.litestruts.StrutsBean.Action;
import com.coderising.litestruts.StrutsBean.Result;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
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
        // 读取配置文件struts.xml
        Map<String, Action> configuration = this.parseXML();
        if (null == configuration || configuration.size() == 0) {
            return null;
        }
        try {
            Action actions = configuration.get(actionName);
            Class<?> clazz = Class.forName(actions.getClassName());
            Object action = clazz.newInstance();
            ReflectionUtil.setParameters(parameters, action);
            Method executeMethod = clazz.getMethod("execute");
            Object object = executeMethod.invoke(action);
            Map<Object, Object> map = ReflectionUtil.getParametersMap(clazz, action);
            View view = new View();
            view.setParameters(map);
            // 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，放到View对象的jsp字段中
            List<Result> resultList = actions.getResult();
            for (Result result : resultList) {
                if (result.getName().equals(object)) {
                    view.setJsp(result.getValue());
                }
            }
            return view;
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
        return null;
    }

    private Map<String, Action> parseXML() {
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

}

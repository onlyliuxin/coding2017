package com.coderising.litestruts;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.collections4.CollectionUtils;

import com.coderising.litestruts.api.StructAction;
import com.coderising.litestruts.api.View;
import com.coderising.litestruts.parser.StructXmlParser;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Struts {

    private static Map<String, StructAction> actionMapping = Maps.newConcurrentMap();

    private static final String DEFAULT_CONFIG = "struts.xml";

    private static List<String> configFiles;

    static {
        // 读取配置文件struts.xml
        loadStrutsConfig(configFiles);
    }

    public static View runAction(String actionName, Map<String, String> parameters) {
        View view = new View();
        try {
            StructAction action = actionMapping.get(actionName);
            Class clazz = Class.forName(action.getClazzName());
            Object instance = clazz.newInstance();
            String result = (String) process(clazz, instance, parameters, action);
            buildView(view, action, instance, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    private static void buildView(View view, StructAction action, Object instance, String result)
            throws IntrospectionException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        // 读取action属性
        BeanInfo beanInfo = Introspector.getBeanInfo(Class.forName(action.getClazzName()));
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Map<Object, Object> prop = Maps.newHashMap();
        for (PropertyDescriptor PropertyDescriptor : propertyDescriptors) {
            Method readMethod = PropertyDescriptor.getReadMethod();
            // 内省存在一个问题就是说属性的get方法本身不一定存在
            if (readMethod != null) {
                Object invoke = readMethod.invoke(instance);
                prop.put(PropertyDescriptor.getName(), invoke);
            }
        }
        // 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中。
        Map<String, String> actions = action.getActions();
        view.setParameters(prop);
        view.setJsp(actions.get(result));
    }

    @SuppressWarnings("unchecked")
    private static Object process(Class clazz, Object instance, Map<String, String> parameters, StructAction action)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        // 根据actionName找到相对应的class ，属性填充我认为暴力反射会更好一点
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            try {
                Field field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(instance, entry.getValue());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        // 通过反射调用对象的execute方法
        Method execute = clazz.getDeclaredMethod("execute");
        execute.setAccessible(true);
        return execute.invoke(instance);
    }

    private static void loadStrutsConfig(List<String> configFiles) {
        if (CollectionUtils.isEmpty(configFiles)) {
            configFiles = Lists.newArrayList(DEFAULT_CONFIG);
        }
        for (String configFile : configFiles) {
            InputStream inputStream = Struts.class.getResourceAsStream(configFile);
            SAXParser parser = null;
            try {
                parser = SAXParserFactory.newInstance().newSAXParser();
                StructXmlParser structXmlParser = new StructXmlParser();
                parser.parse(inputStream, structXmlParser);
                List<StructAction> actions = structXmlParser.getData();
                for (StructAction action : actions) {
                    actionMapping.put(action.getName(), action);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setConfigFiles(List<String> configFiles) {
        configFiles = configFiles;
    }
}

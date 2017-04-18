package dataStruct.com.coderising.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
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

//        0. 读取配置文件struts.xml
        Action action = matchAction(parseXml("/struts.xml"), actionName);
        try {

//            1. 根据actionName找到相对应的class, 通过反射实例化（创建对象）,
//               根据parameters中的数据，调用对象的setter方法
            Class<?> clazz = Class.forName(action.getClassName());
            Object instance = clazz.newInstance();
            for (String key : parameters.keySet()){
                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(key, clazz);
                    Method setMethod = propertyDescriptor.getWriteMethod();
                    setMethod.invoke(instance, parameters.get(key));
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
            }

//            2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
            Method exectueMethod = null;
            String result = null;
            try {
                exectueMethod = clazz.getMethod("execute");
                result = (String)exectueMethod.invoke(instance);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

//            3. 通过反射找到对象的所有getter方法（例如 getMessage）,
//            通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
//            放到View对象的parameters
            Map<String, Object> hashMap = new HashMap<String, Object>();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields){
                String name = field.getName();
                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(name, clazz);
                    Method getMethod = propertyDescriptor.getReadMethod();
                    Object res = getMethod.invoke(instance);
                    hashMap.put(name, res);
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
            }

//            4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
//            放到View对象的jsp字段中。
            View view = new View();
            view.setJsp((String)action.getResults().get(result));
            view.setParameters(hashMap);
            return view;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
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

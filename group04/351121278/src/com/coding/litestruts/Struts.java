package com.coding.litestruts;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

        /*
		0. 读取配置文件struts.xml
		dom4j 来读取
		*/

        List<Element> elements = null;
        try {
            elements = openStrutsXML("struts.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        for (Element element: elements) {
            String name = element.attribute("name").getValue();
            String aClass = element.attribute("class").getValue();
            /*
            1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
            据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
             ("name"="test" ,  "password"="1234") ,那就应该调用 setName和setPassword方法
             */

            //此时的name等于传入的actionName
            Class<?> clazz = null;
            try {
                clazz = Class.forName(aClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Object obj = null;
            try {
                obj = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            for (Map.Entry entry: parameters.entrySet()) {
                String key = entry.getKey().toString();
                String parSetName = parSetName(key);
                Method method = null;
                try {
                    method = clazz.getMethod(parSetName, String.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    method.invoke(obj, entry.getValue());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            //2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
            Method execute = null;
            try {
                execute = clazz.getMethod("execute", null);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Object result = null;
            try {
                result = execute.invoke(obj, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            Map resultParameters = new HashMap();

            // 3. 通过反射找到对象的所有getter方法（例如 getMessage）,
            // 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
            // 放到View对象的parameters
            Field[] fields = clazz.getDeclaredFields();
            //此处取到的方法是所有的public修饰的方法
            Method[] methods = clazz.getMethods();
            for (Field field: fields) {
                String fieldName = field.getName();
                String parGetName = parGetName(fieldName);
                boolean isGetMethod = checkGetMethod(methods, parGetName);
                if (!isGetMethod) {
                    continue;
                }
                Method method = null;
                try {
                    method = clazz.getMethod(parGetName, null);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    Object getResult = method.invoke(obj, null);
                    if (getResult != null) {
                        resultParameters.put(fieldName, getResult.toString());
                    }
                    //如果execute方法返回fail，添加错误信息到map中
                    if ("fail".equals(result)) {
                        resultParameters.put("message", "login failed,please check your user/pwd");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            //4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
            //放到View对象的jsp字段中。
            List<Element> resultElements = element.elements("result");
            for (Element resultElemnt: resultElements) {
                Attribute resultNode = resultElemnt.attribute("name");
                String resultValue = resultNode.getValue();
                String resultJsp = resultElemnt.getTextTrim();
                if (result.equals(resultValue)) {
                    View view = new View();
                    view.setJsp(resultJsp);
                    view.setParameters(resultParameters);
                    return view;
                }
            }

        }

        return null;
    }

    /**
     * 打开struts.xml文件，
     * @param strutsXMLPath struts.xml文件的位置
     * @return List<Element>节点对象
     * @throws DocumentException
     */
    public static List<Element> openStrutsXML(String strutsXMLPath) throws DocumentException {
        InputStream in = View.class.getResourceAsStream(strutsXMLPath);
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        Element rootElement = document.getRootElement();
        return (List<Element>) rootElement.elements("action");
    }

    /**
     * 拼接属性的get方法
     * @param fieldName
     * @return
     */
    public static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 拼接在某属性的 set方法
     *
     * @param fieldName
     * @return String
     */
    public static String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "set"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 判断是否存在某属性的 get方法
     *
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    public static boolean checkGetMethod(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }


}
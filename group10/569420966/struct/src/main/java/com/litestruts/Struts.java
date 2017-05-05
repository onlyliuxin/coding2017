package com.litestruts;

import com.myutil.JavaBeanUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Struts {

    private static Map<String, Map<String, String>> xmlInfo = new HashMap<>();

    public static View runAction(String actionName, Map<String, String> parameters) {


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

        // 【0】
        getInfo();


        Map<String, String> action = xmlInfo.get(actionName);
        Class<?> classForName;
        View view = new View();
        try {
            // 【1】
            Object aObj = Class.forName(action.get("class")).newInstance();
            classForName = aObj.getClass();
            Method[] methods = classForName.getMethods();
            for (Method method : methods) {
                for (String key : parameters.keySet()) {
                    if (JavaBeanUtil.getSetMethodName(key).equals(method.getName())) {
                        Method declaredMethod = classForName.getDeclaredMethod(method.getName(), method.getParameterTypes());
                        declaredMethod.invoke(aObj, parameters.get(key));
                    }
                }
            }
            // 【2】
            Map<String, String> resultMap = new HashMap<>();
            Method execute = classForName.getDeclaredMethod("execute");
            String invoke = (String) execute.invoke(aObj);
            // 【3】
            Field[] fields = classForName.getDeclaredFields();
            for (Field field : fields) {
                for (Method method : methods) {
                    if (JavaBeanUtil.getGetMethodName(field.getName()).equals(method.getName())) {
                        Method declaredMethod = classForName.getDeclaredMethod(method.getName());
                        String invoke1 = (String)declaredMethod.invoke(aObj);
                        resultMap.put(field.getName(), invoke1);
                    }
                }
            }
            view.setParameters(resultMap);
            // 【4】
            view.setJsp(action.get(invoke));
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

        return view;
    }

    private static void getInfo() {
        try {
            File file = new File("D:\\Own\\Code\\Java\\coding2017\\group10\\569420966\\struct\\src\\main\\resources\\struts.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList nl = doc.getElementsByTagName("struts");

            if (nl.getLength() > 0) {
                NodeList actions = doc.getElementsByTagName("action");
                if (actions.getLength() > 0) {
                    Map<String, String> actionInfo;
                    for (int i = 0; i < actions.getLength(); i++) {
                        actionInfo = new HashMap<>();
                        NamedNodeMap attributes = actions.item(i).getAttributes();
                        for (int j = 0; j < attributes.getLength(); j++) {
                            attributes.item(j).getNodeName();
                            actionInfo.put(attributes.item(j).getNodeName(), attributes.item(j).getNodeValue());
                        }
                        NodeList childNodes = actions.item(i).getChildNodes();
                        for (int j = 0; j < childNodes.getLength(); j++) {
                            if (childNodes.item(j).getNodeType() == 1) { // 元素节点
                                NodeList childNodes1 = childNodes.item(j).getChildNodes();
                                NamedNodeMap attributes1 = childNodes.item(j).getAttributes();
                                NodeList childNodes2 = childNodes.item(j).getChildNodes();
                                actionInfo.put(attributes1.item(0).getNodeValue(), childNodes2.item(0).getNodeValue());
                            }
                        }
                        if (actionInfo.get("name") != null) {
                            xmlInfo.put(actionInfo.get("name"), actionInfo);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getInfo();
    }

}
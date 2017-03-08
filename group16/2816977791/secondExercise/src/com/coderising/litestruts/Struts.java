package com.coderising.litestruts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class Struts {

    private static Map<String, ActionXml> actionXmlMap;

    static {
        //0. 读取配置文件struts.xml
        actionXmlMap = parserXml("src/com/coderising/litestruts/struts.xml");
        System.out.println("parse end");
    }

    public static View runAction(String actionName, Map<String, String> parameters) {

        try {
            ActionXml actionXml = actionXmlMap.get(actionName);
            if (actionName == null) {
                return null;
            } else {
                String className = actionXml.getClassName();
                Class cls = Class.forName(className);
                Object obj = cls.newInstance();
                /**
                 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
                 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
                 ("name"="test" ,  "password"="1234") ,
                 那就应该调用 setName和setPassword方法
                 */
                Method[] methods = cls.getMethods();
                for (String field : parameters.keySet()) {
                    for (Method method : methods) {
                        if (method.getName().startsWith("set") && method.getName().toLowerCase().endsWith(field.toLowerCase())) {
                            method.invoke(obj, parameters.get(field));
                            break;
                        }
                    }
                }
                Method action = cls.getMethod("execute");
                //通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
                String result = (String) action.invoke(obj);
                /**
                 通过反射找到对象的所有getter方法（例如 getMessage）,
                 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
                 放到View对象的parameters
                 */
                Field[] fields = cls.getDeclaredFields();
                Map<String, Object> params = new HashMap<>();
                for (Field field : fields) {
                    for (Method method : methods) {
                        if (method.getName().startsWith("get") && method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                            String viewResult = (String) method.invoke(obj);
                            params.put(field.getName(), viewResult);
                            break;
                        }
                    }
                }
                /**
                 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
                 放到View对象的jsp字段中。
                 */
                View view = new View();
                view.setParameters(params);
                view.setJsp(actionXmlMap.get(actionName).getMap().get(result));
                return view;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    //解析xml文件
    private static Map<String, ActionXml> parserXml(String fileName) {
        try {
            Map<String, ActionXml> map = new HashMap<>();
            //create documentBuilder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //create document
            Document document = db.parse(fileName);
            //extract root element
            Element root = document.getDocumentElement();
            System.out.println("Root element :" + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("action");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node nNode = nodeList.item(temp);
                System.out.println("\nCurrent Element :"
                    + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    ActionXml actionXml = new ActionXml();
                    Element eElement = (Element) nNode;
                    System.out.println("action name : "
                        + eElement.getAttribute("name"));
                    System.out.println("class name : "
                        + eElement.getAttribute("class"));
                    actionXml.setName(eElement.getAttribute("name"));
                    actionXml.setClassName(eElement.getAttribute("class"));
                    NodeList result = eElement.getElementsByTagName("result");
                    Map<String, String> results = new HashMap<>();
                    for (int i = 0; i < result.getLength(); i++) {
                        Node resultNode = result.item(i);
                        if (resultNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element resultElement = (Element) resultNode;
                            System.out.println("result name:" + resultElement.getAttribute("name"));
                            System.out.println("result context:" + resultElement.getTextContent());
                            results.put(resultElement.getAttribute("name"), resultElement.getTextContent());
                        }
                        actionXml.setMap(results);
                    }
                    map.put(actionXml.getName(), actionXml);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        parserXml("src/com/coderising/litestruts/struts.xml");
    }
}

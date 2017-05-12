package litestruts;

import datastructure.basic.ArrayList;
import litestruts.exception.XmlElementNotFoundException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

public class Struts {

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
    public static View runAction(String actionName, Map<String,String> parameters) {
        //读取xml
        Document document;
        try {
            document = loadXml();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            return null;
        }

        //获取所有action
        Element struts = document.getDocumentElement();
        NodeList actionList = struts.getElementsByTagName("action");

        //根据actionName找到相应action，创建View
        try {
            Element action = getAction(actionList, actionName);
            return createView(action, parameters);
        } catch (InvocationTargetException | XmlElementNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Element getAction(NodeList actionList, String name) throws XmlElementNotFoundException {
        //根据actionName找到相应action
        for (int i = 0; i < actionList.getLength(); ++i) {
            Element action = (Element) actionList.item(i);
            if (action.getAttribute("name").equals(name)) {
                return action;
            }
        }
        throw new XmlElementNotFoundException(elementNotFoundMessage("action", name));
    }

    private static View createView(Element action, Map<String, String> parameters)
            throws InvocationTargetException {

        String className = action.getAttribute("class");
        Class clazz;
        Object object;
        String executeResult;
        try {
            clazz = Class.forName(className);
            object = clazz.newInstance();
            setParameters(object, parameters);
            executeResult = execute(object);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }

        NodeList resultList = action.getElementsByTagName("result");
        String jsp;
        Map<String, String> parameterMap;
        try {
            jsp = getJsp(resultList, executeResult);
            parameterMap = createParameterMap(clazz, object);
        } catch (XmlElementNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        View view = new View();
        view.setJsp(jsp);
        view.setParameters(parameterMap);
        return view;
    }

    private static Map<String, String> createParameterMap(Class clazz, Object object)
            throws InvocationTargetException, IllegalAccessException {
        //获取所有getter
        Method[] getters = getAllGetters(clazz);
        //创建parameter Map
        Map<String, String> parameterMap = new Hashtable<>();
        for (Method getter : getters) {
            parameterMap.put(getAttributeName(getter.getName()), (String) getter.invoke(object));
        }
        return parameterMap;
    }

    private static String getJsp(NodeList resultList, String executeResult) throws XmlElementNotFoundException {
        return getResult(resultList, executeResult).getTextContent();
    }

    private static Element getResult(NodeList resultList, String name) throws XmlElementNotFoundException {
        for (int j = 0; j < resultList.getLength(); ++j) {
            Element result = (Element) resultList.item(j);
            if (result.getAttribute("name").equals(name)) {
                return result;
            }
        }
        throw new XmlElementNotFoundException(elementNotFoundMessage("result", name));
    }

    private static String elementNotFoundMessage(String elementName, String nameAttribute) {
        return "\"" + elementName + "\" XML element not found: " + "attribute \"name\": " + nameAttribute;
    }

    private static Method[] getAllGetters(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        ArrayList list = new ArrayList();
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                list.add(method);
            }
        }

        Method[] getters = new Method[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            getters[i] = (Method) list.get(i);
        }
        return getters;
    }

    private static Document loadXml() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;
        builder = factory.newDocumentBuilder();
        document = builder.parse(new File("src/litestruts/struts.xml"));
        return document;
    }

    private static String execute(Object object)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method execute = object.getClass().getDeclaredMethod("execute");
        return (String) execute.invoke(object);
    }

    private static void setParameters(Object object, Map<String, String> parameters)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            Method setter = object.getClass().getDeclaredMethod(getSetterName(entry.getKey()), String.class);
            if (setter != null) {
                setter.invoke(object, entry.getValue());
            }
        }
    }

    private static String getSetterName(String attributeName) {
        return "set" + Character.toUpperCase(attributeName.charAt(0)) + attributeName.substring(1);
    }

    private static String getAttributeName(String getterOrSetterName) {
        String sub = getterOrSetterName.substring(3);
        return Character.toLowerCase(sub.charAt(0)) + sub.substring(1);
    }
}

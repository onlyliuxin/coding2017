package com.dudy.learn01.litestruts;

import com.dudy.learn01.utils.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {


         
		//0. 读取配置文件struts.xml
        Map<String, ActionPojoParseXML> parseXml = null;
        try {
           parseXml = parseXml();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

// 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
//		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
//		("name"="test" ,  "password"="1234") ,
//		那就应该调用 setName和setPassword方法
        ActionPojoParseXML actionPojoParseXML = parseXml.get(actionName);

        Object result = null;

        Map viewParameters = new HashMap();

        try {
            Class actionClass = Class.forName(actionPojoParseXML.getClassname());
            Object base = actionClass.newInstance();


            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                // 这里 只能传递object吧
                //actionClass.gett
                Method method = actionClass.getDeclaredMethod(methodNameconversion(entry.getKey()), String.class);
                method.setAccessible(true);
                method.invoke(base,entry.getValue());
            }


//		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"


            Method method = actionClass.getDeclaredMethod("execute");
            method.setAccessible(true);
            result = method.invoke(base);
//		3. 通过反射找到对象的所有getter方法（例如 getMessage）,
//		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
//		放到View对象的parameters

            Method[] methods = actionClass.getDeclaredMethods();

            for(int i = 0; i< methods.length ; i++){
                methods[i].setAccessible(true);
                String methodName = methods[i].getName();
                if (methodName.startsWith("get")){
                    Object value = methods[i].invoke(base);
                    viewParameters.put( StringUtils.lowerFirstLetter(methodName.substring(3)),
                            value);
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

//		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
//		放到View对象的jsp字段中。

        View view = new View();
        view.setJsp(actionPojoParseXML.getChildElement().get(result));

        view.setParameters(viewParameters);
        System.out.println(view);

        return view;
    }

    /**
     *
     * @param key
     * @return
     */
    private static String methodNameconversion(String key) {
        return  "set" + StringUtils.upperFirstLetter(key);
    }

    /**
     * 解析xml 获取解析对象
     * @return
     * @throws DocumentException
     */
    private static Map<String,ActionPojoParseXML> parseXml() throws DocumentException {
        SAXReader reader = new SAXReader();
        File file = new File("/Users/dudy/coding2017-1/group04/1796244932/learn01/src/main/resource/struts.xml");
        Document document = reader.read(file);
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();

        Map<String,ActionPojoParseXML> result = new HashMap<>();


        for (Element child : childElements) {

            ActionPojoParseXML parseXML = new ActionPojoParseXML(
                    child.attributeValue("name"),
                    child.attributeValue("class"));

            //未知子元素名情况下
            List<Element> elementList = child.elements();
            for (Element ele : elementList) {
                parseXML.getChildElement().put(ele.attributeValue("name"),ele.getText());
            }
            result.put(parseXML.getName(),parseXML);
        }
//        System.out.println(result);
        return  result;
    }

    public static void main(String[] args) throws DocumentException {
        parseXml();
    }

}

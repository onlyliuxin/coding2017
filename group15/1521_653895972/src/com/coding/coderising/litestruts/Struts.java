package com.coding.coderising.litestruts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class Struts {


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

        //解析xml
        Map xmlInfo = parsersXml(actionName);
        //获取类名
        String allName = (String) xmlInfo.get("className");
        try {
            //加载类
            Class cls = Class.forName(allName);
            //实例化
            Object object = cls.newInstance();
            for (String key : parameters.keySet()) {
                //拼接set方法名
                String setName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                Method setMethod = cls.getDeclaredMethod(setName, String.class);
                //放射执行set方法
                setMethod.invoke(object, parameters.get(key));
            }
            //执行execute方法
            Method exectue = cls.getDeclaredMethod("execute", null);
            String reslut = (String) exectue.invoke(object, null);
            //执行getMessage方法
            Method getMessage = cls.getDeclaredMethod("getMessage", null);
            String message = (String) getMessage.invoke(object, null);
            //获取xml配置想返回结果
            String jsp = (String) xmlInfo.get(reslut);
            //组装view
            Map map = new HashMap();
            map.put("message", message);
            View view = new View();
            view.setJsp(jsp);
            view.setParameters(map);
            return view;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Map parsersXml(String actionName) {
        File file = new File(Struts.class.getResource("").getPath() + "/struts.xml");
        //1.获取DOM解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        HashMap<String, String> map = new HashMap<>();
        try {
            //2.获取解析器
            DocumentBuilder builder = factory.newDocumentBuilder();
            //3.加载xml文档
            Document document = builder.parse(file);
            //4.获取指定action的action集合
            NodeList actionlist = document.getElementsByTagName("action");
            //5.如果actionName重复 只去第一个
            for (int j = 0; j < actionlist.getLength(); j++) {
                Element actionElement = (Element) actionlist.item(j);
                if (actionElement.getAttribute("name") != null && actionElement.getAttribute("name").equalsIgnoreCase(actionName)) {
                    //6.获取 类全限定名
                    String className = actionElement.getAttribute("class");
                    map.put("className", className);
                    //7.获取 action子节点
                    NodeList childList = actionElement.getChildNodes();
                    int lenght = childList.getLength();
                    for (int i = 0; i < lenght; i++) {
                        Node node = childList.item(i);
                        //判断为element节点 排除空格 换行
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element child = (Element) node;
                            switch (child.getAttribute("name")) {
                                case "success":
                                    map.put("success", child.getTextContent());
                                    break;
                                case "fail":
                                    map.put("fail", child.getTextContent());
                                    break;
                                case "error":
                                    map.put("error", child.getTextContent());
                                    break;
                            }
                        }
                    }
                    break;
                }

            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}

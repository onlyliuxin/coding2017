package net.coding.coderising.litestruts;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

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

        Map<String, String> name2ClassMap = new HashMap<String, String>();
        Map<String, String> result2JSPMap = new HashMap<String, String>();


        try {
            File xmlFile = new File("group16/542087872/src/net/coding/coderising/litestruts/struts.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList actionList = doc.getElementsByTagName("action");
            for (int i = 0; i < actionList.getLength(); i++) {
                Node node = actionList.item(i);
                System.out.println(node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element)node;
                    String acName = element.getAttribute("name");
                    String acClass = element.getAttribute("class");
                    name2ClassMap.put(acName, acClass);

                    NodeList resultList = element.getElementsByTagName("result");
                    for (int j = 0; j < resultList.getLength(); j++) {
                        Element resultElemet = (Element)(resultList.item(j));
                        String acResultName = resultElemet.getAttribute("name");
                        String acResultJSP = resultElemet.getTextContent();

                        result2JSPMap.put(acName + "_" + acResultName, acResultJSP);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("parse XML ERROR!");
        }

        String classStr = name2ClassMap.get(actionName);
        if (classStr == null) {
            throw new RuntimeException("ACTION FOUND ERROR!");
        }

        try {
            Class actionClass = Class.forName(classStr);
            Object actionObj = actionClass.newInstance();
            for (String key : parameters.keySet()) {
                String value = parameters.get(key);
                Method theMethod = actionClass.getMethod("set" + key.substring(0, 1).toUpperCase() + key.substring(1), String.class);
                theMethod.invoke(actionObj, value);
            }

            // execut
            Method exeMethod = actionClass.getMethod("execute");
            String result = (String)exeMethod.invoke(actionObj);

            // find JSP
            String JSPPath = result2JSPMap.get(actionName + "_" + result);
            View view = new View();
            view.setJsp(JSPPath);

            // generate map
            Map map = new HashMap();
            for(Method method: actionClass.getMethods()) {
                if (method.getName().startsWith("get")) {
                    Object key = method.getName().substring(3).toLowerCase();
                    Object value = method.invoke(actionObj);
                    map.put(key, value);
                }
            }
            view.setParameters(map);

            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        runAction(null, null);
    }

}

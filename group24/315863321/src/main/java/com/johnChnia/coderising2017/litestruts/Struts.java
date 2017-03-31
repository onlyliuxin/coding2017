package com.johnChnia.coderising2017.litestruts;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters) throws Exception {

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

        SAXReader reader = new SAXReader();
        Document document = null;
        document = reader.read(new File("target/classes/struts.xml"));
        Element node = document.getRootElement();
        List<Element> elements = node.elements("action");

        Class action = null;
        Object actionInstance = null;
        Method[] methods = null;
        View view = null;
        for (Element element :
                elements) {
            if (element.attribute("name").getValue().equals(actionName)) {
                action = Class.forName(element.attribute("class").getValue());
                Constructor constructor = action.getConstructor(null);
                actionInstance = constructor.newInstance(null);


                methods = action.getDeclaredMethods();
                Iterator mapIterator = parameters.entrySet().iterator();
                while (mapIterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) mapIterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    for (Method method :
                            methods) {
                        if (method.getName().equalsIgnoreCase("set" + key)) {
                            method.invoke(actionInstance, value);
                            break;
                        }
                    }
                }


                Method method = action.getMethod("execute", null);
                String resultName = (String) method.invoke(actionInstance, null);


                Map<String, String> map = new HashMap<>();
                Field[] fields = action.getDeclaredFields();
                for (Field field :
                        fields) {
                    String fieldName = field.getName();
                    String methodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    map.put(fieldName,
                            (String) action.getMethod(methodName, null)
                                    .invoke(actionInstance, null));
                }

                String jsp = null;
                Iterator<Element> xmlIterator = element.elementIterator();
                while (xmlIterator.hasNext()) {
                    Element e = xmlIterator.next();
                    if (e.attribute("name").getValue().equals(resultName)) {
                        jsp = e.getTextTrim();
                    }
                }
                view = new View();
                view.setParameters(map);
                view.setJsp(jsp);
            }
        }


        return view;
    }


}

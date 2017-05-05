package com.coderising.litestruts;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
        View view = new View();
        SAXReader reader = new SAXReader();
        String classValue = "";


        try {
            Document document= reader.read(new File("src/com/coderising/litestruts/struts.xml"));
            //获取根节点
            Element rootElement = document.getRootElement();
            //根节点属性
            List<Element> elements = rootElement.elements();
            Element actionElement = null;
            for (Element element : elements) {
                Attribute actionNameAttr = element.attribute("name");
                if (actionNameAttr.getValue().equals(actionName)) {
                    Attribute classAttr = element.attribute("class");
                    classValue = classAttr.getValue();
                    actionElement = element;
                }
            }



            Class newClass = Class.forName(classValue);
            Object function = newClass.newInstance();
            Field[] fields = newClass.getDeclaredFields();
            String jsp = "";

            Set<Map.Entry<String, String>> entrySet = parameters.entrySet();
            for (Map.Entry entry:entrySet) {
                String name = (String) entry.getKey();
                String password = (String) entry.getValue();

                Method setName = newClass.getDeclaredMethod("setName", String.class);
                setName.invoke(function, name);

                Method setPassword = newClass.getDeclaredMethod("setPassword", String.class);
                setPassword.invoke(function, password);

                Method executeMethod = newClass.getDeclaredMethod("execute");
                executeMethod.invoke(function);

                List<Element> resultElements = actionElement.elements();
                for(Element e:resultElements) {
                    Attribute resultNameAttr = e.attribute("name");
                    if ( resultNameAttr.getValue().equals(executeMethod.invoke(function))){
                        jsp = e.getText();
                        break;
                    }
                }
            }

            HashMap<String, Object> hashMap = new HashMap<>();
            for (Field field : fields) {
                Method getMessage = newClass.getDeclaredMethod("getMessage");
                Object message = getMessage.invoke(function);
                hashMap.put(field.getName(), message);
            }

            view.setParameters(hashMap);
            view.setJsp(jsp);


        } catch (DocumentException e) {
            e.printStackTrace();
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

}

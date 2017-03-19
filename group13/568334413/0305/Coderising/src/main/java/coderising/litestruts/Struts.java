package coderising.litestruts;

//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;

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

        SAXReader saxReader = new SAXReader();
        Document document = null;
        View view = new View();

        try {
            document = saxReader.read(new File(Struts.class.getResource("/struts.xml").getPath()));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Element root = document.getRootElement();
        List<Element> childList = root.elements();
        String classValue = null;
        String nameValue = null;
        Element resultElement = null;
        for (int i = 0; i < childList.size(); i++) {
            Element element = childList.get(i);
            List<Attribute> list = element.attributes();
            for (Attribute attribute : list) {
                if (attribute.getName().equals("name")) {
                    nameValue = attribute.getValue();
                } else {
                    classValue = attribute.getValue();
                }
            }
            if (nameValue != null && classValue != null) {
                resultElement = element;
                break;

            }
        }
        Class<?> class1 = null;

        try {

            class1 = Class.forName(classValue);
            Object loginAction = class1.newInstance();
            Field[] fields = class1.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                field.set(loginAction, parameters.get(field.getName()));
            }
            Method method = class1.getMethod("execute", null);
            String object = (String) method.invoke(loginAction, null);
            List<Element> name = resultElement.elements();
            String jsp = null;
            for (Element element : name) {
                Attribute attribute1 = element.attribute("name");
                if (attribute1.getValue().equals(object)) {
                    jsp = element.getText();
                    break;
                }
            }

            HashMap<String, Object> parameter = new HashMap<String, Object>();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                parameter.put(field.getName(), field.get(loginAction));
            }
            view.setJsp(jsp);
            view.setParameters(parameter);
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

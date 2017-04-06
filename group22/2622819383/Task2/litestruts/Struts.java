//本篇代码参考自学员2415980327


import java.io.InputStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {
    
    public static Element parseXml(String fileName) {
        InputStream input = Struts.class.getResourceAsStream(fileName);
        SAXReader reader = new SAXReader();
        Document document = null;
        
        try {
            document = reader.read(input);
            Element struts = document.getRootElement(); 
            return struts;           
        } catch (DocumentException e) {
            e.printStackTrace();
        } 
        return null; 
    }
    
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
        Element struts = parseXml("struts.xml");
        List<Element> actions = struts.elements();
        List<Element> resultRefs = new ArrayList<>();
        String actionClass = "";
        for (Element element : actions)
            if (actionName.equals(element.attributeValue("name"))) {
                actionClass = element.attributeValue("class");
                resultRefs = element.elements();
                break;
            }
        
        Set<String> attributes = parameters.keySet();
        Iterator<String> it = attributes.iterator();
        try {    
            Object action = Class.forName(actionClass).newInstance();
            while (it.hasNext()) {
                String attribute = it.next();
                Method method = action.getClass().getDeclaredMethod("set"  
                                + attribute.substring(0, 1).toUpperCase()
                                + attribute.substring(1), String.class);
                method.invoke(action, parameters.get(attribute));
            }
            
            Method execute = action.getClass().getDeclaredMethod("execute");
            String result = (String)execute.invoke(execute);
            
            Map<String, String> actionParam = new HashMap(); 
            Method[] methods = action.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    String methodName = method.getName();
                    String name = methodName.substring(3, 4).toUpperCase() + methodName.substring(4);
                    String value = (String)method.invoke(action);
                    actionParam.put(name, value);
                }
            }
        
        
            View view = new View();
            view.setParameters(actionParam);
            for (Element element : resultRefs) {
                if (result.equals(element.attributeValue("name"))) {
                    view.setJsp((String)element.getData());
                    break;
                }
            }
            return view;
            
        } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    public static void main(String[] args) {
        String actionName = "login";
    	Element struts = parseXml("struts.xml");
    	List<Element> actions = struts.elements();
    	for (Element element : actions) {
			if (actionName.equals(element.attributeValue("name"))) {
				System.out.println(element.attributeValue("class"));
				
				for(Element element1:(List<Element>)element.elements()){
					System.out.println(element1.getData());
				}
			}
		}
    }
}






















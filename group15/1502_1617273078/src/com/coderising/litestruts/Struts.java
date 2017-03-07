package com.coderising.litestruts;

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

    public static View runAction(String actionName, Map<String,String> parameters) throws DocumentException {

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
        HashMap<String, String> Parameters = new HashMap<>();
        View view = new View();
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("./src/com/coderising/litestruts/struts.xml"));
        Element root = document.getRootElement();
        Element thiselement = null;
        List<Element> list = root.elements();
        String classpath = null;
        for (Element element : list) {
            //System.out.println(element.attribute("name").getValue());
            if (element.attribute("name").getValue().equals(actionName)) {
                thiselement = element;
                classpath = element.attribute("class").getValue();
                break;
            }
        }
        Class ojbect;

        try {
            ojbect=Class.forName(classpath);
            Object acionclass = ojbect.newInstance();
            for (String string : parameters.keySet()) {
                Method method = ojbect.getMethod("set"+upperCase(string),String.class);
                method.invoke(acionclass,parameters.get(string));
            }
            Method execute = ojbect.getMethod("execute");
            String result= (String) execute.invoke(acionclass);
            //判断返回的jsp
            List<Element> elementresult = thiselement.elements();
            for (Element element:elementresult){
                if (element.attribute("name").getValue().equals(result)) {
                    view.setJsp(element.getStringValue());
                    break;
                }
            }
            // 获得getter方法,并设置Parameters
            Method[] methods = ojbect.getDeclaredMethods();

            for (Method method : methods) {
                if (method.getName().substring(0, 3).equals("get")) {
                    String attribute= (String) method.invoke(acionclass);
                    Parameters.put(method.getName().substring(3).toLowerCase(), attribute);
                }
            }
            view.setParameters(Parameters);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return view;
    }
    //首字母大写
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

}

package coderising.litestruts;

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
    public static void main(String[] args) {
        runAction("login", null);
    }

    public static View runAction(String actionName, Map<String, String> parameters) {
        String className = " ";
        View view = new View();
        Map<String, String> map = new HashMap<>();
        String result = " ";

        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File("src\\coderising\\litestruts\\struts.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        List<Element> eles = root.elements();
        for (Element e : eles) {
            if (e.attribute("name").getValue().equals(actionName)) {
                className = e.attribute("class").getValue();
            }
        }

        try {
            Class clz = Class.forName(className);
            Object obj = clz.newInstance();
            Method method = clz.getDeclaredMethod("setName",String.class);
            method.invoke(obj, parameters.get("name"));
            method = clz.getDeclaredMethod("setPassword",String.class);
            method.invoke(obj, parameters.get("password"));
            method = clz.getDeclaredMethod("execute");
            result = (String) method.invoke(obj);
            method = clz.getDeclaredMethod("getMessage");
            map.put("message", (String) method.invoke(obj));
            method = clz.getDeclaredMethod("getName");
            map.put("name", (String) method.invoke(obj));
            method = clz.getDeclaredMethod("getPassword");
            map.put("password", (String) method.invoke(obj));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        view.setParameters(map);
        for (Element e : eles) {
            if (e.attribute("name").getValue().equals(actionName)) {
                List<Element> ele_subs = e.elements();
                for (Element e_sub : ele_subs) {
                    if (e_sub.attribute("name").getValue().equals(result)) {
                        view.setJsp(e_sub.getTextTrim());
                    }
                }
            }
        }



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

        return view;
    }

}

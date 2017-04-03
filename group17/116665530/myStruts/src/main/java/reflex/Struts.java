package reflex;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

public class Struts {
    public static View runAction(String actionName,Map<String,String> parameters){
        //读取配置文件
        File myXML = new File("src/main/java/reflex/struts.xml");
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(myXML);
            Element root = doc.getRootElement();
            String className=findByActionName(root,actionName);
            java.lang.Object o = findByClassName(className,parameters);
            Class c = o.getClass();
            String result =(String) c.getMethod("exectue").invoke(o);
            String message = (String) c.getMethod("getMessage").invoke(o);
            Map<String,String> map =new HashMap<String, String>();
            map.put("message",message);
            View view = new View();
            view.setParameters(map);
            String jsp = findJspByResult(root,className,result);
            view.setJsp(jsp);
            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String findByActionName(Element node,String actionName) {
        List<Element> list = node.elements();
        for(Element element:list){
            if(element.attribute("name").getValue().equals(actionName)){
                return element.attribute("class").getValue();
            }
        }
        return null;
    }
    private static java.lang.Object findByClassName(String name, Map<String,String> parameter){
        try {
            Class c = Class.forName(name);
            Object o = c.newInstance();
            Set<String> keys = parameter.keySet();
            for (String key:keys){
                String methodName = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
                c.getMethod(methodName,String.class).invoke(o,parameter.get(key));
            }
            return o;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String findJspByResult(Element node,String calssName,String result){
        List<Element> list = node.elements();
        for(Element element:list){
            if(element.attribute("class").getValue().equals(calssName)){
                List<Element> list1 = element.elements();
                for(Element element1:list1){
                    if(element1.attribute("name").getValue().equals(result)){
                        return element1.getStringValue();
                    }
                }
            }
        }
        return null;
    }
}

package com.github.wdn.coding2017.coderising.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class Struts {
    public static void main(String[] args) {
        Struts s = new Struts();
        Map<String,Map<String,Object>> strutsMap = s.readStrutsXml();
        System.out.println(strutsMap);
    }
    public static View runAction(String actionName, Map<String,String> parameters) {
        Map<String,Map<String,Object>> strutsMap = readStrutsXml();
        View view = new View();
        if(actionName.contains(actionName)){
            Map<String,Object> actionMap = strutsMap.get(actionName);
            Map<String,String> resultMap = (Map<String, String>) actionMap.get("result");
            String actionClassName = actionMap.get("class").toString();
            System.out.println(actionClassName);
            try {
                Class c = Class.forName(actionClassName);
                // 创建实例
                Object instance = c.newInstance();
                // 根据parameters调用setter方法
                for(Map.Entry<String,String> entry:parameters.entrySet()){
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Method setter = c.getMethod("set"+key.substring(0, 1).toUpperCase() + key.substring(1),String.class);
                    if(setter!=null){
                       setter.invoke(instance, value);
                    }
                }
                Method executeMethod = c.getMethod("execute");
                Object result = executeMethod.invoke(instance,null);
                view.setJsp(resultMap.get(result));

                Map<String, String> paramters = new HashMap<String, String>();
                Method[] methods = c.getMethods();
                for (Method m:methods) {
                    String methodName = m.getName();
                    if(methodName.startsWith("get")){
                        String key = methodName.replace("get","").toLowerCase();
                        paramters.put(key,m.invoke(instance,null).toString());
                    }
                }
                view.setParameters(paramters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                throw new ClassNotFoundException();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    	return view;
    }    
    public static Map<String,Map<String,Object>> readStrutsXml(){
        SAXReader reader = new SAXReader();
        Map<String,Map<String,Object>> strutsMap = new HashMap<String, Map<String,Object>>();
        try {
            String path = System.getProperty("user.dir");
            Document document = reader.read(new File(path+"\\src\\main\\java\\com\\github\\wdn\\coding2017\\coderising\\litestruts\\struts.xml"));
            Element root = document.getRootElement();
            for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                Map<String,Object> actionMap = new HashMap<String, Object>();
                Element actionElement = (Element) i.next();
                if(actionElement.getName().equals("action")){
                    actionMap.put("name",actionElement.attributeValue("name"));
                    actionMap.put("class",actionElement.attributeValue("class"));
                }
                Map<String,String> resultMap = new HashMap<String, String>();
                for (Iterator j=actionElement.elementIterator();j.hasNext();){
                    Element resultElement = (Element)j.next();
                    resultMap.put(resultElement.attributeValue("name"), resultElement.getText());
                    actionMap.put("result", resultMap);
                }
                strutsMap.put(actionElement.attributeValue("name"),actionMap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return strutsMap;
    }
}

package com.coderising.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Struts {

    private static Map<String, Action> actionMap;

    static {
        actionMap = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(Struts.class.getClassLoader().getResourceAsStream("struts.xml"));
            //获取根节点元素对象
            Element root = document.getRootElement();
            //获取二级节点
            List<Element> list = root.elements();
            //遍历
            listNodes(list);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


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
        //根据actionName取出对应的配置信息
        Action action = actionMap.get(actionName);
        try {
            //找到对应的Class
            // 三种方法： 1.Class.forName('包名+类名') 2. 类名.Class  3. 对象名.getClass()
            Class cls = Class.forName(action.getClassName());
            //通过反射创建一个对象
            //如果是没有无参构造方法 则用 Constructor 创建
            Object object = cls.newInstance();
            //调用 parameters key的setter方法
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                Method method = cls.getMethod(setMethod(entry.getKey()), entry.getValue().getClass());
                method.invoke(object, entry.getValue());
            }
            //调用execute
            Method execute = cls.getMethod("execute");
            String result = (String) execute.invoke(object);

            //通过反射对象的getter方法 将属性名和对象的值 存入map
            Map<String, Object> map = new HashMap<>();
            Method[] allMethod = cls.getMethods();
            for (Method method : allMethod) {
                if (method.getName().startsWith("get")) {
                    map.put(propertyName(method.getName()), method.invoke(object));
                }
            }
            String jsp = action.getResults().get(result);
            //组装View对象
            View view = new View();
            view.setJsp(jsp);
            view.setParameters(map);
            return view;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //遍历所有的action节点
    private static void listNodes(List<Element> list) {
        for (Element node : list) {
            if ("action".equals(node.getName())) {
                String name = node.attributeValue("name");
                String className = node.attributeValue("class");
                Map<String, String> results = new HashMap<>();
                for (Iterator it = node.elementIterator(); it.hasNext(); ) {
                    Element element = (Element) it.next();
                    results.put(element.attributeValue("name"), element.getText());
                }
                Action action = new Action(name, className, results);
                actionMap.put(name, action);
            }
        }
    }

    private static String getMethod(String propertyName) {
        return "get" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
    }

    private static String setMethod(String propertyName) {
        return "set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
    }

    private static String propertyName(String methodName) {
        String propertyName = methodName.substring(3);
        return propertyName.toLowerCase().charAt(0) + propertyName.substring(1);
    }


    /**
     * 对应struts.xml中action节点
     */
    private static class Action {
        private String name;
        private String className;
        private Map<String, String> results;

        private Action(String name, String className, Map<String, String> results) {
            this.name = name;
            this.className = className;
            this.results = results;
        }

        public String getName() {
            return name;
        }

        public String getClassName() {
            return className;
        }

        public Map<String, String> getResults() {
            return results;
        }
    }

}
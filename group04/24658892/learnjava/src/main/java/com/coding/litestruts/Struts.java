package com.coding.litestruts;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Struts {

    private static Struts instance = new Struts();

    @SuppressWarnings("unchecked")
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
        Map<String, Action> actions = instance.parseXml();
        Action action = actions.get(actionName);
        View view = new View();
        view.setParameters(new HashMap<>());
        if (action != null) {
            try {
                Class clazz = Class.forName(action.getClazz());
                Object actionObj = clazz.newInstance();
                for (String s : parameters.keySet()) {
                    Method method = clazz.getDeclaredMethod("set" + captureName(s), String.class);
                    method.invoke(actionObj, parameters.get(s));
                }
                Method method = clazz.getDeclaredMethod("execute");
                Object o = method.invoke(actionObj);
                if (o != null) {
                    String flag = o.toString();
                    for (Result res : action.getResultList()) {
                        if (flag.equals(res.getName())) {
                            view.setJsp(res.getPage());
                        }
                    }
                }
                Method[] methods = clazz.getDeclaredMethods();
                for (Method m : methods) {
                    if (m.getName().startsWith("get")) {
                        view.getParameters().put(m.getName().substring(3).toLowerCase(), m.invoke(actionObj));
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    private Map<String, Action> parseXml() {
        Map<String, Action> actionMap = new HashMap<>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factory.newPullParser();
            pullParser.setInput(getClass().getClassLoader().getResourceAsStream("struts.xml"), null);
            int event = pullParser.getEventType();
            String tag = null;
            Action action = null;
            Result result = null;
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.TEXT:
                        String s = pullParser.getText();
                        if (result != null) {
                            result.setPage(s);
                        }
                        break;
                    case XmlPullParser.START_TAG:
                        tag = pullParser.getName();
                        if ("action".equals(tag)) {
                            String name = pullParser.getAttributeValue(null, "name");
                            String clazz = pullParser.getAttributeValue(null, "class");
                            action = new Action(name, clazz);
                            actionMap.put(name, action);
                        }
                        else if ("result".equals(tag)) {
                            String name = pullParser.getAttributeValue(null, "name");
                            result = new Result(name, "");
                            if (action != null) {
                                action.addResultList(result);
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("action".equals(tag)) {
                            action = null;
                        }
                        else if ("result".equals(tag)) {
                            result = null;
                        }
                        tag = null;
                        break;
                }
                event = pullParser.next();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return actionMap;
    }

    private static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}

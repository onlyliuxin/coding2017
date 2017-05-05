package com.coderising.litestruts;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {
        /*
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		根据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
		("name"="test" ,  "password"="1234") ,
		那就应该调用 setName和setPassword方法

		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"

		3. 通过反射找到对象的所有getter方法（例如 getMessage）,
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
		放到View对象的parameters

		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
		放到View对象的jsp字段中。
        */

        try {
            //0读取配置文件struts.xml
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File("src/com/coderising/litestruts/struts.xml"));
            Element root = doc.getRootElement();  //根元素
            Map<String,String> action = new HashMap<>();
            Map<String,String> loginResult = new HashMap<>();
            Map<String,String> logoutResult = new HashMap<>();
            java.util.Iterator iterator = root.elementIterator("action");
            while(iterator.hasNext()){
                Element actionNode = (Element) iterator.next();
                String key = actionNode.attributeValue("name");
                String value = actionNode.attributeValue("class");
                action.put(key,value);
                java.util.Iterator it = actionNode.elementIterator("result");
                while(it.hasNext()){
                    Element resultNode = (Element) it.next();
                    String k = resultNode.attributeValue("name");
                    String v = resultNode.getText();
                    if(key.equals("login")){
                        loginResult.put(k,v);
                    }else {
                        logoutResult.put(k,v);
                    }
                }
            }

            //1
            String className = action.get(actionName);  //获取类名
            Object o = Class.forName(className).newInstance();  //创建对象
            if(o instanceof LoginAction){
                LoginAction loginAction = (LoginAction) o;
                Set<Map.Entry<String,String>> set = parameters.entrySet();
                for (Map.Entry<String, String> en : set) {
                    if(en.getKey().equals("name")){
                        loginAction.setName(en.getValue());
                    }else if(en.getKey().equals("password")){
                        loginAction.setPassword(en.getValue());
                    }
                }

                //2
                Class<LoginAction> clazz = (Class<LoginAction>) loginAction.getClass();
                Method m = clazz.getDeclaredMethod("execute",null);
                m.setAccessible(true);
                String message = (String) m.invoke(loginAction,null);

                //3
                View view = new View();
                Map params = new HashMap();
                Method[] ms = clazz.getDeclaredMethods();
                for (Method method : ms) {
                    method.setAccessible(true);
                    if(method.getName().equals("getName")){
                        String value = (String) method.invoke(loginAction,null);
                        params.put("name",value);
                    }else if(method.getName().equals("getPassword")){
                        String value = (String) method.invoke(loginAction,null);
                        params.put("password",value);
                    }else if(method.getName().equals("getMessage")){
                        String value = (String) method.invoke(loginAction,null);
                        params.put("message",value);
                    }
                }
                view.setParameters(params);

                //4
                String jsp = loginResult.get(message);
                view.setJsp(jsp);

                return view;
            }else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }    

}

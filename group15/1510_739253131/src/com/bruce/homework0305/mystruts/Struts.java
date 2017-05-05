package com.bruce.homework0305.mystruts;

import com.sun.deploy.util.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) throws DocumentException,
            ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

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
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/com/bruce/homework0305/mystruts/struts.xml"));
        Element root = document.getRootElement();
        Iterator<Element> actions = root.elementIterator("action");
        HashMap<String,String> map = new HashMap<>();
        View view = new View();
        while(actions.hasNext()){
            Element next = actions.next();
            List<Attribute> attributes = next.attributes();
            Attribute action_name = next.attribute("name");
            if(actionName.equals(action_name.getValue())){
                //找到name="login"的action,拿到其class路径
                Attribute aClass = next.attribute("class");
                //通过反射拿到LoginAction类
               Class clazz = Class.forName(aClass.getValue());
               LoginAction login = (LoginAction) clazz.newInstance();
                //从parameters中拿到所有的key，通过这些key拿到对应的值，并且传入LoginAction对应的setter方法
                Set<String> keys = parameters.keySet();parameters.entrySet();
                for(String key : keys){
                    //首字母大写，拿到setter方法,并将parameters中对应该key的value拿出来，反射传入相应方法
                    String setter = "set"+ key.substring(0,1).toUpperCase()+key.substring(1,key.length());
                    Method method = clazz.getMethod(setter,String.class);
                    method.invoke(login,parameters.get(key));
                }
                //反射拿到execute方法，并拿到执行结果
                Method execute = clazz.getMethod("execute");
                String result = (String) execute.invoke(login);
                //反射拿到getMessage方法,结果以map格式保存，并保存在view对象中
                Method getMessage = clazz.getMethod("getMessage");
                String message = (String)getMessage.invoke(login);
                map.put("message",message);
                view.setParameters(map);
                //根据execute方法的执行结果，找到相关result的jsp路径，将该路径保存在view对象中
                Iterator<Element> results = next.elementIterator("result");
                while(results.hasNext()){
                    Element element = results.next();
                    if(result.equals(element.attribute("name").getValue())){
                       view.setJsp(element.getText());
                        break;
                    }
                }
            }
        }
        return view;
    }
}

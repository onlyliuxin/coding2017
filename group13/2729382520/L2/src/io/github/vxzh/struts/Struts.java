package io.github.vxzh.struts;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by vxzh on 28/02/2017.
 */
public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters)
            throws DocumentException, ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {

        Map<String, String> map = parseXML(actionName, "/Users/zzzz/Documents/IdeaProjects/demo/src/io/github/vxzh/struts/struts.xml");


        /**
         * 1. 根据actionName找到相对应的class, 例如LoginAction, 通过反射实例化（创建对象）
         *    据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
         *    ("name"="test" ,"password"="1234") ,那就应该调用setName和setPassword方法
         */
        Class clz = Class.forName(map.get(actionName));
        LoginAction loginAction = (LoginAction) clz.newInstance();
        Method name = clz.getMethod("setName", String.class);
        Method password = clz.getMethod("setPassword", String.class);
        name.invoke(loginAction, parameters.get("name"));
        password.invoke(loginAction, parameters.get("password"));

        /**
         * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
         */
        Method exectue = clz.getMethod("execute");
        String str = (String) exectue.invoke(loginAction);
        map.put("returnValue", str);

        /**
         * 3. 通过反射找到对象的所有getter方法（例如 getMessage）,
         *    通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
         *    放到View对象的parameters
         */
        Method getMessage = clz.getMethod("getMessage");
        String message = (String) getMessage.invoke(loginAction);
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("message", message);


        /**
         * 4. 根据struts.xml中的 <result> 配置, 以及execute的返回值, 确定哪一个jsp，
         *    放到View对象的jsp字段中。
         */
        View view = new View();
        view.setParameters(hashMap);
        String jsp = map.get(map.get("returnValue"));
        view.setJsp(jsp);
        return view;
    }

    public static Map<String, String> parseXML(String actionName, String path) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        File file = new File(path);
        Document document = saxReader.read(file);

        //获取根节点对象
        Element root = document.getRootElement();
        Element actionElement = null;
        HashMap<String, String> map = new HashMap<>();
        for (Iterator i = root.elementIterator(); i.hasNext(); ) {
            Element element = (Element) i.next();
            String name = element.attributeValue("name");
            String clazz = element.attributeValue("class");
            if (actionName.equals(name)) {
                actionElement = element;
                map.put(name, clazz);
            }
        }

        for (Iterator j = actionElement.elementIterator(); j.hasNext(); ) {
            Element elem = (Element) j.next();
            String resultName = elem.attributeValue("name");
            String jsp = elem.getText();
            map.put(resultName, jsp);
        }

        return map;
    }

}

package com.coding.litestruts;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Struts {

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
        String pathName = "D:\\Program Files\\GitRepository\\coding2017-1\\group19\\604322962\\learning2017" +
                "\\src\\main\\java\\com\\coding\\litestruts\\struts.xml";
        try {
            String className = getClassByActionName(pathName, actionName);
            Class<?> clazz = Class.forName(className);
            Object obj = clazz.newInstance();
            for ( Map.Entry entry : parameters.entrySet()) {
                String key = (String)entry.getKey();
                String methodName = "set"+key.substring(0,1).toUpperCase()+key.substring(1,key.length());
                Method setMethod = clazz.getMethod(methodName, String.class);//获取set方法
                setMethod.invoke(obj,entry.getValue());//调用set方法，设值
            }
            Method execute = clazz.getMethod("execute");
            String returnValue = (String)execute.invoke(obj);//获取返回值

            Method[] methods = clazz.getDeclaredMethods();
            Map<String, Object> resultMap = new HashMap<String, Object>();
            for (Method method : methods) {
                if (method.getName().startsWith("get")){
                    //判断当方法名以get开头,并且以get后的字符串为方法名的方法存在时
                    String fieldNameUpper = method.getName().substring(3,method.getName().length());
                    String fieldName = fieldNameUpper.substring(0,1).toLowerCase()+fieldNameUpper.
                            substring(1,fieldNameUpper.length());
                    Field field = clazz.getDeclaredField(fieldName);
                    if ( null != field) {
                        method.invoke(obj);
                        field.setAccessible(true);//设置属性访问属性
                        resultMap.put(fieldName, field.get(obj));
                    }
                }
            }
            View view = new View();
            view.setParameters(resultMap);
            String jsp = getResultByActionName(pathName, actionName, returnValue);
            view.setJsp(jsp);
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件的document对象，然后获取对应的根节点
     * @author chenleixing
     */
    private static String getClassByActionName(String pathName, String actionName) throws Exception{
        Element root = getRootElement(pathName);
        List<Element> elements = root.elements("action");
        for (Element e : elements) {
            if (e.attribute("name").getValue().equals(actionName))
                return e.attribute("class").getValue();
        }
        return null;
    }

    private static String getResultByActionName(String pathName, String actionName, String result) throws Exception {
        Element root = getRootElement(pathName);
        List<Element> elements = root.elements("action");
        for (Element e : elements) {
            if (e.attribute("name").getValue().equals(actionName)) {
                List<Element> elementList = e.elements("result");
                for (Element subE : elementList) {
                    if (subE.attribute("name").getValue().equals(result)) {
                        return subE.getTextTrim();
                    }
                }
            }
        }
        return null;
    }

    private static Element getRootElement(String pathName) throws Exception{
        SAXReader sax=new SAXReader();//创建一个SAXReader对象
        File xmlFile=new File(pathName);//根据指定的路径创建file对象
        Document document=sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
        Element root=document.getRootElement();//获取根节点
        return root;
    }
}

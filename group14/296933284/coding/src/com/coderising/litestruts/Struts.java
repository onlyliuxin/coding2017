package com.coderising.litestruts;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



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

        SAXReader reader = new SAXReader();
        File file = new File("src/com/coderising/litestruts/struts.xml");
        View view = new View();

        try {
            // 加载配置文件
            Document doucment = reader.read(file);
            Element struts = doucment.getRootElement();
            Iterator strutsIterator = struts.elementIterator();
            Element action = null;
            Object obj = null;

            while (strutsIterator.hasNext()) {
                action = (Element) strutsIterator.next();
                Attribute actionAttribute = action.attribute("name");

                // 通过反射获取实例化对象
                if (actionAttribute.getValue().equals(actionName)) {
                    obj = Class.forName(action.attribute("class").getValue()).newInstance();
                    break;
                }
            }

            Iterator<Map.Entry<String, String>> parametersIterator = parameters.entrySet().iterator();

            while (parametersIterator.hasNext()) {

                Map.Entry<String, String> entry = parametersIterator.next();

                // 调用相应属性的setter方法
                Method setterMethod = obj.getClass().getMethod("set" +
                        toUpperFisrtLetter(entry.getKey()), String.class);
                setterMethod.invoke(obj, entry.getValue());
            }

            Method exectueMethod= obj.getClass().getMethod("execute");
            String exectueValue = (String) exectueMethod.invoke(obj);

            // 获取类中的所有属性
            Field[] fields = obj.getClass().getDeclaredFields();
            HashMap<String, String> fieldHashMap = new HashMap<>();

            for(int i = 0; i < fields.length; i++){

                String fieldName = fields[i].getName();

                // 获取对应的getter方法
                Method getterMethod = obj.getClass().getMethod("get" +
                        toUpperFisrtLetter(fieldName));
                String fieldValue = (String) getterMethod.invoke(obj);

                fieldHashMap.put(fieldName, fieldValue);
            }


            view.setParameters(fieldHashMap);

            Iterator actionIterator = action.elementIterator();

            while (actionIterator.hasNext()) {
                Element result = (Element) actionIterator.next();
                Attribute resuAttribute = result.attribute("name");

                if (resuAttribute.getValue().equals(exectueValue)) {
                    view.setJsp(result.getStringValue());
                    break;
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return view;
    }

    // 将字符串首字母大写
    @NotNull
    private static String toUpperFisrtLetter(String str) {
        String string = str.toLowerCase();
        char[] cs = string.toCharArray();
        // 首字母大写
        cs[0] -= 32;

        return String.valueOf(cs);
    }

}

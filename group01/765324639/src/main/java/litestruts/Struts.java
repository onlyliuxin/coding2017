package litestruts;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters) {

        /*
         * 
         * 0. 读取配置文件struts.xml
         * 
         * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象） 据parameters中的数据，调用对象的setter方法，
         * 例如parameters中的数据是 ("name"="test" , "password"="1234") , 那就应该调用 setName和setPassword方法
         * 
         * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
         * 
         * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message": "登录成功"}
         * , 放到View对象的parameters
         * 
         * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中。
         * 
         */

        try {

            SAXReader reader = new SAXReader();
            InputStream struts = Struts.class.getResourceAsStream("struts.xml");
            Document document = null;
            try {
                document = reader.read(struts);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            String className = ""; // actionName对应的类名
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator("action");
            Element targetAction = null; // actionName对应的action
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                String name = element.attributeValue("name");
                if (name.equals(actionName)) {
                    className = element.attributeValue("class");
                    targetAction = element;
                    break;
                }
            }


            Class<?> class1 = Class.forName(className);
            Object instance = class1.newInstance();

            Set<String> keySet = parameters.keySet();
            for (String key : keySet) {
                // 将变量名拼成对应的set方法名
                String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                Class<?> type = class1.getDeclaredField(key).getType();
                Method method = class1.getDeclaredMethod(methodName, type);
                // 依次调用对应的set方法
                method.invoke(instance, parameters.get(key));
            }

            String result = (String) class1.getDeclaredMethod("execute").invoke(instance);

            Method[] declaredMethods = class1.getDeclaredMethods();
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < declaredMethods.length; i++) {
                if (declaredMethods[i].getName().startsWith("get")) {
                    String fieldValue = (String) declaredMethods[i].invoke(instance);
                    String fieldName = methodNameToFieldName(declaredMethods[i].getName());
                    map.put(fieldName, fieldValue);
                }
            }

            View view = new View();
            view.setParameters(map);

            Iterator elementIterator = targetAction.elementIterator("result");
            while (elementIterator.hasNext()) {
                Element element = (Element) elementIterator.next();
                if (result.equals(element.attributeValue("name"))) {
                    view.setJsp(element.getText());
                }
            }

            return view;


        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return null;
    }

    private static String methodNameToFieldName(String methodName) {
        if (!methodName.startsWith("get") && !methodName.startsWith("set")) {
            throw new IllegalArgumentException();
        }

        return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
    }

}

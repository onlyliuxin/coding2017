package main.coding_170302;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by peter on 2017/3/3.
 */
public class Struts {
    public static View runAction(String actionName, Map<String,String> parameters) throws DocumentException {
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
        View view = new View();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("DataStructure/src/main/coding_170302/struts.xml"));
        Element root = document.getRootElement();//获取根元素
        List<Element> listOfAction = root.elements("action");//获取action子元素
        int i=0;
        for(;i<listOfAction.size();i++){
            if(listOfAction.get(i).attributeValue("name").equals(actionName)){
                break;
            }
        }
        if(i==listOfAction.size()){
            System.out.println("没有对应的action");
        }
        Element action = listOfAction.get(i);
        String classPackage = action.attributeValue("class");
        try {
            Class c = Class.forName(classPackage);
            Object o = c.newInstance();//实例化对象
            Set<Map.Entry<String,String>> inputs = parameters.entrySet();
            //执行setName和setPassword
            for(Map.Entry<String,String> input:inputs){
                String key = input.getKey();
                String value = input.getValue();
                String methodName = "set"+Character.toUpperCase(key.charAt(0))+key.substring(1);
                Method method = c.getDeclaredMethod(methodName,String.class);
                method.invoke(o,value);
            }
            //执行execute方法，并返回message
            Method execute = c.getDeclaredMethod("execute");
            String returnMessage = (String)execute.invoke(o);

            Method[] methods = c.getDeclaredMethods();
            //获取所有getter方法，并且将值放到view的parameters中
            Map<String,String> paraMap = new HashMap<>();
            for(int j=0;j<methods.length;j++){
                String getName = methods[j].getName();
                if(getName.indexOf("get")!=-1){
                    String returnValue = (String)methods[j].invoke(o);
                    String functionName = Character.toLowerCase(getName.charAt(3))+getName.substring(4);
                    paraMap.put(functionName,returnValue);

                }
            }
            view.setPatameters(paraMap);
            //4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
           // 放到View对象的jsp字段中。
            List<Element> resultOfElements = action.elements("result");
            for(int k=0;k<resultOfElements.size();k++){
                if(returnMessage.indexOf(resultOfElements.get(k).attributeValue("name"))!=-1){
                    view.setJsp(resultOfElements.get(k).getText());
                    break;
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return view;
    }
}

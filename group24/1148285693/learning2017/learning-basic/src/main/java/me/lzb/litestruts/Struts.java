package me.lzb.litestruts;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


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


public class Struts {

    private static final String XML = "struts.xml";

    private static final XmlUtil resource = createResource(XML);

    private static XmlUtil createResource(String xml){
        try {
            return new XmlUtil(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static View runAction(String actionName, Map<String,String> parameters) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        Object loginAction = getAuctionByName(actionName);

        invokeSetMethods(loginAction, parameters);

        String resultName = invokeExecute(loginAction).toString();

        View view = new View();
        view.setJsp(resource.getResultJsp(actionName, resultName));
        view.setParameters(invokeGetMethods(loginAction));

    	return view;
    }

    private static Object getAuctionByName(String auctionName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName(resource.getAuctionPathByName(auctionName));
        return c.newInstance();
    }


    private static Object invokeExecute(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = o.getClass();
        Method mExectue = c.getDeclaredMethod("execute");
        return mExectue.invoke(o);
    }


    private static void invokeSetMethods(Object o, Map<String,String> parameteMap) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = o.getClass();
        Method[]  methods = c.getDeclaredMethods();
        for (int  i = 0;  i< methods.length; i++) {
            String name = methods[i].getName();
            if(StringUtils.startsWith(name, "set")){
                String key = name.replaceAll("^set", "").toLowerCase();
                if(parameteMap.containsKey(key)){
                    methods[i].invoke(o, parameteMap.get(key));
                }
            }
        }

//        //这样参数类型不固定的话不好搞
//        for (Map.Entry<String, String> entry : parameteMap.entrySet()) {
//            Method mSetter = c.getDeclaredMethod("set" + StringUtils.capitalize(entry.getKey()), String.class);
//            mSetter.invoke(o, entry.getValue());
//        }
    }


    private static Map invokeGetMethods(Object o) throws InvocationTargetException, IllegalAccessException {
        Map resultMap = new HashMap();
        Class c = o.getClass();
        Method[] methods = c.getDeclaredMethods();
        for(int i =0 ;i<methods.length;i++){
            String methodName = methods[i].getName();
            if(StringUtils.startsWith(methodName, "get")){
                String key = methodName.replaceAll("^get", "");
                key = StringUtils.lowerCase(key);
                resultMap.put(key,methods[i].invoke(o));
            }
        }
        return resultMap;
    }

}

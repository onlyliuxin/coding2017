package me.lzb.homework0319.litestruts;

import org.apache.commons.lang3.StringUtils;

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
    private static final XmlUtil xmlUtil = XmlUtil.create(XML);

    public static View runAction(String actionName, Map<String,String> parameters) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        Class loginClasss = Class.forName(xmlUtil.getAuctionPathByName(actionName));
        LoginAction loginAction = (LoginAction) loginClasss.newInstance();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            Method mSetter = loginClasss.getDeclaredMethod("set" + StringUtils.capitalize(entry.getKey()), String.class);
            mSetter.invoke(loginAction, entry.getValue());
        }

        Method mExectue = loginClasss.getDeclaredMethod("execute");
        String resultName = (String) mExectue.invoke(loginAction);


        //获取所有属性和get方法
        Map resultMap = new HashMap();
        Method[] methods = loginClasss.getDeclaredMethods();
        for(int i =0 ;i<methods.length;i++){
            String methodName = methods[i].getName();
            if(StringUtils.startsWith(methodName, "get")){
                String key = methodName.replaceAll("^get", "");
                key = StringUtils.lowerCase(key);
                resultMap.put(key,methods[i].invoke(loginAction));
            }
        }


        View view = new View();
        view.setJsp(xmlUtil.getResultJsp(actionName, resultName));
        view.setParameters(resultMap);


    	return view;
    }

}

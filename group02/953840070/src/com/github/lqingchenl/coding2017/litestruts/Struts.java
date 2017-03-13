package com.github.lqingchenl.coding2017.litestruts;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters) {

        try {
            Document d = Jsoup.parse(new File("src/com/github/lqingchenl/coding2017/litestruts/struts.xml"), "UTF-8"); //读取配置文件
            String classStr = null;

            Map<String, String> resultMap = new HashMap<>();
            for (Element element : d.select("action")) {
                if (element.attr("name").equals(actionName)) {
                    classStr = element.attr("class");
                    for (Element element1 : element.select("result")) {
                        resultMap.put(element1.attr("name"), element1.text());
                    }
                }
            }

            if (StringUtil.isBlank(classStr)) {
                return null;
            }
            Class<?> c = Class.forName(classStr);
            Object object = c.newInstance(); //创建对象
            //写数据 setName和setPassword方法
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                PropertyDescriptor pd = new PropertyDescriptor(entry.getKey(), c);//使用java.beans.PropertyDescriptor获取Method进行方法调用
                Method method = pd.getWriteMethod();//获得写方法
                method.invoke(object, entry.getValue());
            }

            //通过反射，执行execute方法
            Method method = c.getDeclaredMethod("execute", null);
            String result = (String) method.invoke(object);

//            通过反射找到对象的所有getter方法（例如 getMessage）,通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
//            放到View对象的parameters
            Map<String, String> map = new HashMap<>();
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), c); // message没有set方法，报错
                Method getMethod = pd.getReadMethod();
                String str = (String) getMethod.invoke(object);
                map.put(field.getName(), str);

            }
//          根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，放到View对象的jsp字段中。
            View view = new View();
            view.setParameters(map);
            String jsp = resultMap.get(result);
            view.setJsp(jsp);
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}

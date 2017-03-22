package week2.com.coding.litestruts;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts
{
    
    public static View runAction(String actionName, Map<String, String> parameters)
    {
        
        /*
         * 
         * 0. 读取配置文件struts.xml
         * 
         * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象） 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
         * ("name"="test" , "password"="1234") , 那就应该调用 setName和setPassword方法
         * 
         * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
         * 
         * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message": "登录成功"} ,
         * 放到View对象的parameters
         * 
         * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中。
         * 
         */
        File file = new File("src/week2/com/coding/litestruts/struts.xml");
        resolveXml(file);
        Object object = actions.get(actionName);
        Set<String> set = parameters.keySet();// 获取键值
        Iterator<String> it = set.iterator();
        View view = new View();
        try
        {
            while (it.hasNext())
            {
                String keyName = it.next();
                String setMethodName = "set" + keyName.substring(0, 1).toUpperCase() + keyName.substring(1);// 组合方法名
                
                Method method = object.getClass().getMethod(setMethodName, String.class);
                if (method == null)
                {
                    continue;
                }
                method.invoke(object, parameters.get(keyName));// 执行set方法
            }
            
            Method exeMethod = object.getClass().getMethod("execute");
            String result = (String)exeMethod.invoke(object);// 获取execute方法返回值
            // 获取对象所有的属性值
            Field[] fs = object.getClass().getDeclaredFields();
            HashMap<String, String> resultMap = new HashMap<String, String>();
            for (Field f : fs)
            {
                String fieldName = f.getName();
                Method m2 = object.getClass()
                    .getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                String rs2 = (String)m2.invoke(object);
                // 将所有get方法的返回值存入map
                resultMap.put(fieldName, rs2);
            }
            view.setParameters(resultMap);
            // 根据result的值找到xml配置的值
            if (null != result)
            {
                String viewURL = (String)actions.get(actionName + "_" + result);
                view.setJsp(viewURL);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return view;
    }
    
    static Map<String, Object> actions = new HashMap<String, Object>();
    
    /**
     * 解析XML,将xml映射对象，以及返回值的属性存入actions
     * 
     * @param file
     */
    @SuppressWarnings("unchecked")
    public static void resolveXml(File file)
    {
        SAXReader read = new SAXReader();
        Element rootElement = null;
        try
        {
            rootElement = read.read(file).getRootElement();
            List<Element> actionList = rootElement.elements("action");
            for (Element ele : actionList)
            {
                String name = ele.attributeValue("name");
                String clz = ele.attributeValue("class");// 找到类名
                Object obj = Class.forName(clz).newInstance();
                actions.put(name, obj);
                if (ele.hasContent())// 如果还有节点
                {
                    List<Element> list = ele.elements("result");
                    for (Element e : list)
                    {
                        String cName = e.attributeValue("name");
                        String cValue = e.getTextTrim();
                        actions.put(name + "_" + cName, cValue);// 示例key:login_success
                    }
                }
            }
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
        throws Exception
    {
        File file = new File("src/week2/com/coding/litestruts/struts.xml");
        resolveXml(file);
        System.out.println(actions.toString());
        // Map<String, String> parameters = new HashMap<String, String>();
        // parameters.put("name", "luojunyi");
        // runAction("login", parameters);
        // Class clazz = Class.forName("week2.com.coding.litestruts.LoginAction");
        // Object obj = clz.newInstance();
        // System.out.println(obj.toString());
        // Method m1 = clz.getMethod("setName", java.lang.String.class);
        // System.out.println(m1.getName());
        // m1.invoke(obj, "hello");
        // Method m2 = clz.getMethod("getName");
        // System.out.println(m2.getName());
        // String s = (String)m2.invoke(obj);
        // System.out.println(s);
        // Field[] f = clazz.getDeclaredFields();
        // for (int i = 0; i < f.length; i++)
        // {
        // System.out.println(f[i].getName());
        // }
    }
}

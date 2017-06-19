package code02.litestruts;

import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Struts {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Struts.class);
    /*
      0. 读取配置文件struts.xml

       1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
      据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
      ("name"="test" ,  "password"="1234") ,
      那就应该调用 setName和setPassword方法




  */
    public static View runAction(String actionName, Map<String, String> parameters)  {
        View view = new View();
        Configuration cfg = new Configuration("src/main/resources/struts.xml");
        ReflectionUtil reflectionUtil = new ReflectionUtil();
        reflectionUtil.initiateClazz(cfg);
       /* 1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）*/
        Object o = reflectionUtil.getInstance(actionName);
        /*2. 根据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
            ("name"="test" ,  "password"="1234") ,那就应该调用 setName和setPassword方法*/
        reflectionUtil.setParameters(o,parameters);
        /*3. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"*/
        String result = (String) reflectionUtil.runMethodWithoutParams(o,"execute");
       /* 4. 通过反射找到对象的所有getter方法（例如 getMessage）,通过反射来调用，
       把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,放到View对象的parameters*/
        Map params = new HashMap<String, String>();
        List<Method> methods = reflectionUtil.getGetterMethods(o.getClass());
        for(Method method : methods){
            String key = method.getName().substring(3);
            String value = null;
            try {
                value = (String) method.invoke(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            params.put(key,value);
        }
        /*5. 根据struts.xml中的 <result> 配置,以及execute的返回值，确定哪一个jsp，放到View对象的jsp字段中。*/
        String jsp = cfg.getView(actionName,result);
        view.setParameters(params);
        view.setJsp(jsp);

        return view;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        String actionName = "login";
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("name","test");
        params.put("password","12345");

        View view = Struts.runAction(actionName,params);
        System.out.println(view.getJsp());
        System.out.println(view.getParameters());


    }

}

package basic.liteStruts;

import java.lang.reflect.Method;
import java.util.Map;


public class Struts {
    /**
     * 0. 读取配置文件struts.xml
     * <p>
     * 1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
     * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
     * ("name"="test" ,  "password"="1234") ,
     * 那就应该调用 setName和setPassword方法
     * <p>
     * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
     * <p>
     * 3. 通过反射找到对象的所有getter方法（例如 getMessage）,
     * 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
     * 放到View对象的parameters
     * <p>
     * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
     * 放到View对象的jsp字段中。
     */
    public static View runAction(String actionName, Map<String, String> parameters) {
        try{
            //0. 读取配置文件struts.xml
            ReadXML read = new ReadXML("/resources/struts.xml");
            //1. 找到对应的class
            String className = read.getActionClass(actionName);
            Class clz = Class.forName(className);
            //得到对象
            Object la = clz.newInstance();
            setNameAndPassword(clz, la, parameters);
            //2. 调用execute方法
            String result = invokeExecute(clz, la);
            //3. 找到对象的所有getter方法
            getResultMap(clz, la, parameters);
            //4. 确定使用哪一个jsp
            String viewName = read.getJspPage(actionName, result);
            View view = new View();
            view.setJsp(viewName);
            view.setParameters(parameters);
            return view;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static void setNameAndPassword(Class clz, Object la, Map<String, String> parameters) throws Exception {
        Method setName = clz.getDeclaredMethod("setName", String.class);
        setName.invoke(la, parameters.get("name"));

        Method setPassword = clz.getDeclaredMethod("setPassword", String.class);
        setPassword.invoke(la, parameters.get("password"));
    }

    private static String invokeExecute(Class clz, Object la)throws Exception{
        Method execute = clz.getDeclaredMethod("execute", null);
        Method getMessage = clz.getDeclaredMethod("getMessage", null);
        execute.invoke(la, null);
        return getMessage.invoke(la, null).toString();
    }

    private static void getResultMap(Class clz, Object la, Map<String, String> parameters) throws Exception{
        Method[] methods = clz.getMethods();
        for(Method me : methods){
            if(me.getName().startsWith("get")){
                String info = me.invoke(la, null).toString();
                String method= me.getName();
                String key = method.substring(3, method.length()).toLowerCase();
                parameters.put(key, info);
            }else continue;
        }

    }
}

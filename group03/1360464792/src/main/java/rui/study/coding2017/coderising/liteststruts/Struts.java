package rui.study.coding2017.coderising.liteststruts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {
        View view=new View();
        try {
            StrutsAction strutsAction= StrutsAction.getFormStrutsMap(actionName);

            Class clazz=strutsAction.getaClass();
            Object object=clazz.newInstance();

            setActionValue(parameters,object);
            String resultStr = execute(object);
            getVlaue(view, object);
            getJspPath(view, strutsAction, resultStr);
        } catch (IOException e) {
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

    /**
     *
     1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
     据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
     ("name"="test" ,  "password"="1234") ,
     那就应该调用 setName和setPassword方法

     * @param parameters 请求参数
     * @param object 对象
     */
    private static void setActionValue(Map<String, String> parameters, Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (Map.Entry<String,String> entry: parameters.entrySet()) {
            String key=entry.getKey();
            String methodName="set"+key.substring(0,1).toUpperCase()+key.substring(1,key.length());
            Method method=object.getClass().getMethod(methodName,String.class);
            method.invoke(object,entry.getValue());
        }
    }

    /**
     * 2. 通过反射调用对象的execute 方法， 并获得返回值，例如"success"
     * @param object 反射生成的对象
     * @return 执行结果表示
     */
    private static String execute(Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
        Method executeMethod=object.getClass().getMethod("execute");
        Object returnObj=executeMethod.invoke(object);
        if(returnObj.getClass()!=String.class) throw new IOException("不支持非页面跳转类型");
        return (String) returnObj;
    }


    /**
     *
     3. 通过反射找到对象的所有getter方法（例如 getMessage）,
     通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
     放到View对象的parameters
     * @param view 视图
     * @param object 反射之后的action类
     */
    private static void getVlaue(View view, Object object) throws IllegalAccessException, InvocationTargetException {
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Method[] methods=object.getClass().getDeclaredMethods();
        for (Method getMethod:methods) {
            String methodName=getMethod.getName();
            if(methodName.contains("get")){
                Object o=getMethod.invoke(object);
                String fileName=methodName.replace("get","");
                fileName=fileName.substring(0,1).toLowerCase()+fileName.substring(1,fileName.length());
                resultMap.put(fileName,o);
            }
        }
        view.setParameters(resultMap);
    }

    /**
     4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
     放到View对象的jsp字段中。
     * @param view 视图
     * @param strutsAction xml解析后的存储类
     * @param resultStr 结果表示
     */
    private static void getJspPath(View view, StrutsAction strutsAction, String resultStr) {
        for (StrutsAction.Result result:strutsAction.getResults()) {
            if(result.name.equals(resultStr))view.setJsp(result.jspPath);
        }
    }
}

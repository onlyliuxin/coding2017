package code02.litestruts;

import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoyuan on 2017/3/21.
 */
public class ReflectionUtil {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    private static final Map<String, Class<?>> clazzMap = new HashMap<String, Class<?>>();

    //加载xml文件中的类
    public void initiateClazz(Configuration cfg){
        Map<String, ActionConfig> actionMap = cfg.getActionMap();

        for (Map.Entry<String, ActionConfig> entry : actionMap.entrySet()) {
            String actionName = entry.getKey(); //login
            ActionConfig actionConfig =entry.getValue();
            String className = actionConfig.getClassName(); //code02.litestruts.LoginAction
            Class<?> cls;
            try {
                cls = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
                clazzMap.put(actionName,cls);
            } catch (Exception e) {
                logger.warn("加载类 " + className + "出错！");
            }
        }

    }


    //返回实例对象
    public Object getInstance(String actionName){
        Object instance = null;
        for (Map.Entry<String, Class<?>> entry : clazzMap.entrySet()) {
            String action = entry.getKey(); //login
            Class<?> cls = entry.getValue(); //code02.litestruts.LoginAction.class
            if(actionName.equals(action)){
                try {
                    instance = cls.newInstance();
                } catch (Exception e) {
                    logger.error("生成实例出错！", e);
                    throw new RuntimeException(e);
                }
            }
        }
        return instance;
    }


    //参数赋值
    public void setParameters(Object o, Map<String, String> params) {

        List<Method> methods = getSetterMethods(o.getClass());
        for (String name : params.keySet()) {
            String methodName = "set" + name;
            for (Method m : methods) {
                if (m.getName().equalsIgnoreCase(methodName)) {
                    try {
                        m.invoke(o, params.get(name));
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //运行无参方法
    public Object runMethodWithoutParams(Object o , String methodName){
        Class<?> clz = o.getClass();
        Object result = null;
        try {
            Method method = clz.getDeclaredMethod(methodName);
            try {
                result = method.invoke(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    //返回以set开头的方法
    public List<Method> getSetterMethods(Class<?> clz){
        return getMethods(clz,"set");
    }

    //返回以get开头的方法
    public List<Method> getGetterMethods(Class<?> clz){
        return getMethods(clz,"get");
    }

    private List<Method> getMethods(Class<?> clz, String startWithName){
        List<Method> methodsList = new ArrayList<Method>();
        Method[] methods = clz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            String methodName = methods[i].getName();
            if(methodName.startsWith(startWithName)){
                methodsList.add(methods[i]);
            }
        }
        return methodsList;
    }

}

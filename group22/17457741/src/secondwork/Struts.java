package secondwork;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;





public class Struts {

   
    public static View runAction(String actionName, Map<String, String> parameters) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
        Map<String, StrutsAction> actionMap = StrutsParser.doParse();
        StrutsAction action = actionMap.get(actionName);

        if (action == null) {
            System.out.println("couldn't get action: " + actionName + ", return");
            return null;
        }

        try {
            // 通过反射, 创建实例对象
            Class<?> actionClass = Class.forName(action.getActionClassName());
            Object actionObj = actionClass.newInstance();

            // 调用 parameters 中的 set 方法
            for (Map.Entry<String, String> parameterEntry : parameters.entrySet()) {
                Method[] methods = actionClass.getMethods();
                for (Method method : methods) {
                    if (method.getName().equalsIgnoreCase("set" + parameterEntry.getKey())) {
                        method.invoke(actionObj, parameterEntry.getValue());
                    }
                }
            }
         
            Method executeMethod = actionClass.getMethod("execute");
            Object executeResult = executeMethod.invoke(actionObj);

            // 根据 execute 方法的结果, 获取 xml 配置的 jsp 页面
            String jsp = action.getAttributes().get(Objects.toString(executeResult));

           
            Map<String, String> actionFieldMap = new HashMap<>();
            Field[] actionFields = actionClass.getDeclaredFields();
            for (Field actionFiled : actionFields) {
                Method[] methods = actionClass.getMethods();
                for (Method method : methods) {
                    if (method.getName().equalsIgnoreCase("get" + actionFiled.getName())) {
                        method.invoke(actionObj);
                        actionFieldMap.put(actionFiled.getName(), Objects.toString(method.invoke(actionObj)));
                    }
                }
            }
           
            View view = new View();
            view.setParameters(actionFieldMap);
            view.setJsp(jsp);
            return view;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        return null;
    }
}

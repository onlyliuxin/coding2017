package com.coderising.litestruts;

import com.coderising.litestruts.dom.StrutsAction;
import com.coderising.litestruts.util.StrutsParser;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Struts {

    /**
     * 0. 读取配置文件struts.xml
     *
     * 1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
     * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
     * ("name"="test" ,  "password"="1234") ,
     * 那就应该调用 setName和setPassword方法
     *
     * 2. 通过反射调用对象的execute 方法， 并获得返回值，例如"success"
     *
     * 3. 通过反射找到对象的所有getter方法（例如 getMessage）,
     * 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
     * 放到View对象的parameters
     *
     * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
     * 放到View对象的jsp字段中。
     */
    public static View runAction(String actionName, Map<String, String> parameters) {
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
            //for (Map.Entry<String, String> parameterEntry : parameters.entrySet()) {
            //    for (PropertyDescriptor propertyDescriptor :
            //            Introspector.getBeanInfo(actionClass).getPropertyDescriptors()) {
            //        if (propertyDescriptor.getDisplayName().equals(parameterEntry.getKey())) {
            //            Method writeMethod = propertyDescriptor.getWriteMethod();
            //            writeMethod.invoke(actionObj, parameterEntry.getValue());
            //        }
            //    }
            //}

            // 调用 execute 方法
            Method executeMethod = actionClass.getMethod("execute");
            Object executeResult = executeMethod.invoke(actionObj);

            // 根据 execute 方法的结果, 获取 xml 配置的 jsp 页面
            String jsp = action.getAttributes().get(Objects.toString(executeResult));

            // 调用 get 方法
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
            //for (PropertyDescriptor propertyDescriptor :
            //        Introspector.getBeanInfo(actionClass).getPropertyDescriptors()) {
            //    Method readMethod = propertyDescriptor.getReadMethod();
            //    Object readMethodResult = readMethod.invoke(actionObj);
            //    actionFieldMap.put(propertyDescriptor.getDisplayName(), Objects.toString(readMethodResult));
            //}

            // 返回 View 对象
            View view = new View();
            view.setParameters(actionFieldMap);
            view.setJsp(jsp);
            return view;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }/* catch (IntrospectionException e) {
            e.printStackTrace();
        }*/

        return null;
    }
}

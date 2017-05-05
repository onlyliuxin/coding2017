package com.coding2017.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;

public class Struts {

    private static final String STRUTS_FILE_PATH = "/struts.xml";

    private static final String ACTION_EXECUTE_METHOD = "execute";

    /**
     * 0. 读取配置文件struts.xml
     * 
     * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象） 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
     * ("name"="test" , "password"="1234") , 那就应该调用 setName和setPassword方法
     * 
     * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
     * 
     * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message": "登录成功"} , 放到View对象的parameters
     * 
     * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中。
     * 
     * @param actionName
     * @param parameters
     * @return
     */
    public static View runAction(String actionName, Map<String, String> parameters) {

        if (Strings.isNullOrEmpty(actionName)) {
            return null;
        }

        StrutsDefinition strutsDefinition = StrutsXmlUtil
                .parseResource(Struts.class.getResourceAsStream(STRUTS_FILE_PATH));
        if (strutsDefinition == null) {
            return null;
        }
        StrutsDefinition.ActionDefinition actionDefinition = findActionDefinition(strutsDefinition, actionName);
        if (actionDefinition == null) {
            return null;
        }
        try {
            Class<?> actionClass = Class.forName(actionDefinition.getClazz());
            Object action = actionClass.newInstance();
            setParameter(actionClass, action, parameters);

            Method executeMethod = actionClass.getMethod(ACTION_EXECUTE_METHOD);
            String actionResult = (String) executeMethod.invoke(action);

            StrutsDefinition.ResultDefinition resultDefinition = findResultDefinition(actionDefinition, actionResult);
            if (resultDefinition == null) {
                return null;
            }

            View view = new View();
            view.setJsp(resultDefinition.getValue());
            view.setParameters(makeParameters(action, actionClass));
            return view;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map makeParameters(Object action, Class<?> actionClass)
            throws InvocationTargetException, IllegalAccessException {
        Method[] methods = actionClass.getMethods();
        Map map = new HashMap();
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                map.put(getField(method.getName()), method.invoke(action));
            }
        }
        return map;
    }

    private static String getField(String getMethodName) {
        String field = getMethodName.substring(3);
        return field.substring(0, 1).toLowerCase() + field.substring(1);
    }

    private static StrutsDefinition.ResultDefinition findResultDefinition(
            StrutsDefinition.ActionDefinition actionDefinition, String actionResult) {
        for (StrutsDefinition.ResultDefinition resultDefinition : actionDefinition.getResultDefinitions()) {
            if (resultDefinition.getName().equals(actionResult)) {
                return resultDefinition;
            }
        }
        return null;
    }

    private static void setParameter(Class<?> actionClass, Object action, Map<String, String> parameters)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (Map.Entry<String, String> paramEntry : parameters.entrySet()) {
            String setMethodName = getMethodName(paramEntry.getKey());
            Method method = actionClass.getMethod(setMethodName, String.class);
            method.invoke(action, paramEntry.getValue());
        }
    }

    private static String getMethodName(String field) {
        return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    private static StrutsDefinition.ActionDefinition findActionDefinition(StrutsDefinition strutsDefinition,
            String actionName) {
        for (StrutsDefinition.ActionDefinition definition : strutsDefinition.getActionDefinitionList()) {
            if (actionName.equals(definition.getName())) {
                return definition;
            }
        }
        return null;
    }

}

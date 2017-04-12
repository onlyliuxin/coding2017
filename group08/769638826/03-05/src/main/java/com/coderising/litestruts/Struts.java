package com.coderising.litestruts;

import com.coderising.litestruts.builder.StrutsConfigBuilder;
import com.coderising.litestruts.conf.StrutsConfiguration;
import com.coderising.litestruts.exception.LiteStrutsException;
import com.coderising.litestruts.model.Response;
import com.coderising.litestruts.model.View;
import com.coderising.litestruts.util.ClassUtil;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by huitailang on 17/3/4.
 * 核心类
 */
public class Struts {
    private static final String DEFAULT_HANDLER_METHOD = "exectue";

    private static StrutsConfiguration configuration;
    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("struts.xml");
    private static StrutsConfigBuilder builder = new StrutsConfigBuilder(inputStream);

    static {
        configuration = builder.parse();
    }

    public static View runAction(String actionName, Map<String, String> parameters) {
        String actionHandler = getActionHandler(actionName);

        try {
            if (actionHandler == null) {
                throw new LiteStrutsException("请求为" + actionName + "的action没有找到对应的action处理器");
            }

            Class actionHandlerClass = Class.forName(actionName);
            Object actionHandlerInstance = actionHandlerClass.newInstance();
            //根据请求参数，初始化action处理器
            Iterator<Map.Entry<String, String>> iterator = parameters.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> parameter = iterator.next();
                String fieldName  = parameter.getKey();
                String fieldValue = parameter.getValue();

                Method method = actionHandlerClass.getDeclaredMethod(ClassUtil.setter(fieldName), String.class);
                method.invoke(actionHandlerInstance, fieldValue);
            }

            //获取处理响应的handler方法
            Method handlerMethod = actionHandlerClass.getDeclaredMethod(DEFAULT_HANDLER_METHOD, Void.class);
            Object handlerResult = handlerMethod.invoke(actionHandlerInstance, null);

            //获取action处理器所有的getter方法
            Method[] getterMethods = ClassUtil.getGetterMethod(actionHandlerClass);


            //组装响应结果

        } catch (Exception e) {
            throw new LiteStrutsException("处理action为" + actionName + "的请求出错：", e);
        }

        return null;
    }

    private static String getActionHandler(String actionName) {
        Iterator<Map<String, String>> iterator = configuration.getActionMap().iterator();
        String actionHandler = null;

        while (iterator.hasNext()) {
            Map<String, String> map = iterator.next();
            if (map.get(actionName) != null) {
                actionHandler = map.get(actionName);
                break;
            }
        }

        return actionHandler;
    }

    private static View getView(String actionHandler, String responseCode) {
        Iterator<Map<String, Set<Response>>> iterator = configuration.getResponseMap().iterator();
        Set<Response> resultSet = null;
        View view = new View();

        while (iterator.hasNext()) {
            Map<String, Set<Response>> map = iterator.next();
            if (map.get(actionHandler) != null) {
                resultSet = map.get(actionHandler);
                break;
            }
        }

        if (resultSet != null && resultSet.size() > 0) {
            for (Iterator<Response> iterator1 = resultSet.iterator(); iterator1.hasNext(); ) {
                Response response = iterator1.next();
                if (response.getCode().equals(responseCode)) {
                    view.setJsp(response.getViewPath());
                }
            }
        }

        return view;
    }

    public Map<String, String> responseParameters(Object obj, Method[] getterMethods){
        Map<String, String> map = new HashMap<>();

        for(int i = 0 ; i < getterMethods.length; i++){
//            Method method =
        }

        return null;
    }
}

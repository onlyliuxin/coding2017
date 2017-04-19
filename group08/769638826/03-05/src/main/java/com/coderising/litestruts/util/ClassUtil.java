package com.coderising.litestruts.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huitailang on 17/3/5.
 *
 * @author zhangkun
 * @date 2017年03月05日17:56:35
 * Class操作看相关工具类
 */
public final class ClassUtil {
    public static final String DEFAULT_HANDLER_METHOD = "exectue";
    public static final String GETTER_METHOD_PREFIX = "get";
    public static final String SETTER_METHOD_PREFIX = "set";

    public static String setter(String fieldName) {
        return SETTER_METHOD_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static String getter(String fieldName) {
        return GETTER_METHOD_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static String extractFieldName(Method method) {
        return method.getName().substring(GETTER_METHOD_PREFIX.length(), GETTER_METHOD_PREFIX.length() + 1)
                + method.getName().substring(GETTER_METHOD_PREFIX.length() + 1);
    }

    public static Method[] getGetterMethod(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> methodList = new ArrayList<>();

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().startsWith(GETTER_METHOD_PREFIX)) {
                methodList.add(method);
            }
        }

        return ArrayUtil.convertCollectionToArray(methodList);
    }
}

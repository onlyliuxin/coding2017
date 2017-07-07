package com.coding.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luoziyihao on 5/25/16.
 */
public class BeanUtils {


    public static final String SET = "set";
    public static final String GET = "get";
    // 日志输出类
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final StringUtils2 stringUtils = new StringUtils2();

    public Object setInvoke(Object para, String methodName, Object obj) {
        Method method = null;
        Object returnObj = null;
        try {

            method = obj.getClass().getMethod(methodName, para.getClass());
            returnObj = method.invoke(obj, para);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return returnObj;
    }

    public Object getInvoke(String methodName, Object obj) {
        Method method = null;
        Object returnObj = null;
        try {
            method = obj.getClass().getMethod(methodName);
            returnObj = method.invoke(obj);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return returnObj;
    }

    public Object invokeWithNoParamter(String methodName, Object obj) {
        Method method;
        Object returnObj = null;
        try {
            method = obj.getClass().getMethod(methodName);
            returnObj = method.invoke(obj);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return returnObj;
    }



    public Object getPara(String paraName, Object object) {
        if (stringUtils.isSpaceOrNull(paraName)) {
            throw new RuntimeException("paraname is null or space");
        }
        String methodName = new StringBuilder().append(GET).append(stringUtils.toUpperCase(paraName, 0)).toString();
        return getInvoke(methodName, object);
    }



    public Object setPara(Object para, String paraName, Object object) {
        if (stringUtils.isSpaceOrNull(paraName)) {
            throw new RuntimeException("paraname is null or space");
        }
        String methodName = new StringBuilder().append(SET).append(stringUtils.toUpperCase(paraName, 0)).toString();
        return setInvoke(para, methodName, object);
    }

    public <T> T get(String paraName, Object object, Class<T> clazz) {
        return (T) getPara(paraName, object);
    }

    public Integer getInt(String paraName, Object object) {
        return (Integer) getPara(paraName, object);
    }

    public Long getLong(String paraName, Object object) {
        return (Long) getPara(paraName, object);
    }

    public Double getDouble(String paraName, Object object) {
        return (Double) getPara(paraName, object);
    }

    public BigDecimal getBigDecimal(String paraName, Object object) {
        return (BigDecimal) getPara(paraName, object);
    }

    public String getString(String paraName, Object object) {
        return getPara(paraName, object).toString();
    }

    public Date getDate(String paraName, Object object) {
        return (Date) getPara(paraName, object);
    }

    public Long getLongByString(String paraName, Object object) {
        return Long.parseLong(getString(paraName, object));
    }

    public Integer getIntByString(String paraName, Object object) {
        return Integer.parseInt(getString(paraName, object));
    }

    public Double getDoubleByString(String paraName, Object object) {
        return Double.parseDouble(getString(paraName, object));
    }

    public BigDecimal getBigDecimalByString(String paraName, Object object) {
        return new BigDecimal(getString(paraName, object));
    }

    private final static String GETTER_PRE = "get";
    public Map<String, Object> describe(Object model) {
        Method[] methods = model.getClass().getDeclaredMethods(); //获取实体类的所有属性，返回Field数组
        Map<String, Object> properties = new HashMap<>();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith(GETTER_PRE)) {
                try {
                    Object o = method.invoke(model);
                    String valueName = stringUtils.toLowwerCase(methodName.substring(GETTER_PRE.length()), 0);
                    properties.put(valueName, o);
                } catch (IllegalAccessException | InvocationTargetException e) {
                   throw new IllegalStateException(e);
                }
            }
        }
        return properties;
    }
}

package com.coderising.myknowledgepoint.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by thomas_young on 23/9/2017.
 * http://www.cnblogs.com/luoxn28/p/5686794.html
 * 调用处理器类
 */
public class DynamicProxyHandler implements InvocationHandler {
    private Object proxyed;

    public DynamicProxyHandler(Object proxyed) {
        this.proxyed = proxyed;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("代理工作了.");
        return method.invoke(proxyed, args);
    }
}

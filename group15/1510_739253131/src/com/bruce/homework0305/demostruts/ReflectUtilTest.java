package com.bruce.homework0305.demostruts;

import junit.framework.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectUtilTest {

    @Test
    public void testGetSetterMethods() throws Exception{
        String name = "com.bruce.homework0305.demostruts.LoginAction";
        Class<?> aClass = Class.forName(name);
        List<Method> setterMethods = ReflectUtil.getSetterMethods(aClass);
        List<String> expectNames = new ArrayList<>();
        expectNames.add("setName");
        expectNames.add("setPassword");
        List<String> acctualNames = new ArrayList<>();
        for(Method method: setterMethods) {
            acctualNames.add(method.getName());
        }
        Assert.assertTrue(acctualNames.containsAll(expectNames));
    }

    @Test
    public void testSetParameters() throws Exception{
        String name = "com.bruce.homework0305.demostruts.LoginAction";
        Class<?> clz = Class.forName(name);
        Object o = clz.newInstance();
        Map<String, String> params = new HashMap<>();
        params.put("name","test");
        params.put("password","1234");
        ReflectUtil.setParameters(o, params);
        Field f = clz.getDeclaredField("name");
        f.setAccessible(true);
        Assert.assertEquals("test",f.get(o));
        f = clz.getDeclaredField("password");
        f.setAccessible(true);
        Assert.assertEquals("1234",f.get(o));
    }

    public void testGetParameters() throws Exception{
        String name = "com.bruce.homework0305.demostruts.LoginAction";
        Class<?> clz = Class.forName(name);
    }
}

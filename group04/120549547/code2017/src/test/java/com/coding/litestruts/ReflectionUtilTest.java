package com.coding.litestruts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by bobi on 2017/4/1.
 * at code2017
 */
public class ReflectionUtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetSetterMethod() throws ClassNotFoundException {
        String name = "com.coding.litestruts.LoginAction";

        Class clz = Class.forName(name);
        List<Method> methods = ReflectionUtil.getSetterMethods(clz);

        Assert.assertEquals(2, methods.size());

        List<String> expectedNames = new ArrayList<>();
        expectedNames.add("setName");
        expectedNames.add("setPassword");

        List<String> acctualNames = new ArrayList<>();
        for (Method method : methods) {
            acctualNames.add(method.getName());
        }

        Assert.assertTrue(acctualNames.containsAll(expectedNames));

    }

    @Test
    public  void testSetParameters() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        String name = "com.coding.litestruts.LoginAction";

        Class clz = Class.forName(name);
        Object o = clz.newInstance();

        Map<String, String> params = new HashMap<>();
        params.put("name", "test");
        params.put("password", "1234");

        ReflectionUtil.setParameters(o, params);


        Field f = clz.getDeclaredField("name");
        f.setAccessible(true);
        Assert.assertEquals("test", f.get(o));

    }


    @Test
    public void testGetGetterMethod() throws ClassNotFoundException {
        String name = "com.coding.litestruts.LoginAction";

        Class clz = Class.forName(name);
        List<Method> methods = ReflectionUtil.getGetterMethods(clz);

        Assert.assertEquals(3, methods.size());

        List<String> expectedNames = new ArrayList<>();
        expectedNames.add("getName");
        expectedNames.add("getPassword");
        expectedNames.add("getMessage");

        List<String> acctualNames = new ArrayList<>();

        for (Method method : methods) {
            acctualNames.add(method.getName());
        }

        Assert.assertTrue(acctualNames.containsAll(expectedNames));

    }

    @Test
    public  void testGetParameters() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        String name = "com.coding.litestruts.LoginAction";
        Class clz = Class.forName(name);

        LoginAction o = (LoginAction) clz.newInstance();
        o.setName("test");
        o.setPassword("123456");

        Map<String, Object> params;
        params = ReflectionUtil.getParamterMap(o);

        Assert.assertEquals(3, params.size());
        Assert.assertEquals(null, params.get("message"));
        Assert.assertEquals("test", params.get("name"));
        Assert.assertEquals("123456", params.get("password"));


    }

    @Test
    public  void testDouble(){
        double d = 6.02e23;
        long i = (long) d;
        System.out.println(i);
    }
}
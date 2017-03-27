package com.donaldy.litestruts;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by donal on 2017/3/21.
 */
public class ReflectionUtiilTest {

    @Test
    public void testGetSetterMethod() throws Exception {

        String name = "com.coderising.litestruts.LoginAction";
        Class<?> clz = Class.forName(name);
        List<Method> methods = ReflectionUtil.getSetterMethods(clz);

        Assert.assertEquals(2, methods.size());

        List<String> expectedNames = new ArrayList<>();
        expectedNames.add("setName");
        expectedNames.add("setPassword");

        Set<String> acctualNames = new HashSet<>();
        for(Method m : methods){
            acctualNames.add(m.getName());
        }

        Assert.assertTrue(acctualNames.containsAll(expectedNames));
    }

    @Test
    public void testSetParameters() throws Exception{

        String name = "com.coderising.litestruts.LoginAction";
        Class<?> clz = Class.forName(name);
        Object o = clz.newInstance();

        Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");

        ReflectionUtil.setParameters(o,params);



        Field f = clz.getDeclaredField("name");
        f.setAccessible(true);
        Assert.assertEquals("test", f.get(o));

        f = clz.getDeclaredField("password");
        f.setAccessible(true);
        Assert.assertEquals("1234", f.get(o));
    }
    @Test
    public void testGetGetterMethod() throws Exception{
        String name = "com.coderising.litestruts.LoginAction";
        Class<?> clz = Class.forName(name);
        List<Method> methods = ReflectionUtil.getGetterMethods(clz);

        Assert.assertEquals(3, methods.size());

        List<String> expectedNames = new ArrayList<>();
        expectedNames.add("getMessage");
        expectedNames.add("getName");
        expectedNames.add("getPassword");

        Set<String> acctualNames = new HashSet<>();
        for(Method m : methods){
            acctualNames.add(m.getName());
        }

        Assert.assertTrue(acctualNames.containsAll(expectedNames));
    }

    @Test
    public void testGetParamters() throws Exception{
        String name = "com.donaldy.litestruts.LoginAction";
        Class<?> clz = Class.forName(name);
        LoginAction action = (LoginAction)clz.newInstance();
        action.setName("test");
        action.setPassword("123456");




        Map<String,Object> params = ReflectionUtil.getParamterMap(action);

        Assert.assertEquals(3, params.size());

        Assert.assertEquals(null, params.get("messaage") );
        Assert.assertEquals("test", params.get("name") );
        Assert.assertEquals("123456", params.get("password") );
    }
}

package com.coderising.litestruts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static  org.junit.Assert.*;
/**
 * ReflectionUtil Test
 */
public class ReflectionUtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetParameters(){
        String clzName = "com.coderising.litestruts.LoginAction";
        try {
            Class clz = Class.forName(clzName);
            Object o = clz.newInstance();

            Map<String,Object> params = new HashMap();
            params.put("name","test");
            params.put("password","1234");
            ReflectionUtil.setParameters(o,params);

            Field field  = clz.getField("name");
            field.setAccessible(true);
            assertEquals("test",field.get(o));
            field = clz.getField("password");
            field.setAccessible(true);
            assertEquals("1234",field.get(0));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
package com.coderising.litestruts;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReflectionUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSetterMethod() throws ClassNotFoundException {
		String name = "com.coderising.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		List<Method> methods=ReflectionUtil.getSetterMethods(clz);
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
	public void testGetGetterMethod() throws ClassNotFoundException {
		String name = "com.coderising.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		List<Method> methods=ReflectionUtil.getGetterMethods(clz);
		Assert.assertEquals(3, methods.size());
		
		List<String> expectedNames = new ArrayList<>();
		expectedNames.add("getName");
		expectedNames.add("getPassword");
		expectedNames.add("getMessage");
		Set<String> acctualNames = new HashSet<>();
		for(Method m : methods){
			acctualNames.add(m.getName());
		}
		Assert.assertTrue(acctualNames.containsAll(expectedNames));
	}
	@Test
	public void testSetterParams() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String name = "com.coderising.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		Object o = clz.newInstance();
		
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");
        ReflectionUtil.setParams(o,params);
        
        Field f = clz.getDeclaredField("name");
		f.setAccessible(true);
		Assert.assertEquals("test", f.get(o));
		
		f = clz.getDeclaredField("password");
		f.setAccessible(true);
		Assert.assertEquals("1234", f.get(o));
	}
	@Test
	public void testGetterParams() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String name = "com.coderising.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		LoginAction la = (LoginAction) clz.newInstance();
		la.setName("test");
		la.setPassword("123456");
		Map<String,Object> params =ReflectionUtil.getParams(la);
		Assert.assertEquals(3, params.size());
		
		Assert.assertEquals(null, params.get("messaage") );
		Assert.assertEquals("test", params.get("name") );
		Assert.assertEquals("123456", params.get("password") );
	}
}

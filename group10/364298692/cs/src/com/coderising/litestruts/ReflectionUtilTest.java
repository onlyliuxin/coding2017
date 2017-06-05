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
	public void testGetSetterMethod() throws Exception{//JUnit中的异常不需要处理，抛出即可
		String name = "com.coderising.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		List<Method> methods = ReflectionUtil.getSetterMethods(clz);
		
		Assert.assertEquals(2, methods.size());
		
		List<String> expectedNames = new ArrayList<>();
		expectedNames.add("setName");
		expectedNames.add("setPassword");
		
		Set<String> actualNames = new HashSet<>();
		for(Method m : methods){
			actualNames.add(m.getName());
		}
		
		Assert.assertTrue(actualNames.containsAll(expectedNames));
		
	}
	
	@Test
	public void testSetParameters() throws Exception{
		//实例化LoginAction对象
		String name = "com.coderising.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		Object o = clz.newInstance();
		
		//参数放进map中
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "test");
		params.put("password", "1234");
		
		ReflectionUtil.setParameters(o, params);
		
		Field f = clz.getDeclaredField("name");
		f.setAccessible(true);//忽略访问权限的限制，可直接调用
		Assert.assertEquals("test", f.get(o));
				
	}
	
	@Test
	public void testGetMethod() throws Exception{
		String name = "com.coderising.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		List<Method> methods = ReflectionUtil.getGetterMethods(clz);
		
		Assert.assertEquals(3, methods.size());		
		
		List<String> expectedNames = new ArrayList<>();
		expectedNames.add("getName");
		expectedNames.add("getPassword");
		expectedNames.add("getMessage");
		
		Set<String> actualNames = new HashSet<>();
		for(Method m : methods){
			if(m.getName().startsWith("get")){
				actualNames.add(m.getName());
			}
		}
		
		Assert.assertTrue(actualNames.containsAll(expectedNames));
		
	}
	
	@Test
	public void testGetParameters() throws Exception{
		//实例化LoginAction对象
		String name = "com.coderising.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		LoginAction action = (LoginAction)clz.newInstance();
		action.setName("test");
		action.setPassword("123456");
		
		Map<String,Object> params = ReflectionUtil.getParamterMap(action);
		
		Assert.assertEquals(3, params.size());
		
		Assert.assertEquals(null, params.get("message"));
		Assert.assertEquals(params.get("name"), "test");
		Assert.assertEquals(params.get("password"), "123456");
	}

}

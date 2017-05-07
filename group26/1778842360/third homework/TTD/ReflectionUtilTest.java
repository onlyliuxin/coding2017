package com.coderising.litestruts;

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
	    public void setUp() throws Exception{
	    	
	    }
	    @After
	    public void TearDown() throws Exception{
	    	
	    }
	@Test
	public void testGetSetterMethod() throws ClassNotFoundException {
		 String name="com.coderising.litestruts.LoginAction";
		 Class<?> clz=Class.forName(name);
		 List<Method> methods=ReflectionUtil.getSetterMethods(clz);
		 
		 Assert.assertEquals(2, methods.size());
		 
		 List<String> expectedNames=new ArrayList<>();
		 expectedNames.add("setName");
		 expectedNames.add("setPassword");
		 
		 Set<String> actualNames=new HashSet<>();
		 for(Method m:methods){
			 actualNames.add(m.getName());
		 }
		 Assert.assertTrue(actualNames.containsAll(expectedNames));
	}
	@Test
	public void testSetParameter() throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException
	{
		String name="com.coderising.litestruts.LoginAction";
		Class<?> clz=Class.forName(name);
		
		Object o=clz.newInstance();
		
		Map<String,String> params=new HashMap<String,String>();
		params.put("name", "test");
		params.put("password", "1234");
		
		ReflectionUtil.setParameters(o,params);
		
		Field f=clz.getDeclaredField("name");
		f.setAccessible(true);
		Assert.assertEquals("test", f.get(o));
		
		f=clz.getDeclaredField("password");
		f.setAccessible(true);
		Assert.assertEquals("1234", f.get(o));	
	}
	@Test
	public void testGetGetterMethod() throws Exception
	{
		String name="com.coderising.litestruts.LoginAction";
		Class<?> clz=Class.forName(name);
		List<Method> methods=ReflectionUtil.getGetterMethods(clz);
		
		Assert.assertEquals(3,methods.size());
		
		List<String> expectedNames=new ArrayList<>();
		 expectedNames.add("getMessage");
		 expectedNames.add("getName");
		 expectedNames.add("getPassword");
		 
		 Set<String> actualNames=new HashSet<>();
		 for(Method m:methods)
		 {
			 actualNames.add(m.getName());
		 }
		 Assert.assertTrue(actualNames.containsAll(expectedNames));
	}
	@Test
	public void testGetParmters() throws Exception
	{
		String name="com.coderising.litestruts.LoginAction";
		Class<?> clz=Class.forName(name);
		
		LoginAction action=(LoginAction)clz.newInstance();
		action.setName("test");
		action.setPassword("123456");
		
		Map<String,Object> params=ReflectionUtil.getParamterMap(action);
		Assert.assertEquals(3,params.size());
		
		Assert.assertEquals(null, params.get("message"));
		Assert.assertEquals("test",params.get("name"));
		Assert.assertEquals("123456",params.get("password"));
	}
	

}

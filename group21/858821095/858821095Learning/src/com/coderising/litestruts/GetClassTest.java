package com.coderising.litestruts;



import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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



public class GetClassTest {

	SparseXml sx = new SparseXml("struts.xml");
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void GetLoginClasstest() {
		String actionName = "login";
		String ex = "com.coderising.litestruts.LoginAction";
		String result = sx.getLoginClass(actionName);
		Assert.assertEquals(ex, result);
	}
	
	@Test
	public void GetLogoutClasstest(){
		String actionName = "logout";
		String ex = "com.coderising.litestruts.LogoutAction";
		String result = sx.getLogoutClass(actionName);
		Assert.assertEquals(ex, result);
	}

	@Test
	public void GetSetMethodstest() throws ClassNotFoundException{
		String className = "com.coderising.litestruts.LoginAction";
		Class clz = Class.forName(className);
		List<String> exMethods = new ArrayList<>();
		exMethods.add("setName");
		exMethods.add("setPassword");
		List<Method> setMethods = sx.getSetMethods(clz);
		for(int i=0;i<exMethods.size();i++){
			Assert.assertEquals(exMethods.get(i), setMethods.get(i).getName());
		}
		
	}
	
	@Test
	public void SetParamstest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException{
		String className = "com.coderising.litestruts.LoginAction";
		Class clz = Class.forName(className);
		LoginAction o = (LoginAction) clz.newInstance();
		Map<String, String> exSetParams = new HashMap<>();
		exSetParams.put("name", "test");
		exSetParams.put("password", "12345");
		sx.setParams(o, exSetParams);
		
		Field f = clz.getDeclaredField("name");
		f.setAccessible(true);
		Assert.assertEquals("test", f.get(o));
		
		f = clz.getDeclaredField("password");
		f.setAccessible(true);
		Assert.assertEquals("12345", f.get(o));
	}
	
	
	@Test
	public void GetGetMethodstest() throws ClassNotFoundException{
		String className = "com.coderising.litestruts.LoginAction";
		Class clz = Class.forName(className);
		List<String> exMethods = new ArrayList<>();
		exMethods.add("getName");
		exMethods.add("getPassword");
		exMethods.add("getMessage");
		
		List<Method> getMethods = sx.getGetMethods(clz);
		Set<String> getMset = new HashSet<>();
		for(int i=0;i<getMethods.size();i++){
			getMset.add(getMethods.get(i).getName());
		}
		
		Assert.assertTrue(getMset.containsAll(exMethods));
	}
	
	@Test
	public void GetParamstest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String className = "com.coderising.litestruts.LoginAction";
		Class clz = Class.forName(className);
		LoginAction o = (LoginAction) clz.newInstance();
		
		o.setName("test");
		o.setPassword("12345");
		
		
		Map<String, Object> getParams = sx.getParams(o);
		Assert.assertEquals(3, getParams.size());
		Assert.assertEquals("test", getParams.get("name"));
		Assert.assertEquals("12345", getParams.get("password"));
		Assert.assertEquals(null, getParams.get("message"));
	}
	
	@Test
	public void GetExcutetest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		String className = "com.coderising.litestruts.LoginAction";
		Class clz = Class.forName(className);
		LoginAction o = (LoginAction) clz.newInstance();
		Map<String, String> exSetParams = new HashMap<>();
		exSetParams.put("name", "test");
		exSetParams.put("password", "12345");
		sx.setParams(o, exSetParams);
		String result = sx.GetExcute(o);
		Assert.assertEquals("success", result);
	}
	
	@Test
	public void GetResultViewtest(){
		String resultJsp = sx.getResultView("login", "success");
		Assert.assertEquals("/jsp/homepage.jsp", resultJsp);
		
		resultJsp = sx.getResultView("login", "fail");
		Assert.assertEquals("/jsp/showLogin.jsp", resultJsp);
		
		resultJsp = sx.getResultView("logout", "success");
		Assert.assertEquals("/jsp/welcome.jsp", resultJsp);
		
		resultJsp = sx.getResultView("logout", "error");
		Assert.assertEquals("/jsp/error.jsp", resultJsp);
		
		
	}
}

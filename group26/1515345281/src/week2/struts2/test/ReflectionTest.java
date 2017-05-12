package week2.struts2.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week2.struts2.LoginAction;
import week2.struts2.ReflectionUtil;

public class ReflectionTest {

	@Test
	public void testGetSetterMethods() throws ClassNotFoundException {

		String clazzName = "week2.struts2.LoginAction";

		Class<?> clazz = Class.forName(clazzName);

		List<Method> methods = ReflectionUtil.getSetterMethods(clazz);

		assertEquals(3, methods.size());

		List<String> expectedNames = new ArrayList<String>();
		expectedNames.add("setUserName");
		expectedNames.add("setPassword");
		expectedNames.add("setMessage");

		Set<String> actualsNames = new HashSet<>();
		for (Method m : methods) {
			actualsNames.add(m.getName());
		}

		assertTrue(actualsNames.containsAll(expectedNames));
	}

	@Test
	public void testGetGetterMethods() throws ClassNotFoundException {

		String clazzName = "week2.struts2.LoginAction";
		Class<?> clazz = Class.forName(clazzName);
		List<Method> methods = ReflectionUtil.getGetterMethods(clazz);

		assertEquals(3, methods.size());

		List<String> expectedNames = new ArrayList<>();
		expectedNames.add("getUserName");
		expectedNames.add("getPassword");
		expectedNames.add("getMessage");

		Set<String> actualNames = new HashSet<>();
		for (Method m : methods) {
			actualNames.add(m.getName());
		}

		assertTrue(actualNames.containsAll(expectedNames));
	}

	@Test
	public void testSetParameters() throws Exception {

		String clazzName = "week2.struts2.LoginAction";
		Class<?> clazz = Class.forName(clazzName);

		Object o = clazz.newInstance();
		Map<String, String> params = new HashMap<>();
		params.put("userName", "沈健");
		params.put("password", "123456");

		ReflectionUtil.setParameters(o, params);

		Field userName = clazz.getDeclaredField("userName");
		userName.setAccessible(true);
		assertEquals(userName.get(o), "沈健");

		Field password = clazz.getDeclaredField("password");
		password.setAccessible(true);
		assertEquals(password.get(o), "123456");

	}

	@Test
	public void testGetParameterMap() throws Exception {

		String clazzName = "week2.struts2.LoginAction";
		Class<?> clazz = Class.forName(clazzName);
		LoginAction action = (LoginAction) clazz.newInstance();
		action.setUserName("沈健");
		action.setPassword("123456");

		Map<String, Object> params = ReflectionUtil.getParamterMap(action);

		Assert.assertEquals(3, params.size());

		Assert.assertEquals(null, params.get("messaage"));
		Assert.assertEquals("沈健", params.get("username"));
		Assert.assertEquals("123456", params.get("password"));
	}

}

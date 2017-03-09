package com.louis.action;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;

public class LYStructTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testLoginActionSuccess() {
		String actionName = "login";
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("name", "test");
		parameters.put("password", "123456");
		
		LYView view = LYStruct.runAction(actionName, parameters);
		
		Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
		Assert.assertEquals("login successful", view.getParameters().get("message"));
	}
	
	@Test
	public void testLoginActionFailed() {
		String actionName = "login";
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("name", "test");
		parameters.put("password", "121212");
		
		LYView view = LYStruct.runAction(actionName, parameters);
		
		Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
		Assert.assertEquals("login failed, please check your user/pwd", view.getParameters().get("message"));
	}
	
	@Test
	public void testLogoutActionSuccess() {
		String actionName = "logout";
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("name", "test");
		parameters.put("password", "123456");
		
		LYView view = LYStruct.runAction(actionName, parameters);
		
		Assert.assertEquals("/jsp/welcome.jsp", view.getJsp());
		Assert.assertEquals("logout successful", view.getParameters().get("message"));
	}
	
	@Test
	public void testLogoutActionFailed() {
		String actionName = "logout";
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("name", "test");
		parameters.put("password", "121212");
		
		LYView view = LYStruct.runAction(actionName, parameters);
		
		Assert.assertEquals("/jsp/error.jsp", view.getJsp());
		Assert.assertEquals("logout failed, please check your user/pwd", view.getParameters().get("message"));
	}
}

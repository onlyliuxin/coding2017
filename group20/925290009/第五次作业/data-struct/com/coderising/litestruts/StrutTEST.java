package com.coderising.litestruts;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class StrutTEST {

	@Test
	public void runActionSuccess() {
		String action = "login";
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("name", "test");
		hashMap.put("password", "1234");
		
		View view = Struts.runAction(action, hashMap);
		Assert.assertEquals("login successful", view.getParameters().get("message"));
		Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
	}
	
	@Test
	public void runActionFail(){
String action = "login";
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("name", "test");
		hashMap.put("password", "12345");
		
		View view = Struts.runAction(action, hashMap);
		Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
		Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
		
	}

}

package com.coderising.litestruts;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.coderising.litestruts.exception.StrutsRunActionException;





public class StrutsTest {
	Struts struts = Struts.getInstance();

	
	@Test
	public void testLoginActionSuccess() {
		
		String actionName = "login";
        
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");
        
        
        View view = null;
		try {
			view = struts.runAction(actionName,params);
		} catch (StrutsRunActionException e) {
			e.printStackTrace();
		}        
        
        Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
        Assert.assertEquals("login successful", view.getParameters().get("message"));
	}

	@Test
	public void testLoginActionFailed() {
		String actionName = "login";
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","123456"); //密码和预设的不一致
        
        View view = null;
		try {
			view = struts.runAction(actionName,params);
		} catch (StrutsRunActionException e) {
			e.printStackTrace();
		}        
        
        Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
        Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
	}
}

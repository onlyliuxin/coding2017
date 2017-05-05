package com.github.ipk2015.coding2017.coderising.litestruts;



import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;





public class StrutsTest {

	@Test
	public void testLoginActionSuccess() {
		
		String actionName = "login";
        
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");
        
        
        View view = null;
		try {
			view = Struts.runAction(actionName,params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("发生异常");
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
			view = Struts.runAction(actionName,params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("发生异常");
			e.printStackTrace();
		}        
        
        Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
        Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
	}
}

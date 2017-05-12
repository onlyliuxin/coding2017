package week2.struts2;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class StrutsTest {

	@Test
	public void testLoginActionSuccess() {
		String actionName="login";
	    Map<String,String> params=new HashMap<>();
	   
	    params.put("userName", "沈健");
	    params.put("password", "123456");
	    
	    View view=Struts.runAction(actionName, params);
	    assertEquals("/jsp/homepage.jsp", view.getJsp());
	    assertEquals("login successful", view.getParameters().get("message"));
	}

	@Test
	public void testLoginActionFailed() {
		String actionName="login";
	    Map<String,String> params=new HashMap<>();
	   
	    params.put("name", "沈健");
	    params.put("password", "1234565");
	    
	    View view=Struts.runAction(actionName, params);
	    assertEquals("/jsp/showLogin.jsp", view.getJsp());
	    assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
	}
}

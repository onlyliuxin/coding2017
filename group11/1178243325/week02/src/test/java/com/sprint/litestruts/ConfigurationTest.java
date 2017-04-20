package com.sprint.litestruts;

import org.junit.Test;
import org.junit.Assert;
public class ConfigurationTest {
	Configuration cfg = new Configuration("struts.xml");

	@Test
	public void testGetClassName() {
		String clzName = cfg.getClassName("login");
		Assert.assertEquals("com.sprint.litestruts.LoginAction", clzName);
		clzName = cfg.getClassName("logout");
		Assert.assertEquals("com.sprint.litestruts.LoginAction", clzName);
	}

	@Test
	public void testGetResultView() {
		String jsp = cfg.getResultView("login", "success");	
		Assert.assertEquals("/jsp/homepage.jsp", jsp);
		
		jsp = cfg.getResultView("login", "fail");
		Assert.assertEquals("/jsp/showLogin.jsp", jsp);

		jsp = cfg.getResultView("logout", "success");
		Assert.assertEquals("/jsp/welcome.jsp", jsp);

		jsp = cfg.getResultView("logout", "error");
		Assert.assertEquals("/jsp/error.jsp", jsp);
	}

}

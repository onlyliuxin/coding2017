package com.coderising.litestruts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {
    Configureation cfg=new Configureation("struts.xml");
    @Before
    public void setUp() throws Exception{
    	
    }
    @After
    public void TearDown() throws Exception{
    	
    }
	@Test
	public void testGetClassName() {
		String clzName=cfg.getClassName("login");
		Assert.assertEquals("com.coderising.litestruts.LoginAction", clzName);
		clzName=cfg.getClassName("loginout");
		Assert.assertEquals("com.coderising.litestruts.LoginAction", clzName);
	}
	@Test
	public void tsetGetResult()
	{
		String jsp=cfg.getResultView("login", "success");
		Assert.assertEquals("/jsp/homepage.jsp",jsp);
		jsp=cfg.getResultView("login", "fail");
		Assert.assertEquals("/jsp/showLogin.jsp",jsp);
		jsp=cfg.getResultView("logout", "success");
		Assert.assertEquals("/jsp/welcome.jsp",jsp);
		jsp=cfg.getResultView("logout", "error");
		Assert.assertEquals("/jsp/error.jsp",jsp);
	}
	
	
	

}

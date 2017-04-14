package com.github.HarryHook.coding2017.litestruts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {

    Configuration cfg = new Configuration("struts.xml");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetClassName() {
	String clzName = cfg.getClassName("login");
	Assert.assertEquals("com.github.HarryHook.coding2017.litestruts.LoginAction", clzName);

	clzName = cfg.getClassName("logout");
	Assert.assertEquals("com.github.HarryHook.coding2017.litestruts.LogoutAction", clzName);
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

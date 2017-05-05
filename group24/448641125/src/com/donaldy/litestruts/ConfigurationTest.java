package com.donaldy.litestruts;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by donal on 2017/3/21.
 */
public class ConfigurationTest {

    Configuration cfg = new Configuration("struts.xml");

    @Test
    public void testGetClassName() {
        String clzName = cfg.getClassName("login");
        Assert.assertEquals("com.donaldy.litestruts.LoginAction", clzName);
        clzName = cfg.getClassName("logout");
        Assert.assertEquals("com.donaldy.litestruts.LogoutAction", clzName);
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

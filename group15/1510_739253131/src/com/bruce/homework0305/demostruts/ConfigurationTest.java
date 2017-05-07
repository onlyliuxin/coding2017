package com.bruce.homework0305.demostruts;

import org.junit.Assert;
import org.junit.Test;

public class ConfigurationTest {

    Configuration cfg = new  Configuration("struts.xml");
    @Test
    public void testGetClassName(){
        String login = cfg.getClassName("login");
        Assert.assertEquals("com.bruce.homework0305.demostruts.LoginAction",login);
        String logout = cfg.getClassName("logout");
        Assert.assertEquals("com.bruce.homework0305.demostruts.LoginAction",login);
    }

    @Test
    public void getResultView(){
        String resultView = cfg.getResultView("login", "success");
        Assert.assertEquals("/jsp/homepage.jsp",resultView);
        String resultView1 = cfg.getResultView("login", "fail");
        Assert.assertEquals("/jsp/showLogin.jsp",resultView1);
        String resultView2 = cfg.getResultView("logout", "success");
        Assert.assertEquals("/jsp/welcome.jsp",resultView2);
        String resultView3 = cfg.getResultView("logout", "error");
        Assert.assertEquals("/jsp/error.jsp",resultView3);
    }

}

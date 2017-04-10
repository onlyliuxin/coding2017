package com.coding.litestruts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



/**
 * Created by bobi on 2017/4/1.
 * at code2017
 */
public class ConfigurationTest {
    private Configuration config;
    @Before
    public void setUp() throws Exception {
        config = new Configuration("struts.xml");
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testGetClassName(){
        String clzName = config.getClassName("login");
        Assert.assertEquals("com.coding.litestruts.LoginAction", clzName);
        clzName = config.getClassName("logout");
        Assert.assertEquals("com.coding.litestruts.LogoutAction", clzName);

    }

    @Test
    public void testGetResultView(){
        String jsp = config.getResultView("login", "success");
        Assert.assertEquals("jsp/homepage.jsp", jsp);

        jsp = config.getResultView("login", "fail");
        Assert.assertEquals("jsp/showLogin.jsp", jsp);
    }

}
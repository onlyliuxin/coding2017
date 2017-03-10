package com.coderising.litestruts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Configuration Test
 */
public class ConfigurationTest {

    Configuration configuration = new Configuration("struts.xml");

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        configuration = null;
    }

    @Test
    public void testGetClassName() {
        String login = configuration.getClassName("login");
        assertEquals("com.coderising.litestruts.LoginAction", login);

        String logout = configuration.getClassName("logout");
        assertEquals("com.coderising.litestruts.LogoutAction", logout);

    }

    @Test
    public void testGetResultJsp() {

        String loginSuccess = configuration.getResultJsp("login", "success");
        assertEquals("/jsp/homepage.jsp", loginSuccess);

        String loginFail = configuration.getResultJsp("login", "fail");
        assertEquals("/jsp/showLogin.jsp", loginSuccess);

        String logoutSuccess = configuration.getResultJsp("logout", "success");
        assertEquals("/jsp/welcome.jsp", loginSuccess);
        String logoutFail = configuration.getResultJsp("logout", "fail");
        assertEquals("/jsp/error.jsp", loginSuccess);

    }
}
package com.coderising.litestruts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 *  Struts Test
 */
public class StrutsTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testLoginActionSuccess() {

        String actionName = "login";

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "1234");
        View view = Struts.runAction(actionName, params);

        assertEquals("/jsp/homepage.jsp", view.getJsp());
        assertEquals("login successful", view.getParameters().get("message"));
    }

    @Test
    public void testLoginActionFailed() {
        String actionName = "login";

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "123456"); //密码和预设的不一致
        View view = Struts.runAction(actionName, params);

        assertEquals("/jsp/showLogin.jsp", view.getJsp());
        assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
    }

}
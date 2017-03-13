package com.circle.struts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by keweiyang on 2017/3/5.
 */
public class StrutsTest {
    Map<String, String> map = null;
    Struts struts = null;
    String pathName = null;
    String actionName = null;



    @Test
    public void runAction() throws Exception {
        map = new HashMap<>();
        struts = new Struts();
        map.put("name", "test");
        map.put("password", "1234");
        actionName = "login";
        pathName = "src/main/resources/struts.xml";

        View view = struts.runAction(actionName, map, pathName);
        Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
        Assert.assertEquals("login successful", view.getParameters().get("message"));
    }

    @Test
    public void testLoginActionFailed() throws Exception {
        map = new HashMap<>();
        struts = new Struts();
        map.put("name", "test");
        map.put("password", "12345");
        actionName = "login";
        pathName = "src/main/resources/struts.xml";

        View view = struts.runAction(actionName, map, pathName);
        Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
        Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
    }

}
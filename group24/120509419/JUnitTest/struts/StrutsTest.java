/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.struts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class StrutsTest {

    @Test
    public void testLoginActionSuccess() throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

        String actionName = "login";

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "1234");

        View view = Struts.runAction(actionName, params);

        Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
        Assert.assertEquals("login successful", view.getParameters().get("message"));
    }

    @Test
    public void testLoginActionFailed() throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        String actionName = "login";
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "123456"); //密码和预设的不一致

        View view = Struts.runAction(actionName, params);

        Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
        Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
    }
}

package com.bruce.homework0305.mystruts;

import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class StrutsTest {

	@Test
	public void testLoginActionSuccess() {
        try {
            String actionName = "login";
            Map<String,String> params = new HashMap<String,String>();
            params.put("name","test");
            params.put("password","1234");
            View view  = Struts.runAction(actionName,params);
            Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
            Assert.assertEquals("login successful", view.getParameters().get("message"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

	@Test
	public void testLoginActionFailed() {
        try {
            String actionName = "login";
            Map<String,String> params = new HashMap<String,String>();
            params.put("name","test");
            params.put("password","123456"); //密码和预设的不一致
            View view  = Struts.runAction(actionName,params);
            Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
            Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

package io.github.vxzh.struts;


import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by vxzh on 28/02/2017.
 */
public class StrutsTest {

    @Test
    public void testLoginActionSuccess() {

        String actionName = "login";
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "1234");

        try {
            View view = Struts.runAction(actionName, params);
            Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
            Assert.assertEquals("login successful", view.getParameters().get("message"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginActionFailed() {
        String actionName = "login";
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "123456"); //密码和预设的不一致

        try {
            View view = Struts.runAction(actionName, params);
            Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
            Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

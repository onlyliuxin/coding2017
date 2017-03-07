package main.coding_170302;

import junit.framework.TestCase;
import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by peter on 2017/3/3.
 */
public class StrutsTest extends TestCase {
    @Test
    public void testLoginActionSuccess() throws DocumentException {
        String actionName = "login";

        Map<String,String> params = new HashMap<>();
        params.put("name","test");
        params.put("password","1234");


        View view  = Struts.runAction(actionName,params);

        Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
        Assert.assertEquals("login successful", view.getParameters().get("message"));
    }
    @Test
    public void testLoginActionFailed() throws DocumentException {
        String actionName = "login";
        Map<String,String> params = new HashMap<>();
        params.put("name","test");
        params.put("password","123456"); //密码和预设的不一致

        View view  = Struts.runAction(actionName,params);

        Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
        Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
    }

}
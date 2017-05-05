package rui.study.coding2017.coderising.liteststruts;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 测试struts功能
 * Created by 赵睿 on 2017/3/4.
 */

public class StrutsTest {

    @Test
    public void testLoginActionSuccess() {

        String actionName = "login";

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "1234");


        View view = Struts.runAction(actionName, params);

        Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
        Assert.assertEquals("login successful", view.getParameters().get("message"));
    }

    @Test
    public void testLoginActionFailed() {
        String actionName = "login";
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "123456"); //密码和预设的不一致

        View view = Struts.runAction(actionName, params);

        Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
        Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
    }

}
package coderising.litestruts;

import com.coderising.litestruts.Struts;
import com.coderising.litestruts.View;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class StrutsTest {

    private Struts struts;

    @Before
    public void setUp() {
        struts = new Struts();
    }

    @Test
    public void testLoginActionSuccess() {
        String actionName = "login";
        Map<String, String> params = new HashMap<>();
        params.put("name", "test");
        params.put("password", "1234");
        View view = struts.runAction(actionName, params);
        Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
        Assert.assertEquals("login successful", view.getParameters().get("message"));
    }

    @Test
    public void testLoginActionFailed() {
        String actionName = "login";
        Map<String, String> params = new HashMap<>();
        params.put("name", "test");
        // 密码和预设的不一致
        params.put("password", "123456");
        View view = struts.runAction(actionName, params);
        Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
        Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
    }

}

package study.coderising.litestruts;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;

public class StrutsTest {

    @Test
    public void testLoginActionSuccess() {

        String actionName = "login";

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "1234");

        View view = null;
        try {
            view = Struts.runAction(actionName, params);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
        Assert.assertEquals("login successful", view.getParameters().get("message"));
    }

    @Test
    public void testLoginActionFailed() {
        String actionName = "login";
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "123456"); //密码和预设的不一致

        View view = null;
        try {
            view = Struts.runAction(actionName, params);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
        Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
    }

    @Test
    public void testLogoutSuccess() {
        String actionName = "logout";

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");

        View view = null;
        try {
            view = Struts.runAction(actionName, params);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals("/jsp/welcome.jsp", view.getJsp());
        Assert.assertEquals("test logout success", view.getParameters().get("message"));
    }

    @Test
    public void testLogoutFail() {
        String actionName = "logout";

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "unknownUser");

        View view = null;
        try {
            view = Struts.runAction(actionName, params);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals("/jsp/error.jsp", view.getJsp());
        Assert.assertEquals("unknownUser logout fail", view.getParameters().get("message"));
    }
}

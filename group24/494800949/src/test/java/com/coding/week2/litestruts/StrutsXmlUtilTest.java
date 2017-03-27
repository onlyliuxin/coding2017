package com.coding.week2.litestruts;

import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/19 0019.
 */
public class StrutsXmlUtilTest {

    @Test
    public void testGetClassNameOfAction() throws Exception {
        String path = "src\\main\\java\\com\\coding\\week2\\litestruts\\struts.xml";

        String actionCLass = StrutsXmlUtil.getActionClassName( "login");
        Assert.assertEquals(actionCLass, "com.coderising.litestruts.LoginAction");
        actionCLass = StrutsXmlUtil.getActionClassName("logout");
        Assert.assertEquals(actionCLass, "com.coderising.litestruts.LogoutAction");

    }


    @Test
    public void testGetResultOfAction() throws DocumentException {
        String path = "src\\main\\java\\com\\coding\\week2\\litestruts\\struts.xml";
        Map<String,String> res = StrutsXmlUtil.getResultOfAction("login");
        Assert.assertNotNull(res.get("success"), "/jsp/homepage.jsp");
        Assert.assertNotNull(res.get("fail"), "/jsp/showLogin.jsp");
    }


}
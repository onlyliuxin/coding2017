package week2_0305.litestruts;

import week2_0305.litestruts.Configuration;

import org.junit.Assert;
import org.junit.Test;

public class ConfigurationTest {

	Configuration cfg = new Configuration("struts.xml");

	@Test
	public void testGetClassName() {
		String clzName = cfg.getClassName("login");
		Assert.assertEquals("week2_0305.litestruts.LoginAction", clzName);
		clzName = cfg.getClassName("logout");
		Assert.assertEquals("week2_0305.litestruts.LogoutAction", clzName);
	}
	@Test
	public void testGetFileName() {
		String packageName = this.getClass().getPackage().getName();
		packageName = packageName.replace('.', '/');
		packageName = "/" + packageName + "/" + "struts.xml";
		Assert.assertEquals("/week2_0305/litestruts/struts.xml", packageName);
	}
	
	@Test
	public void testGetResultView() {
		String actionName = "login";
		String resultName = "success";
		String viewName = cfg.getResultView(actionName, resultName);
		Assert.assertEquals("/jsp/homepage.jsp", viewName);
		
		actionName = "login";
		resultName = "fail";
		viewName = cfg.getResultView(actionName, resultName);
		Assert.assertEquals("/jsp/showLogin.jsp", viewName);
		
		actionName = "logout";
		resultName = "success";
		viewName = cfg.getResultView(actionName, resultName);
		Assert.assertEquals("/jsp/welcome.jsp", viewName);
		
		actionName = "logout";
		resultName = "error";
		viewName = cfg.getResultView(actionName, resultName);
		Assert.assertEquals("/jsp/error.jsp", viewName);
		
	}

}

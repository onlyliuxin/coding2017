package test;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;

import main.Struts;
import main.View;

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
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
		Assert.assertEquals("login successful", view.getParameters().get("message"));
	}

	@Test
	public void testLoginActionFailed() {
		String actionName = "login";
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "tes");
		params.put("password", "1234");
		View view = null;
		try {
			view = Struts.runAction(actionName, params);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
		Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
	}

}

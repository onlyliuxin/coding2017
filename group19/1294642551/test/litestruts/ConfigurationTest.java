package litestruts;

import org.junit.Assert;
import org.junit.Test;

/**
 * 配置类Configuration的测试类
 * @author 12946
 *
 */

public class ConfigurationTest {
	
	private Configuration cfg = new Configuration("struts.xml");

	@Test
	public void testGetClassName(){
		String clzName = cfg.getClassName("login");
		Assert.assertEquals("litestruts.LoginAction", clzName);
		
		clzName = cfg.getClassName("logout");
		Assert.assertEquals("litestruts.LogoutAction", clzName);
	}
	
	@Test
	public void testGetResultView(){
		String jpg = cfg.getResultView("login","success");
		Assert.assertEquals("/jsp/homepage.jsp", jpg);
		
		jpg = cfg.getResultView("login","fail");
		Assert.assertEquals("/jsp/showLogin.jsp", jpg);
		
		jpg = cfg.getResultView("logout","success");
		Assert.assertEquals("/jsp/welcome.jsp", jpg);
		
		jpg = cfg.getResultView("logout","error");
		Assert.assertEquals("/jsp/error.jsp", jpg);
	}
}

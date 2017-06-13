package litestruts;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetGetterMethods() {
		Configuration cfg = Configuration.getNewInstance();
		Map<String,String> actionName = new HashMap<>();
		actionName.put("login","com.coderising.action.LoginAction");
		actionName.put("logout","com.coderising.action.LogoutAction");
		
		//Assert.assertTrue(cfg.getActionName().containsKey(actionName));
		
		
		
	}

}

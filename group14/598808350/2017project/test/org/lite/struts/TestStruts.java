package org.lite.struts;

import java.util.HashMap;

import org.junit.Assert;

import junit.framework.TestCase;

public class TestStruts extends TestCase{

	private Struts struts = null;
	
	@Override
	public void setUp(){
		struts = new Struts();
	}
	public void testRunAction(){
		String actionName = "login";
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("name", "test");
		params.put("pwd", "1234");
		View view = struts.runAction(actionName, params);
		String jsp = view.getJsp();
		String exp1 = "/jsp/homepage.jsp";
		Assert.assertEquals("��¼�ɹ�", exp1, jsp);
		
		params = new HashMap<String,String>();
		params = new HashMap<String,String>();
		params.put("name", "test");
		params.put("pwd", "12341");
		view = struts.runAction(actionName, params);
		jsp = view.getJsp();
		exp1 = "/jsp/showLogin.jsp";
		Assert.assertEquals("������󣬵�¼ʧ��", exp1, jsp);
		
		
	}
}

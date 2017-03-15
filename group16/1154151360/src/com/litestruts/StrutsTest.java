package com.litestruts;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class StrutsTest {

	@Test
	public void testLoginActionSuccess() throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		String actionName = "login";
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("name","test");
		params.put("password","1234");
	
		View view  = Struts.runAction(actionName,params);        
		
		Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
		Assert.assertEquals("login successful", view.getParameters().get("message"));
	}

	@Test
	public void testLoginActionFailed() throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		String actionName = "login";
		Map<String,String> params = new HashMap<String,String>();
		params.put("name","test");
		params.put("password","123456"); //密码和预设的不一致
		
		View view  = Struts.runAction(actionName,params);        
		
		Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
		Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
	}

}

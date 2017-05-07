package week2.struts2.test;

import static org.junit.Assert.*;

import org.junit.Test;

import week2.struts2.Configuration;

public class ConfigurationTest {

	Configuration config=new Configuration("struts.xml");
	
	@Test
	public void testGetClassName(){
		
		String clazzName=config.getClassName("login");
		assertEquals("week2.struts2.LoginAction",clazzName);
		
		clazzName=config.getClassName("logout");
		assertEquals("week2.struts2.LoginOutAction",clazzName);
		
		clazzName=config.getClassName("logoutf");
	}
	
	@Test
	public void testGetResultView(){
		
		String jsp=config.getResultView("login","success");
		assertEquals("/jsp/homepage.jsp",jsp);
		
		jsp=config.getResultView("login", "fail");
		assertEquals("/jsp/showLogin.jsp",jsp);
		
		jsp=config.getResultView("logout", "success");
		assertEquals("/jsp/welcome.jsp",jsp);
		
		jsp=config.getResultView("logout", "error");
		assertEquals("/jsp/error.jsp",jsp);
	}
}

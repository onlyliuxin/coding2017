package litestruts;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ReflectionUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetSetterMethod() throws ClassNotFoundException{
		String className = "litestruts.LoginAction";
		Class<?> clazz = Class.forName(className);
		List<Method> methods = ReflectionUtil.getSetterMethod(clazz);
		
		Assert.assertEquals(2, methods.size());
		//----------
		List<String> expectedNames = new ArrayList<String>();
		expectedNames.add("setName");
		expectedNames.add("setPassword");
		
		Set<String> actualNames = new HashSet<String>();
		for(Method method : methods){
			actualNames.add(method.getName());
		}
		Assert.assertTrue(actualNames.containsAll(expectedNames));
			
	}
	
	@Test
	public void testSetParameters() throws Exception{
		String className = "litestruts.LoginAction";
		Class<?> clazz = Class.forName(className);
		LoginAction action = (LoginAction) clazz.newInstance();
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("name", "test");
		parameters.put("password", "1234");
		
		ReflectionUtil.setParameters(action, parameters);
		
		Field f = clazz.getDeclaredField("name");
		f.setAccessible(true);
		Assert.assertEquals("test", f.get(action));
		
		f = clazz.getDeclaredField("password");
		f.setAccessible(true);
		Assert.assertEquals("1234", f.get(action));
	}
	
	@Test
	public void testGetGetterMethod() throws ClassNotFoundException{
		String className = "litestruts.LoginAction";
		Class<?> clazz = Class.forName(className);
		List<Method> methods = ReflectionUtil.getGetterMethod(clazz);
		
		Assert.assertEquals(3, methods.size());
		//----------
		List<String> expectedNames = new ArrayList<String>();
		expectedNames.add("getName");
		expectedNames.add("getPassword");
		expectedNames.add("getMessage");
		
		Set<String> actualNames = new HashSet<String>();
		for(Method method : methods){
			actualNames.add(method.getName());
		}
		Assert.assertTrue(actualNames.containsAll(expectedNames));
		
	}
	
	@Test
	public void testGetParameters() throws Exception {
		String className = "litestruts.LoginAction";
		Class<?> clazz = Class.forName(className);
		LoginAction action = (LoginAction) clazz.newInstance();
		action.setName("test");
		action.setPassword("123456");
		Map<String, Object> parameterMap = ReflectionUtil.getParameters(action);
		
		Assert.assertEquals(3, parameterMap.size());
		
		
		Assert.assertEquals(null, parameterMap.get("messaage") );
		Assert.assertEquals("test", parameterMap.get("name") );
		Assert.assertEquals("123456", parameterMap.get("password") );
		
	}


}

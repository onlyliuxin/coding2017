package week2_0305.litestruts;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class ReflectionUtilTest {

	@Test
	public void testGetSetterMethod() throws Exception{
		String name = "week2_0305.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		List<Method> methods = ReflectionUtil.getSetterMethods(clz);
		
		Assert.assertEquals(2, methods.size());
		
		List<String> expectNames = new ArrayList<>();
		expectNames.add("setName");
		expectNames.add("setPassword");
		
		Set<String> actualNames = new HashSet<>();
		for(Method m : methods) {
			actualNames.add(m.getName());
		}
		Assert.assertTrue(actualNames.containsAll(expectNames));
	}
	
	@Test
	public void testSetParameters() throws Exception{
		String name = "week2_0305.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		Object o = clz.newInstance();
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", "test");
		params.put("password", "123456");
		
		ReflectionUtil.setParameters(o, params);
		
		Field f = clz.getDeclaredField("name");
		f.setAccessible(true);
		Assert.assertEquals("test", f.get(o));
		
		f = clz.getDeclaredField("password");
		f.setAccessible(true);
		Assert.assertEquals("123456", f.get(o));
	}
	
	@Test
	public void testGetGetterMethod() throws Exception{
		String name = "week2_0305.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		List<Method> methods = ReflectionUtil.getGetterMethods(clz);
		
		Assert.assertEquals(3, methods.size());
		List<String> expectNames = new ArrayList<>();
		expectNames.add("getName");
		expectNames.add("getPassword");
		expectNames.add("getMessage");
		
		Set<String> actualNames = new HashSet<>();
		
		for(Method m : methods) {
			actualNames.add(m.getName());
		}
		Assert.assertTrue(actualNames.containsAll(expectNames));
		
	}
	
	@Test
	public void testGetMethods() throws Exception{
		String name = "week2_0305.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		List<Method> methods = ReflectionUtil.getMethods(clz, "get");
		
		Assert.assertEquals(3, methods.size());
		List<String> expectNames = new ArrayList<>();
		expectNames.add("getName");
		expectNames.add("getPassword");
		expectNames.add("getMessage");
		
		Set<String> actualNames = new HashSet<>();
		
		for(Method m : methods) {
			actualNames.add(m.getName());
		}
		Assert.assertTrue(actualNames.containsAll(expectNames));
		
		methods = ReflectionUtil.getMethods(clz, "set");
		
		Assert.assertEquals(2, methods.size());
		
		expectNames = new ArrayList<>();
		expectNames.add("setName");
		expectNames.add("setPassword");
		
		actualNames = new HashSet<>();
		for(Method m : methods) {
			actualNames.add(m.getName());
		}
		Assert.assertTrue(actualNames.containsAll(expectNames));
	}
	
	@Test
	public void testGetParameters() throws Exception{
		String name = "week2_0305.litestruts.LoginAction";
		Class<?> clz = Class.forName(name);
		LoginAction action = (LoginAction)clz.newInstance();
		action.setName("test");
		action.setPassword("123456");
		
		Map<String, Object> params = ReflectionUtil.getParamterMap(action);
		
		Assert.assertEquals(3, params.size());
		
		Assert.assertEquals(null, params.get("message"));
		Assert.assertEquals("test", params.get("name"));
		Assert.assertEquals("123456", params.get("password"));
		
	}

}

package week01.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week01.basic.MyStack;

public class MyStackTest {
	private MyStack stack = null;
	
	@Before
	public void setUp() throws Exception {
		stack = new MyStack();
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
	}

	@After
	public void tearDown() throws Exception {
		stack = null;
	}
	
	@Test
	public void tearPush() throws Exception {
		stack.push(10);
		Assert.assertEquals((Object) new Integer(4), stack.size());
		Assert.assertEquals((Object) new Integer(10), stack.peek());
	}

	@Test
	public void testPop(){
		Assert.assertEquals((Object) new Integer(3), stack.pop());
		Assert.assertEquals((Object) new Integer(2), stack.size());
	}
	
	@Test
	public void testPeek(){
		Assert.assertEquals((Object) new Integer(3), stack.peek());
	}
	
	@Test
	public void testIsEmpty(){
		Assert.assertFalse(stack.isEmpty());
	}
	
	@Test
	public void testSize(){
		Assert.assertEquals((Object) new Integer(3), stack.size());
	}
}

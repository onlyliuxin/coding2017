package test_data_structure;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import data_structure.MyStack;

public class TestMyStack {
	MyStack mystack;

	@Before
	public void setUp() throws Exception {
		mystack=new MyStack();
		System.out.println("开始测试");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("结束测试");
	}

	@Test
	public void testPush() {
		mystack.push("Hello");
		mystack.push(",");
		mystack.push("World");
	}

	@Test
	public void testPop() {
		mystack.push("Hello");
		mystack.push(",");
		mystack.push("World");
		Assert.assertEquals("World", (String)mystack.pop());
		Assert.assertEquals(",", (String)mystack.pop());
	
	}

	@Test
	public void testPeek() {
		mystack.push("Hello");
		Assert.assertEquals("Hello", (String)mystack.peek());
	}

	@Test
	public void testIsEmpty() {
		
		Assert.assertEquals(true, mystack.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(0, mystack.size());
	}

}

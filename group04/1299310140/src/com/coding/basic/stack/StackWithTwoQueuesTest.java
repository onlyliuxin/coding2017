package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackWithTwoQueuesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		StackWithTwoQueues stack = new StackWithTwoQueues();
		Assert.assertTrue(stack.isEmpty());
		Assert.assertEquals(0,stack.size());
		
		stack.push(4);
		Assert.assertFalse(stack.isEmpty());
		Assert.assertEquals(1,stack.size());
		
		Assert.assertEquals(4,stack.pop());
		Assert.assertTrue(stack.isEmpty());
		
		stack.push(2);
		stack.push(3);
		stack.push(5);
		Assert.assertFalse(stack.isEmpty());
		Assert.assertEquals(3,stack.size());
		
		Assert.assertEquals(5,stack.pop());
		
		stack.push(6);
		stack.push(7);
		stack.push(8);
		Assert.assertEquals(8,stack.pop());
		Assert.assertEquals(7,stack.pop());
		Assert.assertEquals(6,stack.pop());
		Assert.assertEquals(3,stack.pop());
		Assert.assertEquals(2,stack.pop());
		Assert.assertTrue(stack.isEmpty());
	}

}

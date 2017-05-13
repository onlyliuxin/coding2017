package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuickMinStackTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		QuickMinStack stack = new QuickMinStack();
		Assert.assertTrue(stack.isEmpty());
		Assert.assertEquals(0,stack.size());
		
		stack.push(3);
		Assert.assertFalse(stack.isEmpty());
		Assert.assertEquals(1,stack.size());
		Assert.assertEquals(3,stack.peek());
		Assert.assertEquals(3,stack.findMin());
		Assert.assertEquals(3,stack.findMax());
		Assert.assertEquals(3,stack.pop());
		Assert.assertTrue(stack.isEmpty());
		Assert.assertEquals(0,stack.size());
		
		stack.push(5);
		stack.push(6);
		stack.push(3);
		stack.push(7);
		stack.push(1);
		Assert.assertEquals(1,stack.findMin());
		Assert.assertEquals(7,stack.findMax());
		
		stack.pop();
		Assert.assertEquals(3,stack.findMin());
		Assert.assertEquals(7,stack.findMax());
		
		stack.pop();
		Assert.assertEquals(3,stack.findMin());
		Assert.assertEquals(6,stack.findMax());
	}

}

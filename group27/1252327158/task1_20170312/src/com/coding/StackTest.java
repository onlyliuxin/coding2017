package com.coding;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class StackTest {
	
	Stack<String> stack;

	@Before
	public void setUp() throws Exception {
		stack = new Stack<String>();
		stack.push("first");
		stack.push("second");
	}

	@Test
	public void testPush() {
		stack.push("third");
		Assert.assertEquals("third", stack.peek());
	}

	@Test
	public void testPop() {
		Assert.assertEquals("second", stack.pop());
	}

	@Test
	public void testPeek() {
		Assert.assertEquals("second", stack.peek());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertEquals(false, stack.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(2, stack.size());
	}
}

package com.coding.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.Stack;

public class StackTest {
	
	private static Stack stack;

	@Before
	public void setUp() throws Exception {
		stack = new Stack();
		stack.push("111");
		stack.push("222");
		stack.push("333");
	}

	@After
	public void tearDown() throws Exception {
		stack = null;
	}

	@Test
	public void testPush() {
		stack.push("444");
		assertEquals(4, stack.size());
		assertEquals("444", stack.pop());
	}

	@Test
	public void testPop() {
		Object obj = stack.pop();
		assertEquals("333", obj);
		assertEquals(2, stack.size());
	}

	@Test
	public void testPeek() {
		Object obj = stack.peek();
		assertEquals("333", obj);
		assertEquals(3, stack.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false, stack.isEmpty());
		stack = new Stack();
		assertEquals(true, stack.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(3,stack.size());
	}

}

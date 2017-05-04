package com.zhaogd.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zhaogd.collection.stack.Stack;

public class StackTest {

	private Stack stack;

	@Before
	public void setUp() throws Exception {
		stack = new Stack();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPush() {
		stack.push(11);
		assertEquals(11, stack.pop());
		assertTrue(stack.isEmpty());
	}

	@Test
	public void testPop() {
		stack.push(11);
		assertEquals(11, stack.pop());
		assertTrue(stack.isEmpty());
	}

	@Test
	public void testPeek() {
		stack.push(11);
		assertEquals(11, stack.peek());
		assertFalse(stack.isEmpty());
		assertEquals(1, stack.size());
	}

}

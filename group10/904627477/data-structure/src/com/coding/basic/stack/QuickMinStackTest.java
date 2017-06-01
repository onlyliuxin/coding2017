package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuickMinStackTest {
	
	private QuickMinStack stack;

	@Before
	public void setUp() throws Exception {
		stack = new QuickMinStack();
	}

	@After
	public void tearDown() throws Exception {
		stack = null;
	}

	@Test
	public void testPush() {
		stack.push(1);
		stack.push(4);
		stack.push(2);
		assertEquals("1,4,2", stack.toString());
	}

	@Test
	public void testPop() {
		stack.push(1);
		stack.push(4);
		assertEquals(4, stack.pop());
		stack.push(2);
		assertEquals("1,2", stack.toString());
	}

	@Test
	public void testFindMin() {
		stack.push(5);
		assertEquals(5, stack.findMin());
		stack.push(3);
		assertEquals(3, stack.findMin());
		stack.push(4);
		assertEquals(3, stack.findMin());
		stack.push(2);
		assertEquals(2, stack.findMin());
		stack.pop();
		assertEquals(3, stack.findMin());
		stack.pop();
		assertEquals(3, stack.findMin());
		stack.pop();
		assertEquals(5, stack.findMin());
		stack.push(1);
		assertEquals(1, stack.findMin());
	}

}

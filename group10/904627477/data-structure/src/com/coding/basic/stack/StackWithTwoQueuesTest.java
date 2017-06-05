package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackWithTwoQueuesTest {
	
	private StackWithTwoQueues stack;

	@Before
	public void setUp() throws Exception {
		stack = new StackWithTwoQueues();
	}

	@After
	public void tearDown() throws Exception {
		stack = null;
	}
	
	@Test
	public void testSize(){
		assertEquals(0, stack.size());
		stack.push(1);
		stack.push(2);
		assertEquals(2, stack.size());
	}

	@Test
	public void testPush() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		assertEquals(3, stack.size());
		assertEquals("3,2,1", stack.toString());
	}

	@Test
	public void testPop() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		assertEquals(3, stack.pop());
		stack.push(4);
		assertEquals("4,2,1", stack.toString());
	}
	
	@Test
	public void testEmpty() {
		assertEquals(true, stack.isEmpty());
		stack.push(1);
		assertEquals(false, stack.isEmpty());
	}

}

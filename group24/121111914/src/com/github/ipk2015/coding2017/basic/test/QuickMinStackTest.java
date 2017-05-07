package com.github.ipk2015.coding2017.basic.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.ipk2015.coding2017.basic.stack.QuickMinStack;

public class QuickMinStackTest {
	QuickMinStack stack;
	@Before
	public void setUp() throws Exception {
		stack = new QuickMinStack();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPush() {
		stack.push(11);
		assertEquals(11, stack.peek());
		stack.push(22);
		assertEquals(22, stack.peek());
	}

	@Test
	public void testPop() {
		stack.push(11);
		stack.push(22);
		stack.push(33);
		assertEquals(33, stack.pop());
		assertEquals(22, stack.pop());
		assertEquals(11, stack.pop());
	}

	@Test
	public void testFindMin() {
		
		stack.push(4);
		stack.push(2);
		stack.push(3);
		stack.push(5);
		assertEquals(2, stack.findMin());
		stack.pop();
		assertEquals(2, stack.findMin());
		stack.pop();
		assertEquals(2, stack.findMin());
		stack.pop();
		assertEquals(4, stack.findMin());
	}

}

package com.coding.test;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

import com.coding.basic.Stack;

//
public class StackTest {

	@Test
	public void testPush() {
		Stack stack = new Stack();
		assertEquals(0, stack.size());
		stack.push(new Object());
		assertEquals(1, stack.size());
	}

	@Test
	public void testPop() {
		Stack stack = new Stack();
		stack.push(new Object());
		assertNotNull(stack.pop());
		assertEquals(0, stack.size());
	}

	@Test
	public void testPeek() {
		Stack stack = new Stack();
		int tmp = 0;
		try {
			stack.peek();
		} catch (EmptyStackException e) {
			tmp = 1;
			assertEquals(1, tmp);
		}
		stack.push(new Object());
		assertNotNull(stack.peek());
		assertEquals(1, stack.size());
	}

	@Test
	public void testIsEmpty() {
		Stack stack = new Stack();
		assertTrue(stack.isEmpty());
		stack.push(new Object());
		assertFalse(stack.isEmpty());
	}

	@Test
	public void testSize() {
		Stack stack = new Stack();
		assertEquals(0, stack.size());
	}
}

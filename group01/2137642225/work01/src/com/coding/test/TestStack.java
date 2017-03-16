package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.mybasic.Stack;

public class TestStack {

	private Stack stack;
	@Before
	public void before() {
		stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
	}
	
	@Test
	public void testPush() {
		assertEquals(3, stack.peek());
	}

	@Test
	public void testPop() {
		assertEquals(3, stack.pop());
		assertEquals(2, stack.pop());
		assertEquals(1, stack.pop());
		//stack.pop();
		//System.out.println(stack.size());
	}

	@Test
	public void testPeek() {
		assertEquals(3, stack.peek());
		assertEquals(3, stack.pop());
		assertEquals(2, stack.pop());
		//assertEquals(1, stack.pop());
		assertEquals(1, stack.peek());
	}

	@Test
	public void testSize() {
		assertEquals(3, stack.size());
	}

}

package com.coding.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Stack;

public class StackTest {
	
	private Stack stack;

	@Before
	public void setUp() throws Exception {
		stack = new Stack();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("size:" + stack.size());
		System.out.println(stack);
	}

	@Test
	public void testPush() {
		stack.push(9);
		stack.push(8);
		stack.push(7);
		stack.push(6);
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		stack.push(1);
		stack.push(0);
		stack.push(9);
	}

	@Test
	public void testPop() {
		stack.push(9);
		stack.push(8);
		stack.push(7);
		stack.push(6);
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		stack.push(1);
		stack.push(0);
		System.out.println(stack.pop());
	}

	@Test
	public void testPeek() {
		stack.push(9);
		stack.push(8);
		stack.push(7);
		stack.push(6);
		stack.push(5);
		System.out.println(stack.peek());
	}

	@Test
	public void testIsEmpty() {
		stack.push(5);
		System.out.println(stack.isEmpty());
	}

	@Test
	public void testSize() {
		stack.push(5);
		System.out.println(stack.size());
	}

}

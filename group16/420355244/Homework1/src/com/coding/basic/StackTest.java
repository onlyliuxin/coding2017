package com.coding.basic;

import org.junit.Before;
import org.junit.Test;

public class StackTest {
	private static Stack stack = new Stack();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPush() {
		stack.push("aaa");
		stack.push("bbb");
		stack.push("ccc");
		System.out.println(stack);
	}

	@Test
	public void testPop() {
		stack.push("aaa");
		stack.push("bbb");
		stack.push("ccc");
		Object pop = stack.pop();
		System.out.println(pop);
		System.out.println(stack);
	}

	@Test
	public void testPeek() {
		stack.push("aaa");
		stack.push("bbb");
		stack.push("ccc");
		Object peek = stack.peek();
		System.out.println(peek);
	}

	@Test
	public void testIsEmpty() {
		System.out.println(stack.isEmpty());
		stack.push("aaa");
		stack.push("bbb");
		stack.push("ccc");
		System.out.println(stack.isEmpty());
		stack.pop();
		stack.pop();
		stack.pop();
		System.out.println(stack.isEmpty());
	}

	@Test
	public void testSize() {
		stack.push("aaa");
		stack.push("bbb");
		stack.push("ccc");
		stack.pop();
		stack.pop();
		System.out.println(stack.size());
	}

}

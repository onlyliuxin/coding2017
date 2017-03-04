package com.coding.week1;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStack {

	@Test
	public void test() {
		Stack stack = new Stack();
		stack.push("AA");
		stack.push("BB");
		stack.push("CC");
		stack.push("DD");
		assertEquals(stack.pop(), "DD");
		assertEquals(stack.pop(), "CC");
		assertEquals(stack.peek(), "BB");
		assertEquals(stack.size(), 2);
		assertEquals(stack.isEmpty(),false);
	}

}

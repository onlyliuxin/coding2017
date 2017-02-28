package com.coding.basic;

import org.junit.Test;

public class StackTest {

	@Test
	public void testStack() {
		Stack s = new Stack();
		s.push("0");
		s.push("1");
		s.push("2");
		s.push("3");
		s.push("4");
		System.out.println(s.pop());
		System.out.println(s.peek());
		
	}
}

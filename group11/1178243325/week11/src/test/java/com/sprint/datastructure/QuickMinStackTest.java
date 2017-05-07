package com.sprint.datastructure;

import org.junit.Test;
public class QuickMinStackTest {
	
	
	@Test
	public void test() {
		QuickMinStack stack = new QuickMinStack();
		stack.push(8);
		stack.push(9);
		stack.push(7);
		stack.push(2);
		stack.pop();

		System.out.println(stack.findMin());
	}
}

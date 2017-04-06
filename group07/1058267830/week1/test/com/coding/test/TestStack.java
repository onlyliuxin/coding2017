package com.coding.test;

import org.junit.Test;

import com.coding.basic.Stack;

public class TestStack {

	@Test
	public void test() {
		Stack stack = new Stack();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.push("d");
		System.out.println(stack.peek());
		System.out.println(stack.size());
		System.out.println("-------------");
		
		stack.pop();
		System.out.println(stack.peek());
		System.out.println(stack.size());
		System.out.println("-------------");
		
		stack.pop();
		System.out.println(stack.peek());
		System.out.println(stack.size());
		System.out.println("-------------");
	}

}

package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.Stack;

public class StackTest {
	Stack stack = new Stack();

	@Test
	public void testPush() {
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
	}

	@Test
	public void testPop() {
		testPush();
		for (int i = 0; i < 3; i++) {
			System.out.println("pop : "+stack.pop());			
		}
	}

	@Test
	public void testPeek() {
		testPush();
		for (int i = 0; i < 3; i++) {
			System.out.println("peek : "+stack.peek());		
		}
	}

	@Test
	public void testIsEmpty() {
		System.out.println("is empty(true) : "+stack.isEmpty());
		testPush();
		System.out.println("is empty(false) : "+stack.isEmpty());
	}

	@Test
	public void testSize() {
		testPush();
		System.out.println("size : "+stack.size());
	}

}

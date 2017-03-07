package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.first.impl.Stack;

public class StackTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testPush() {
		Stack stack = new Stack();
		stack.push("1");
		stack.push("2");
		stack.push("8");
		stack.push("3");
		stack.push("4");
		stack.push("5");

		for(int i=stack.size();i>0;i=stack.size()){
			if(!stack.isEmpty()){
				System.out.print("i:");
				System.out.println(stack.pop());
			}
		}
		assertEquals(0,stack.size());
	}

	@Test
	public void testPop() {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(8);
		stack.push(3);
		stack.push(4);
		stack.push(5);

		for(int i=stack.size();i>0;i=stack.size()){
			if(!stack.isEmpty()){
				System.out.print("i:");
				System.out.println(stack.pop());
			}
		}
		assertEquals(0,stack.size());
	}

	@Test
	public void testPeek() {
		
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(8);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		assertEquals(5,stack.peek());
	}

	@Test
	public void testIsEmpty() {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(8);
		stack.push(3);
		stack.push(4);
		stack.push(5);

		for(int i=stack.size();i>0;i=stack.size()){
			if(!stack.isEmpty()){
				System.out.print("i:");
				System.out.println(stack.pop());
			}
		}
		assertEquals(0,stack.size());
	}

	@Test
	public void testSize() {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(8);
		stack.push(3);
		stack.push(4);
		stack.push(5);

		for(int i=stack.size();i>0;i=stack.size()){
			if(!stack.isEmpty()){
				System.out.print("i:");
				System.out.println(stack.pop());
			}
		}
		assertEquals(0,stack.size());
	}

}

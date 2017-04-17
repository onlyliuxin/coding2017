package com.github.ipk2015.coding2017.basic.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.ipk2015.coding2017.basic.stack.Stack;

public class StackTest {
	Stack stack;
	@Before
	public void setUp() throws Exception {
		stack=new Stack();
	}

	@Test
	public void testPush() {
		stack.push("hehe1");
		stack.push("hehe2");
		assertEquals(2,stack.size());
	}

	@Test
	public void testPop() {
		stack.push("hehe1");
		stack.push("hehe2");
		stack.push("hehe3");
		assertEquals(true,stack.pop()=="hehe3" && stack.size()==2);
	}

	@Test
	public void testPeek() {
		stack.push("hehe1");
		stack.push("hehe2");
		stack.push("hehe3");
		assertEquals(true,stack.peek()=="hehe3" && stack.size()==3);
	}

	@Test
	public void testIsEmpty() {
		stack.push("hehe1");
		stack.push("hehe2");
		stack.pop();
		stack.pop();
		assertEquals(true,stack.isEmpty());
	}

	@Test
	public void testSize() {
		stack.push("hehe1");
		stack.push("hehe2");
		stack.pop();
		assertEquals(1,stack.size());
	}

}

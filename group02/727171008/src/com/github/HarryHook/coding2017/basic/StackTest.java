package com.github.HarryHook.coding2017.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.HarryHook.coding2017.basic.MyStack;

public class StackTest {

	private MyStack stack;
	
	@Before
	public void setUpStack() {
		stack = new MyStack();
	}

	@Test
	public void testStackFunctional() {
		assertEquals(true, stack.isEmpty());
		stack.push(4);
		stack.push(2);
		assertEquals(2, stack.size());
		assertEquals(false, stack.isEmpty());
		
		int i = (Integer)stack.pop();
		assertEquals(2, i);
		
		i = (Integer)stack.peek();
		assertEquals(4, i);
		
		i = (Integer)stack.pop();
		assertEquals(4, i);
		
		assertEquals(0, stack.size());
		assertEquals(true, stack.isEmpty());
	}

}

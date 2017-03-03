package com.nitasty.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.nitasty.util.LinkedList;
import com.nitasty.util.Stack;

public class StackTest {
	
	Stack stack;
	
	@Before
	public void init(){
		stack=new Stack();
		for (int i = 0; i < 100; i++) {
			stack.push(i);
		}
	}

	@Test
	public void testPop() {
		for (int i = 99; i >=0; i--) {
			Assert.assertEquals(i, stack.pop());
		}
	}

	@Test
	public void testPeek() {
		for (int i = 99; i >=0; i--) {
			Assert.assertEquals(99, stack.peek());
		}
	}

	@Test
	public void testIsEmpty() {
		for (int i = 99; i >=0; i--) {
			 stack.pop();
		}
		Assert.assertEquals(true,stack.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(100,stack.size());
	}

}

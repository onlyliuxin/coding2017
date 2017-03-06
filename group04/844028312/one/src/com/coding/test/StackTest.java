package com.coding.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.ArrayList;
import com.coding.basic.Stack;

public class StackTest {
	private Stack stack;
	@Before
	public void setUp() throws Exception {
		stack=new Stack();
		for(int i=0;i<10;i++){
			stack.push(i);
		}
	}
	
	@Test
	public void pushAndpop() {
		Assert.assertEquals(9, stack.pop());
	}
	@Test
	public void peek() {
		Assert.assertEquals(9, stack.peek());
		Assert.assertEquals(9, stack.peek());
	}
	@Test
	public void isEmpty() {
		Assert.assertEquals(false,stack.isEmpty());
	}
	@Test
	public void size(){
		Assert.assertEquals(10,stack.size());		
	}

}

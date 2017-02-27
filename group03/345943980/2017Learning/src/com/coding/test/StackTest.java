package com.coding.test;

import org.junit.Assert;
import org.junit.Test;

import com.coding.basic.Stack;

public class StackTest {

	@Test
	public void test01(){
		Stack stack = new Stack();
		stack.push(111);
		stack.push(2222);
		
		Assert.assertEquals(stack.pop(), 2222);
		
		Assert.assertEquals(stack.peek(),111);
		
		Assert.assertEquals(stack.pop(), 111);
		Assert.assertEquals(stack.isEmpty(), true);
		
	}
}

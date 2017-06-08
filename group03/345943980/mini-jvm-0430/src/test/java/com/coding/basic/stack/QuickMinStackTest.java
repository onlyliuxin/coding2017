package com.coding.basic.stack;

import org.junit.Assert;
import org.junit.Test;

public class QuickMinStackTest {

	@Test
	public void test() {
		QuickMinStack stack = new QuickMinStack();
		stack.push(5);
		Assert.assertEquals(5, stack.findMin());
		stack.push(6);
		Assert.assertEquals(5, stack.findMin());
		stack.push(4);
		Assert.assertEquals(4, stack.findMin());
		stack.push(4);
		Assert.assertEquals(4, stack.findMin());
		
		stack.pop();
		Assert.assertEquals(4, stack.findMin());
		stack.pop();
		Assert.assertEquals(5, stack.findMin());
		stack.pop();
		Assert.assertEquals(5, stack.findMin());
	}
}

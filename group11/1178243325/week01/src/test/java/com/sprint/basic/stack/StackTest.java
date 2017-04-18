package com.sprint.basic.stack;

import org.junit.Assert;
import org.junit.Test;

public class StackTest {
	
	private Stack stack = new Stack();

	@Test
	public void testStack() {
		Assert.assertTrue(stack.push(1));			
		Assert.assertEquals(1, stack.pop());
		Assert.assertTrue(stack.push(2));
		Assert.assertTrue(stack.push(3));
		Assert.assertTrue(stack.push(4));
		Assert.assertEquals(4, stack.pop());
		Assert.assertEquals(2, stack.peek());
		Assert.assertEquals(2, stack.size());
		Assert.assertFalse(stack.isEmpty());
	}

}

package com.sprint.datastructure;

import org.junit.Assert;
import org.junit.Test;
public class StackWithTwoQueuesTest {

	@Test
	public void test() {
		StackWithTwoQueues stack = new StackWithTwoQueues();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		Assert.assertEquals(3, stack.pop());
		Assert.assertEquals(2, stack.pop());
		Assert.assertEquals(1, stack.pop());
	}
}

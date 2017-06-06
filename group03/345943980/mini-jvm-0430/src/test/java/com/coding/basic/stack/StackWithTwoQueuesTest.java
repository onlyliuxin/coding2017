package com.coding.basic.stack;

import org.junit.Assert;
import org.junit.Test;

public class StackWithTwoQueuesTest {

	@Test
	public void test() {
		StackWithTwoQueues stack = new StackWithTwoQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        Assert.assertEquals(4, stack.pop());
        Assert.assertEquals(3, stack.pop());
      
        stack.push(5);
        Assert.assertEquals(5, stack.pop());
        Assert.assertEquals(2, stack.pop());
        Assert.assertEquals(1, stack.pop());
	}
}

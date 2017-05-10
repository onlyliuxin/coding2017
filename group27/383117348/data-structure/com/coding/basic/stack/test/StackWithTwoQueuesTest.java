package com.coding.basic.stack.test;

import java.util.LinkedList;

import org.junit.Test;

import com.coding.basic.stack.StackWithTwoQueues;

import junit.framework.Assert;

public class StackWithTwoQueuesTest {
	
	@Test
	public void testTwoQueuesStack(){
		StackWithTwoQueues swq = new StackWithTwoQueues();
		swq.push(3);
		swq.push(7);
		swq.push(10);
		LinkedList list = null;
		Assert.assertEquals(10, swq.pop());
		Assert.assertEquals(7, swq.pop());
		Assert.assertEquals(3, swq.pop());
	}
}

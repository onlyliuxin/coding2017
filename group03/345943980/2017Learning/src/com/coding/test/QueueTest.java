package com.coding.test;

import org.junit.Assert;
import org.junit.Test;

import com.coding.basic.Queue;

public class QueueTest {

	@Test
	public void test01() {
		Queue queue = new Queue();
		queue.enQueue(1111);
		queue.enQueue(2222);
		
		Assert.assertEquals(queue.deQueue(), 1111);
		Assert.assertEquals(queue.isEmpty(),false);
		Assert.assertEquals(queue.size(),1);
		Assert.assertEquals(queue.deQueue(), 2222);
		Assert.assertEquals(queue.isEmpty(),true);
		Assert.assertEquals(queue.size(),0);
	}
}

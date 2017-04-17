package com.coding.basic;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class QueueTest {

	@Test
	public void test() {
		Queue queue = new Queue();
		for (int i = 0; i < 100; i++) {
			queue.enQueue(i);
		}
		Assert.assertEquals(100, queue.size());
		for (int i = 0; i < 100; i++) {
			Assert.assertEquals(i, queue.deQueue());
		}
		Assert.assertEquals(0, queue.size());
		
	}
	
}

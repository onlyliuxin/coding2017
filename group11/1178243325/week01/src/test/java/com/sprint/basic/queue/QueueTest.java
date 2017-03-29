package com.sprint.basic.queue;

import org.junit.Assert;
import org.junit.Test;
public class QueueTest {
	
	private Queue queue = new Queue();

	@Test
	public void testQueueApi() {
		Assert.assertTrue(queue.enQueue(1));
		Assert.assertTrue(queue.enQueue(2));
		Assert.assertFalse(queue.isEmpty());
		Assert.assertEquals(2, queue.size());
		Assert.assertEquals(1, queue.deQueue());
		Assert.assertEquals(2, queue.deQueue());
		Assert.assertEquals(0, queue.size());
		Assert.assertTrue(queue.isEmpty());
	}
}

package com.sprint.datastructure;

import org.junit.Assert;
import org.junit.Test;
public class QueueWithTwoStacksTest {

	@Test
	public void test() {
		QueueWithTwoStacks queue = new QueueWithTwoStacks();
		Assert.assertTrue(queue.isEmpty());
		
		queue.enQueue("a");
		queue.enQueue("b");
		queue.enQueue("c");
		queue.enQueue("d");
		queue.enQueue("e");
		
		Assert.assertFalse(queue.isEmpty());
		Assert.assertEquals(5, queue.size());
		
		Assert.assertEquals("a", queue.deQueue());
		Assert.assertEquals("b", queue.deQueue());
		Assert.assertEquals("c", queue.deQueue());
		Assert.assertEquals("d", queue.deQueue());
		Assert.assertEquals("e", queue.deQueue());
		
	}
}

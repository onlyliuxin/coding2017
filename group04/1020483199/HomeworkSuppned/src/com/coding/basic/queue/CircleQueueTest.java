package com.coding.basic.queue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class CircleQueueTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		CircleQueue<String> queue = new CircleQueue<String>(5);		
		Assert.assertTrue(queue.isEmpty());
		Assert.assertFalse(queue.isFull());
		
		queue.enQueue("a");
		queue.enQueue("b");
		queue.enQueue("c");
		queue.enQueue("d");
		queue.enQueue("e");
		
		Assert.assertTrue(queue.isFull());
		Assert.assertFalse(queue.isEmpty());
		Assert.assertEquals(5, queue.size());
		
		Assert.assertEquals("a", queue.deQueue());
		Assert.assertEquals("b", queue.deQueue());
		Assert.assertEquals("c", queue.deQueue());
		Assert.assertEquals("d", queue.deQueue());
		Assert.assertEquals("e", queue.deQueue());
		
	}

}

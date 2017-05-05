package com.coding;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueueTest {
	
	private Queue<String> queue;

	@Before
	public void setUp() throws Exception {
		queue = new Queue<String>();
		queue.enQueue("first");
		queue.enQueue("second");
	}

	@Test
	public void testEnQueue() {
		Assert.assertEquals(2, queue.size());
		queue.enQueue("third");
		Assert.assertEquals(3, queue.size());
	}

	@Test
	public void testDeQueue() {
		Assert.assertEquals("first", queue.deQueue());
		Assert.assertEquals("second", queue.deQueue());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertEquals(false, queue.isEmpty());
		queue.deQueue();
		queue.deQueue();
		Assert.assertEquals(true, queue.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(2, queue.size());
	}

}

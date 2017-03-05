package com.zhaogd.collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueueTest {

	private Queue queue;
	
	@Before
	public void setUp() throws Exception {
		queue = new Queue();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEnQueue() {
		queue.enQueue(1);
		assertFalse(queue.isEmpty());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testDeQueueExecption() {
		queue.deQueue();
	}

	@Test
	public void testDeQueue() {
		queue.enQueue(1);
		assertEquals(1, queue.deQueue());
		assertTrue(queue.isEmpty());
	}

	@Test
	public void testIsEmpty() {
		queue.enQueue(1);
		assertFalse(queue.isEmpty());
		
		queue.deQueue();
		assertTrue(queue.isEmpty());
	}

	@Test
	public void testSize() {
		queue.enQueue(1);
		queue.enQueue(1);
		queue.enQueue(1);
		
		assertEquals(3, queue.size());
	}

}

package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.Queue;

//
public class QueueTest {

	@Test
	public void testEnQueue() {
		Queue queue = new Queue();
		assertEquals(queue.size(), 0);
		System.out.print(queue.getHead());
	}

	@Test
	public void testDeQueue() {
		Queue queue = new Queue();
		int tmp = 0;
		try {
			queue.deQueue();
		} catch (IndexOutOfBoundsException e) {
			tmp = 1;
			assertEquals(tmp, 1);
		}
		queue.enQueue(new Object());
		assertNotNull(queue.deQueue());
	}

	@Test
	public void testIsEmpty() {
		Queue queue = new Queue();
		assertTrue(queue.isEmpty());
		queue.enQueue(new Object());
		assertFalse(queue.isEmpty());
	}

	@Test
	public void testSize() {
		Queue queue = new Queue();
		assertEquals(queue.size(), 0);
	}	

}

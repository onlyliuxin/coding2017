package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyQueueTest {

	@Test
	public void testEnQueue() {
		MyQueue mq = new MyQueue();
		assertEquals(mq.size(), 0);
		mq.enQueue(new Object());
		assertEquals(mq.size(), 1);
	}

	@Test
	public void testDeQueue() {
		MyQueue mq = new MyQueue();
		int tmp = 0;
		try {
			mq.deQueue();
		} catch (IndexOutOfBoundsException e) {
			tmp = 1;
			assertEquals(tmp, 1);
		}
		mq.enQueue(new Object());
		assertNotNull(mq.deQueue());
	}

	@Test
	public void testIsEmpty() {
		MyQueue mq = new MyQueue();
		assertTrue(mq.isEmpty());
		mq.enQueue(new Object());
		assertFalse(mq.isEmpty());
	}

	@Test
	public void testSize() {
		MyQueue mq = new MyQueue();
		assertEquals(mq.size(), 0);
	}

}

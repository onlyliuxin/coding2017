package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {

	@Test
	public void testEnQueue() {
		Queue q = new Queue();
		assertEquals(0, q.size());
		
		q.enQueue(1);
		q.enQueue(2);
		q.enQueue(3);
	}

	@Test
	public void testDeQueue() {
		Queue q = new Queue();
		q.enQueue(1);
		q.enQueue(2);
		q.enQueue(3);
		assertEquals(1, q.deQueue());
		assertEquals(2, q.deQueue());
	}

}

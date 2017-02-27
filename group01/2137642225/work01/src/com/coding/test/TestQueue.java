package com.coding.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.coding.mybasic.Queue;

public class TestQueue {

	private Queue queue;
	@Before
	public void before(){
		queue = new Queue();
		queue.enQueue(1);
		queue.enQueue(2);
	}
	@Test
	public void testEnQueue() {
		queue.enQueue(3);
		assertEquals(3, queue.size());
	}

	@Test
	public void testDeQueue() {
		assertEquals(2, queue.deQueue());
		assertEquals(1, queue.deQueue());
	}

	@Test
	public void testSize() {
		assertEquals(2, queue.size());
	}

}

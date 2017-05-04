package com.coding.basic.queue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueueWithTwoStacksTest {
	
	private QueueWithTwoStacks<Integer> queue;

	@Before
	public void setUp() throws Exception {
		queue = new QueueWithTwoStacks<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		queue = null;
	}

	@Test
	public void testIsEmpty() {
		assertEquals(true, queue.isEmpty());
		queue.enQueue(1);
		assertEquals(false, queue.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(0, queue.size());
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		assertEquals(3, queue.size());
	}

	@Test
	public void testEnQueue() {
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		assertEquals("1,2,3", queue.toString());
	}

	@Test
	public void testDeQueue() {
		queue.enQueue(1);
		queue.enQueue(2);
		queue.deQueue();
		queue.enQueue(3);
		assertEquals("2,3", queue.toString());
	}

}

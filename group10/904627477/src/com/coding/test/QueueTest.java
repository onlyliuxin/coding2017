package com.coding.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.Queue;

public class QueueTest {
	
	private static Queue queue;

	@Before
	public void setUp() throws Exception {
		queue = new Queue();
		queue.enQueue("111");
		queue.enQueue("222");
		queue.enQueue("333");
	}

	@After
	public void tearDown() throws Exception {
		queue = null;
	}

	@Test
	public void testEnQueue() {
		queue.enQueue("444");
		assertEquals(4, queue.size());
	}

	@Test
	public void testDeQueue() {
		Object obj = queue.deQueue();
		assertEquals(2, queue.size());
		assertEquals("111",obj);
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false, queue.isEmpty());
		queue = new Queue();
		assertEquals(true, queue.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(3, queue.size());
	}

}

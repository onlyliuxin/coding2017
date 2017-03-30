package com.xiaol.study;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueueTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEnQueue() {
		Queue q = new Queue();
		assertEquals(0, q.size());
		q.enQueue(1);
		q.enQueue(2);
		assertEquals(2, q.size());
	}

	@Test
	public void testDeQueue() {
		Queue q = new Queue();
		assertEquals(0, q.size());
		q.enQueue(1);
		q.enQueue(2);
		assertEquals(2, q.size());
		q.deQueue();
		q.deQueue();
		assertEquals(0, q.size());
	}

	@Test
	public void testIsEmpty() {
		Queue q = new Queue();
		assertEquals(true, q.isEmpty());
		q.enQueue(1);
		q.enQueue(2);
		assertEquals(false, q.isEmpty());
		q.deQueue();
		q.deQueue();
		assertEquals(true, q.isEmpty());
	}

	@Test
	public void testSize() {
		Queue q = new Queue();
		assertEquals(0, q.size());
		q.enQueue(1);
		q.enQueue(2);
		assertEquals(2, q.size());
		q.deQueue();
		q.deQueue();
		assertEquals(0, q.size());
	}

}

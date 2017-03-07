package com.coding.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Queue;

public class QueueTest {

	private Queue queue;

	@Before
	public void setUp() throws Exception {
		queue = new Queue();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("size:" + queue.size());
		System.out.println(queue);
	}

	@Test
	public void testEnQueue() {
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
	}

	@Test
	public void testDeQueue() {
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		System.out.println(queue.deQueue());
	}

	@Test
	public void testIsEmpty() {
		queue.enQueue(1);
		queue.enQueue("abcd");
		System.out.println(queue.isEmpty());
	}

	@Test
	public void testSize() {
		queue.enQueue(1);
		System.out.println(queue.size());
	}

}

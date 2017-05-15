package com.github.ipk2015.coding2017.basic.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.ipk2015.coding2017.basic.queue.Queue;

public class QueueTest {
	Queue queue;
	@Before
	public void setUp() throws Exception {
		queue=new Queue();
	}

	@Test
	public void testEnQueue() {
		queue.enQueue("hehe1");
		queue.enQueue("hehe2");
		assertEquals(2, queue.size());
	}

	@Test
	public void testDeQueue() {
		queue.enQueue("hehe1");
		queue.enQueue("hehe2");
		queue.deQueue();
		assertEquals(1, queue.size());
	}

	@Test
	public void testIsEmpty() {
		queue.enQueue("hehe1");
		queue.enQueue("hehe2");
		queue.deQueue();
		queue.deQueue();
		assertEquals(true, queue.isEmpty());
	}

	@Test
	public void testSize() {
		queue.enQueue("hehe1");
		queue.enQueue("hehe2");
		queue.deQueue();
		assertEquals(1, queue.size());
	}

}

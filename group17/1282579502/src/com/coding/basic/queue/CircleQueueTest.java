package com.coding.basic.queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CircleQueueTest {

	CircleQueue<Integer> cq = null;
	@Before
	public void setUp() throws Exception {
		cq = new CircleQueue<>();
		
	}

	@Test
	public void testSize() {
		cq.enQueue(1);
		cq.enQueue(1);
		cq.enQueue(1);
		assertEquals(3, cq.size());
	}

	@Test
	public void testEnQueue() {
		cq.enQueue(1);
		cq.enQueue(2);
		System.out.println(cq.toString());
	}

	@Test
	public void testDeQueue() {
		cq.enQueue(2);cq.enQueue(3);
		assertEquals(Integer.valueOf(2), cq.deQueue());
		assertEquals(Integer.valueOf(3), cq.deQueue());
	}

	@Test
	public void testIterator() {
		cq.enQueue(1);
		cq.enQueue(2);
		cq.enQueue(3);
		cq.enQueue(4);
		cq.enQueue(5);
		
		System.out.println(cq.toString());
		cq.printInfo();
		assertEquals("1 2 3 4 5 ", cq.toString());
	}

}

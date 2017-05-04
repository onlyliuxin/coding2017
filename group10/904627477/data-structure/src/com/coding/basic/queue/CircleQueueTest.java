package com.coding.basic.queue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CircleQueueTest {
	
	private CircleQueue<Integer> c;

	@Before
	public void setUp() throws Exception {
		c = new CircleQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		c = null;
	}

	@Test
	public void testIsEmpty() {
		assertEquals(true, c.isEmpty());
		c.enQueue(1);
		assertEquals(false, c.isEmpty());
	}

	/*@Test
	public void testIsFull() {
		assertEquals(false, c.isFull());
		c.enQueue(1);
		c.enQueue(2);
		c.enQueue(3);
		assertEquals(true, c.isFull());
	}*/

	@Test
	public void testSize() {
		assertEquals(0, c.size());
		c.enQueue(1);
		c.enQueue(2);
		assertEquals(2, c.size());
		c.deQueue();
		assertEquals(1, c.size());
		c.deQueue();
		assertEquals(0, c.size());
		c.enQueue(1);
		assertEquals(1, c.size());
	}

	@Test
	public void testEnQueue() {
		c.enQueue(1);
		c.enQueue(2);
		c.enQueue(3);
		assertEquals("1,2,3", c.toString());
	}

	@Test
	public void testDeQueue() {
		c.enQueue(1);
		c.enQueue(2);
		c.deQueue();
		c.enQueue(3);
		c.enQueue(4);
		assertEquals("2,3,4", c.toString());
	}

}

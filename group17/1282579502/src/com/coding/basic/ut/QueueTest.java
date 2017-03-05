package com.coding.basic.ut;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Queue;

public class QueueTest {

	Queue target = null;
	@Before
	public void setUp() throws Exception {
		target = new Queue();
	}

	@Test
	public void testEnQueue() {
		target.enQueue(10);
		target.enQueue("s0");
		assertEquals(2, target.size());	
	}

	@Test
	public void testDeQueue() {
		target.enQueue(10);
		target.enQueue("s0");
		assertEquals(10, target.deQueue());
		assertEquals("s0", target.deQueue());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testDeQueueOnEmpty() {
		assertEquals(10, target.deQueue());
		
	}

	@Test
	public void testIsEmpty() {
		assertTrue(target.isEmpty());
		target.enQueue(0);
		assertFalse(target.isEmpty());
	}
	

	@Test
	public void testSize() {
		assertEquals(0, target.size());
		target.enQueue(1);
		assertEquals(1, target.size());
	}

}

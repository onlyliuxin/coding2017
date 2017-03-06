package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 2017/2/26
 * @author 236995728
 *
 */
public class QueueTest {
	Queue queue = new Queue();
	@Before
	public void setUp() throws Exception {
		for(int i=1;i<10;i++){
			queue.enQueue(i);
		}
	}

	@Test
	public void testEnQueue() {
		queue.enQueue(11);
		Object o = queue.deQueue();
		assertEquals(1,o);
	}

	@Test
	public void testDeQueue() {
		Object o = null;
		for(int i=1;i<6;i++){
			o = queue.deQueue();
		}
		assertEquals(5, o);
	}

	@Test
	public void testIsEmpty() {
		for(int i=1;i<10;i++){
			queue.deQueue();
		}
		boolean result = queue.isEmpty();
		assertTrue(result);;
	}

	@Test
	public void testSize() {
		int size = queue.size();
		assertEquals(9, size);
	}

}

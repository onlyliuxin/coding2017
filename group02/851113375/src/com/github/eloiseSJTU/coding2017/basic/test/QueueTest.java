package com.github.eloiseSJTU.coding2017.basic.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.github.eloiseSJTU.coding2017.basic.Queue;

public class QueueTest {
	private Queue queue;
	
	@Before
	public void setUpQueue() {
		queue = new Queue();
	}
	
	@Test
	public void testQueueFunctional() {
		assertEquals(true, queue.isEmpty());
		queue.enQueue(4);
		queue.enQueue(2);
		assertEquals(2, queue.size());
		assertEquals(false, queue.isEmpty());
		
		int i = (Integer)queue.deQueue();
		assertEquals(4, i);
		i = (Integer)queue.deQueue();
		assertEquals(2, i);
		
		assertEquals(0, queue.size());
		assertEquals(true, queue.isEmpty());
	}

}

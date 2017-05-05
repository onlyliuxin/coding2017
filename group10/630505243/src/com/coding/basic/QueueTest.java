package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueueTest {
	Queue queue = null;
	String str1 = "First";
	String str2 = "Second";
	String str3 = "Third";
	String str4 = "Forth";
	@Before
	public void setUp() throws Exception {
		queue = new Queue();
	}

	@Test
	public void testEnQueue() {
		queue.enQueue(str1);
		queue.enQueue(str2);
		queue.enQueue(str3);
		queue.enQueue(str4);
		assertEquals(queue.size(),4);
		assertFalse(queue.isEmpty());
	}

	@Test
	public void testDeQueue() {
		queue.enQueue(str1);
		queue.enQueue(str2);
		queue.enQueue(str3);
		queue.enQueue(str4);
		assertEquals(str1,(String) queue.deQueue());
		assertEquals(queue.size(),3);
		assertEquals(str2,(String) queue.deQueue());
		assertEquals(queue.size(),2);
		assertEquals(str3,(String) queue.deQueue());
		assertEquals(queue.size(),1);
		assertEquals(str4,(String) queue.deQueue());
		assertEquals(queue.size(),0);
		assertFalse(queue.isEmpty());
	}

}

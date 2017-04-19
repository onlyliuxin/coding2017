package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueueTest {

	public static Queue queue = new Queue();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEnQueue() {
		queue.enQueue("aaa");
		queue.enQueue("bbb");
		queue.enQueue("ccc");
		queue.enQueue("aaa");
		System.out.println(queue);
	}

	@Test
	public void testDeQueue() {
		queue.enQueue("aaa");
		queue.enQueue("bbb");
		queue.enQueue("ccc");
		queue.enQueue("ddd");
		queue.enQueue("eee");
		queue.deQueue();
		System.out.println(queue);
	}

	@Test
	public void testIsEmpty() {
		System.out.println(queue.isEmpty());
		
	}

	@Test
	public void testSize() {
		queue.enQueue("aaa");
		queue.enQueue("bbb");
		queue.enQueue("ccc");
		queue.enQueue("ddd");
		queue.enQueue("eee");
		System.out.println(queue.size());
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}

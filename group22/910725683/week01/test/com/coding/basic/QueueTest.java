package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.Queue;

public class QueueTest {
	Queue queue = new Queue();

	@Test
	public void testEnQueue() {
		for (int i=0;i<10;i++){
			queue.enQueue(i);
		}
	}

	@Test
	public void testDeQueue() {
		testEnQueue();
		for (int i=0;i<3;i++){
			System.out.println("deQueue : " + queue.deQueue());
		}
	}

	@Test
	public void testIsEmpty() {
		System.out.println("is empty(true) : "+queue.isEmpty());
		testEnQueue();
		System.out.println("is empty(false) : "+queue.isEmpty());
	}

	@Test
	public void testSize() {
		testEnQueue();
		System.out.println("size : "+queue.size());
	}

}

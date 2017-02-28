package com.coding.basic;

import org.junit.Test;

public class QueueTest {

	@Test
	public void testQueue(){
		Queue q = new Queue();
		q.enQueue("a");
		q.enQueue("b");
		q.enQueue("c");
		q.deQueue();
		System.out.println(q.size());
	}
}

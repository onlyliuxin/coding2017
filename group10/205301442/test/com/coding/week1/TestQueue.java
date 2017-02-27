package com.coding.week1;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestQueue {

	@Test
	public void test() {
		Queue queue = new Queue();
		queue.enQueue("MM");
		assertEquals(queue.deQueue(), "MM");
		assertEquals(queue.isEmpty(), true);
	}

}

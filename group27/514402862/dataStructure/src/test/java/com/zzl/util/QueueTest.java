package com.zzl.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueueTest {

	private Queue q;
	
    @Before
    public void init() {
		q = new Queue();
		
		q.enQueue("1");
		q.enQueue("2");
		q.enQueue("3");
		q.enQueue("4");
		q.enQueue("5");
    }
	
	@Test
	public void testEnQueue() {
		assertEquals(q.size(), 5);
		
		q.deQueue();
		assertEquals(q.size(), 4);
	}

	@Test
	public void testIsEmpty() {
		q = new Queue();
		
		assertEquals(q.isEmpty(), true);
		
		q.enQueue("1");
		assertEquals(q.isEmpty(), false);
		
		q.deQueue();
		assertEquals(q.isEmpty(), true);
	}

}

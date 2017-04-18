package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TqueueTest {

	Tqueue tq = new Tqueue();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}



	@Test
	public void testEnQueue() {
		tq.enQueue(5);
		tq.enQueue(9);
		Assert.assertEquals(2, tq.size());
	}

	@Test
	public void testDeQueue() {
		tq.enQueue(5);
		tq.enQueue(9);
		Assert.assertEquals(2, tq.size());
		Assert.assertEquals(5, tq.deQueue());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertEquals(true, tq.isEmpty());
	}

	@Test
	public void testSize() {
		tq.enQueue(5);
		tq.enQueue(9);
		tq.enQueue(null);
		Assert.assertEquals(3, tq.size());
	}

}

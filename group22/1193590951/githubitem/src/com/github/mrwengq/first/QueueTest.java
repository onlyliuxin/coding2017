package com.github.mrwengq.first;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueueTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	Queue qu = new Queue();

	@Test
	public void testEnQueue() {
		Queue qu = new Queue();
		qu.enQueue("12");
		qu.enQueue("16");
		qu.enQueue("22");
		qu.enQueue("11");
		qu.enQueue("62");

		
	}

	@Test
	public void testDeQueue() {
		Queue qu = new Queue();
		qu.enQueue("12");
		qu.enQueue("16");
		qu.enQueue("22");
		qu.enQueue("11");
		qu.enQueue("62");
		assertEquals(qu.deQueue(), 12+"");
		assertEquals(qu.deQueue(), 16+"");
		assertEquals(qu.deQueue(), 22+"");
		assertEquals(qu.deQueue(), 11+"");
		assertEquals(qu.deQueue(), 62+"");

	}

	@Test
	public void testIsEmpty() {
		Queue qu = new Queue();
		qu.enQueue("12");
		qu.enQueue("16");
		qu.enQueue("22");
		qu.enQueue("11");
		qu.enQueue("62");
		assertEquals(qu.deQueue(), 12+"");
		assertEquals(qu.deQueue(), 16+"");
		assertEquals(qu.deQueue(), 22+"");
		assertEquals(qu.deQueue(), 11+"");
		assertEquals(qu.deQueue(), 62+"");
		assertEquals(qu.isEmpty(),true);
	}

	@Test
	public void testSize() {
		Queue qu = new Queue();
		qu.enQueue("12");
		qu.enQueue("16");
		qu.enQueue("22");
		qu.enQueue("11");
		qu.enQueue("62");
		assertEquals(qu.size(), 5);
	}

}

package org.coding.one;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class QueueTest {

	private Queue target;
	
	@Before
	public void setUp() throws Exception {
		target = new Queue();
	}

	@After
	public void tearDown() throws Exception {
		target = null;
	}

	@Test
	public void testEnQueue() {
		Assert.assertEquals(0, target.size());
		target.enQueue(1);
		target.enQueue(2);
		Assert.assertEquals(2, target.size());
	}

	@Test
	public void testDeQueue() {
		Assert.assertEquals(0, target.size());
		target.enQueue(1);
		target.enQueue(2);
		Assert.assertEquals(2, target.size());
		Assert.assertEquals(1, target.deQueue());
		Assert.assertEquals(2, target.deQueue());
		Assert.assertEquals(0, target.size());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(target.isEmpty());
		target.enQueue(1);
		Assert.assertFalse(target.isEmpty());
		target.deQueue();
		Assert.assertTrue(target.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(0, target.size());
		target.enQueue(1);
		Assert.assertEquals(1, target.size());
		target.deQueue();
		Assert.assertEquals(0, target.size());
	}

}

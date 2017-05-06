package week08.queue.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week08.queue.Queue;

public class QueueTest {

	Queue<Integer> queue = new Queue<Integer>();

	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < 100; i++) {
			queue.enQueue(i);
		}
	}

	@Test
	public void testEnQueue() {
		Assert.assertFalse(queue.isEmpty());
		Assert.assertEquals(100, queue.size());
		for (int i = 100; i < 200; i++) {
			queue.enQueue(i);
		}
		Assert.assertEquals(200, queue.size());
		for (int i = 0; i < 200; i++) {
			Assert.assertEquals(i, queue.deQueue().intValue());
		}
		Assert.assertTrue(queue.isEmpty());
		Assert.assertEquals(0, queue.size());
	}

	@Test
	public void testDeQueue() {
		Assert.assertFalse(queue.isEmpty());
		Assert.assertEquals(100, queue.size());
		for (int i = 0; i < 100; i++) {
			Assert.assertEquals(i, queue.deQueue().intValue());
		}
		Assert.assertTrue(queue.isEmpty());
		Assert.assertEquals(0, queue.size());
	}

}

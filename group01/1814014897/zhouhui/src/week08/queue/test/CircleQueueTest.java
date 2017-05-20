package week08.queue.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week08.queue.CircleQueue;

public class CircleQueueTest {
	
	CircleQueue<Integer> cq = new CircleQueue<Integer>();

	@Test
	public void testenQueue() {
		Assert.assertTrue(cq.isEmpty());
		Assert.assertEquals(0, cq.size());
		for (int i = 0; i < 10; i++) {
			cq.enQueue(i);
		}
		Assert.assertFalse(cq.isEmpty());
		Assert.assertEquals(10, cq.size());
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(i, cq.deQueue().intValue());
		}
		Assert.assertTrue(cq.isEmpty());
		Assert.assertEquals(0, cq.size());
	}

	@Test
	public void testdeQueue() {
		for (int i = 0; i < 10; i++) {
			cq.enQueue(i);
		}
		Assert.assertFalse(cq.isEmpty());
		Assert.assertEquals(10, cq.size());
		for (int i = 0; i < 10; i++) {
			Assert.assertEquals(i, cq.deQueue().intValue());
		}
		Assert.assertTrue(cq.isEmpty());
		Assert.assertEquals(0, cq.size());
	}

}

package week08.queue.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week08.queue.QueueWithTwoStacks;

public class QueueWithTwoStacksTest {

	QueueWithTwoStacks<Integer> qwts = new QueueWithTwoStacks<Integer>();
	
	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < 100; i++) {
			qwts.enQueue(i);
		}
	}

	@Test
	public void testEnQueue() {
		Assert.assertFalse(qwts.isEmpty());
		Assert.assertEquals(100, qwts.size());
		for (int i = 100; i < 200; i++) {
			qwts.enQueue(i);
		}
		Assert.assertEquals(200, qwts.size());
		for (int i = 0; i < 200; i++) {
			Assert.assertEquals(i, qwts.deQueue().intValue());
		}
		Assert.assertTrue(qwts.isEmpty());
		Assert.assertEquals(0, qwts.size());
	}

	@Test
	public void testDeQueue() {
		Assert.assertFalse(qwts.isEmpty());
		Assert.assertEquals(100, qwts.size());
		for (int i = 0; i < 100; i++) {
			Assert.assertEquals(i, qwts.deQueue().intValue());
		}
		Assert.assertTrue(qwts.isEmpty());
		Assert.assertEquals(0, qwts.size());
	}

}

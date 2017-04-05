package testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.basic.Queue;

public class TestQueue
{
	/*
	 * test enQueue() isEmpty() and size() deQueue
	 */
	@Test
	public void testQueue() {
		Queue queue = new Queue();

		assertTrue(queue.isEmpty());

		for (int i = 10; i < 20; i++) {
			queue.enQueue(i);
		}

		assertFalse(queue.isEmpty());
		assertTrue(queue.size() == 10);

		assertTrue(queue.deQueue().equals(10));
		assertTrue(queue.deQueue().equals(11));
	}

}

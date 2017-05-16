package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Queue;

public class QueueTest {
	private Queue queue;
	
	@Before
	public void init(){
		queue = new Queue();
	}

	@Test
	public void testEnQueue() {
	}

	@Test
	public void testDeQueue() {
		try{
			queue.deQueue();
			fail("empty queue hasn't element");
		}catch (Exception e) {
		}
		queue.enQueue("a");
		queue.enQueue("b");
		assertEquals("a", queue.deQueue());
		assertEquals("b", queue.deQueue());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(queue.isEmpty());
		queue.enQueue("a");
		assertFalse(queue.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(0, queue.size());
		queue.enQueue("a");
		queue.enQueue("a");
		assertEquals(2, queue.size());
	}

}

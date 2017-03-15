package liuxincourse;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {

	@Test
	public void testEnqueue() {
		Queue queue=new Queue();
		queue.enQueue(11);
		queue.enQueue(22);
		assertEquals(11, queue.deQueue());
	}
	
	@Test
	public void testIsempty() {
		Queue queue=new Queue();
		queue.enQueue(11);
		queue.enQueue(22);
		queue.deQueue();
		queue.deQueue();
		assertEquals(true, queue.isEmpty());
	}

}

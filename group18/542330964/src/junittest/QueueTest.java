package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import basicstruct.Queue;

public class QueueTest {

	Queue q = new Queue();
	@Before
	public void setUp() throws Exception {
		q.enQueue(11);
		q.enQueue(22);
		q.enQueue(33);
		q.enQueue(44);
		q.enQueue(55);
	}

	@Test
	public void testDeQueue() {
		q.deQueue();
		System.out.println(q.size());
	}

	@Test
	public void testIsEmpty() {
	System.out.println(q.isEmpty());
	}

}

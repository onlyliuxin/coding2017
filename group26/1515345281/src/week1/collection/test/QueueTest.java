package week1.collection.test;

import static org.junit.Assert.*;

import org.junit.Test;

import week1.collection.Queue;

public class QueueTest {

	private Queue queue=new Queue();
	
	@Test
	public void testEnQueue(){
		queue.enQueue("123");
		queue.enQueue("456");
		assertEquals("123",queue.deQueue());
	}
	
	@Test
	public void testDeQueue(){
		queue.enQueue("123");
		queue.enQueue("456");
		queue.deQueue();
		assertEquals("456",queue.deQueue());
	}
}

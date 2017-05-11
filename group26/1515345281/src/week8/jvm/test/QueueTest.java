package week8.jvm.test;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import week8.jvm.queue.CircleQueue;
import week8.jvm.queue.QueueWithTwoStacks;

public class QueueTest {

	@Test
	public void testQueueWithTwoStacks(){
		
		QueueWithTwoStacks queue=new QueueWithTwoStacks();
		
		queue.enQueue("AA");
		queue.enQueue("BB");
		queue.enQueue("CC");
		
		assertEquals("AA",queue.deQueue());
		assertEquals("BB",queue.deQueue());
		assertEquals("CC",queue.deQueue());
		
		try{
			queue.deQueue();
			fail("Queue is  Empty");
		}catch(Exception e){
			
		}
	}
	
	
	@Test
	public void testCircleQueue(){
		CircleQueue<String> queue = new CircleQueue<String>(5);		
		assertTrue(queue.isEmpty());
		assertFalse(queue.isFull());
		
		queue.enQueue("a");
		queue.enQueue("b");
		queue.enQueue("c");
		queue.enQueue("d");
		queue.enQueue("e");
		
		assertTrue(queue.isFull());
		assertFalse(queue.isEmpty());
		assertEquals(5, queue.size());
		
		assertEquals("a", queue.deQueue());
		assertEquals("b", queue.deQueue());
		assertEquals("c", queue.deQueue());
		assertEquals("d", queue.deQueue());
		assertEquals("e", queue.deQueue());
	}
	
	
	
	
	
	
	
	
}

package week01.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week01.basic.MyQueue;

public class MyQueueTest {
	
	private MyQueue queue = null;
	
	@Before
	public void setUp() throws Exception {
		queue = new MyQueue();
		
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
	}

	@After
	public void tearDown() throws Exception {
		queue = null;
	}

	@Test
	public void testEnQueue(){
		queue.enQueue(4);
		Assert.assertEquals((Object)new Integer(4), queue.size());
	}
	
	@Test
	public void testDeQueue(){
		Assert.assertEquals((Object) new Integer(1), queue.deQueue());
	}
	
	@Test
	public void testIsEmpty(){
		Assert.assertFalse(queue.isEmpty());
	}
	
	@Test
	public void testSize(){
		Assert.assertEquals((Object)new Integer(3), queue.size());
	}
}

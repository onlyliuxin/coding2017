package test;

import org.junit.Assert;
import org.junit.Test;

import basic.Queue;

public class QueueTest {
	
	@Test
	public void test01(){
		
		Queue queue = new Queue();
		boolean empty1 = queue.isEmpty();
		Assert.assertEquals(true, empty1);
		queue.enQueue("111");
		queue.enQueue("222");
		queue.enQueue("333");
		Object deQueue = queue.deQueue();
		Assert.assertEquals("111", deQueue);
		
		boolean empty2 = queue.isEmpty();
		Assert.assertEquals(false, empty2);
		
		int size = queue.size();
		Assert.assertEquals(2, size);
	}
	

}

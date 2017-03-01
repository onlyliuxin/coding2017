package test.com.pxshuo.basic.impl;

import org.junit.Assert;
import org.junit.Test;

import com.pxshuo.basic.impl.Queue;

public class QueueTest {
	public Queue object = new Queue();
	
	@Test
	public void enQueueTest() {
		Assert.assertEquals(true, object.isEmpty());
		object.enQueue("hello");
		object.enQueue("world");
		Assert.assertEquals(false, object.isEmpty());
		Assert.assertEquals(2, object.size());
		Assert.assertEquals("hello", object.deQueue());
		Assert.assertEquals("world", object.deQueue());
		Assert.assertEquals(0, object.size());
	}

}

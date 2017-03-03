package com.easy.util.myqueue;

import org.junit.Assert;
import org.junit.Test;

public class TestQueue {
	
	@Test
	public void test_enQueue_object(){
		Queue queue =new Queue();
		queue.enQueue("aa");
		queue.enQueue("bb");
		queue.enQueue("cc");
		Assert.assertEquals("[aa,bb,cc]",queue.toString());
	}
	
	@Test
	public void test_deQueue(){
		Queue queue =new Queue();
		queue.enQueue("aa");
		queue.enQueue("bb");
		queue.enQueue("cc");
		Assert.assertEquals("aa", queue.deQueue());
		Assert.assertEquals("bb", queue.deQueue());
		Assert.assertEquals("cc", queue.deQueue());
	}
}

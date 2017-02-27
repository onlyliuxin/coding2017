package com.nitasty.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.nitasty.util.LinkedList;
import com.nitasty.util.Queue;

public class QueueTest {
	
	Queue queue;
	
	@Before
	public void init(){
		queue=new Queue();
		for (int i = 0; i < 100; i++) {
			queue.enQueue(i);
		}
	}

	@Test
	public void testDeQueue() {
		for(int i=0; i<100;i++){
			Assert.assertEquals(i, queue.deQueue());
		}
	}

	@Test
	public void testIsEmpty() {
		for(int i=0; i<100;i++){
			queue.deQueue();
			if(i<99)
				Assert.assertEquals(false, queue.isEmpty());
		}
		Assert.assertEquals(true, queue.isEmpty());
	}

	@Test
	public void testSize() {
		for(int i=99; i>0;i--){
			queue.deQueue();
			Assert.assertEquals(i, queue.size());
		}
	}

}

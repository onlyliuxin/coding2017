package com.coding.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.ArrayList;
import com.coding.basic.Queue;

public class QueueTest {
	private Queue queue;
	@Before
	public void setUp() throws Exception {
		queue=new Queue();
		for(int i=0;i<10;i++){
			queue.enQueue(i);
		}
	}
	
	@Test
	public void deQueue() {
		Assert.assertEquals(0,queue.deQueue() );
	}
	@Test
	public void isEmpty() {
		Assert.assertEquals(false,queue.isEmpty() );
	}
	@Test
	public void size() {
		Assert.assertEquals(10,queue.size());
	}

}

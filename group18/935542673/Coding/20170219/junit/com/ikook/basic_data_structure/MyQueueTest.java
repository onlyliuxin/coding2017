package com.ikook.basic_data_structure;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * 此单元测试只测试了正常情况，一些异常情况没有测试。
 * @author ikook
 */
public class MyQueueTest {
	
	private MyQueue queue;
	
	@Before
	public void setUp() {
		queue = new MyQueue();
		
		queue.enQueue(111);
		queue.enQueue("222");
		queue.enQueue(new Date());
	}

	@Test
	public void testEnQueue() {
		queue.enQueue(444);
		assertEquals(4, queue.size());
	}

	@Test
	public void testDeQueue() {
		assertEquals(111, queue.deQueue());
	}
	
	@Test
	public void testSize() {
		assertEquals(3, queue.size());
		
		MyQueue queue = new MyQueue();
		assertEquals(0, queue.size());
	}
	
	@Test
	public void testIsEmpty() {
		assertEquals(false, queue.isEmpty());
		
		MyQueue queue = new MyQueue();
		assertEquals(true, queue.isEmpty());
	}

}

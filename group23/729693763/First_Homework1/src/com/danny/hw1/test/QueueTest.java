package com.danny.hw1.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.danny.hw1.LinkedList;
import com.danny.hw1.Queue;

public class QueueTest {

	static Object[] Data = new Object[]{1,2,3,4,5,6,7,8};
	Queue test;
	@Before
	public void setUp() throws Exception{
		test = new Queue();
		for(Object data: Data){
			test.enQueue(data);
		}
	}

	@Test
	public void testEnQueue() {
		Object t=10;
		test.enQueue(t);	
		assertEquals(Data.length+1,test.size());
	}

	@Test
	public void testDeQueue() {
		Object t=test.deQueue();
		assertEquals(Data.length-1,test.size());
		assertEquals(Data[0], t);
	}
	@Test
	public void testIsEmpty() {
		assertFalse(test.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(Data.length,test.size());
	}

	
}

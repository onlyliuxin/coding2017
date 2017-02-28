package com.github.congcongcong250.coding2017.basicTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.congcongcong250.coding2017.basic.Queue;

public class QueueTest implements testCase {

	Queue testqueue = new Queue();
	
	@Override
	@Before
	public void setUp() {
		
		for(int i = 0; i < 20; i++){
			testqueue.enQueue(i);
		}
	}

	@Override
	@After
	public void tearDown() {
		testqueue.clear();
	}
	

	@Override
	@Test
	public void testAdd() {
		assertEquals(20,testqueue.size());
		assertEquals(0,testqueue.peek());
		assertEquals(20,testqueue.size());
		assertFalse(testqueue.isEmpty());
	}

	@Override
	@Test
	public void testRemove() {
		assertEquals(20,testqueue.size());
		assertEquals(0,testqueue.deQueue());
		assertEquals(19,testqueue.size());
		assertEquals(1,testqueue.peek());
		assertFalse(testqueue.isEmpty());
	}

	@Override
	@Test
	public void testFunctional() {
		for(int i = 0; i < 20; i++){
			testqueue.deQueue();
		}
		assertTrue(testqueue.isEmpty());
		testqueue.enQueue(100);
		testqueue.enQueue(200);
		assertEquals(100,testqueue.deQueue());
		testqueue.enQueue(400);
		assertEquals(200,testqueue.deQueue());
		assertFalse(testqueue.isEmpty());
		assertEquals(400,testqueue.deQueue());
		
		boolean hasExp = false;
		try{
			testqueue.deQueue();
		}catch (IndexOutOfBoundsException e){
			hasExp = true;
		}
		assertTrue(hasExp);
	}

}

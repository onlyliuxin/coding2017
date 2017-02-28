package com.github.congcongcong250.coding2017.basicTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.congcongcong250.coding2017.basic.Stack;

public class StackTest implements testCase {

	Stack teststack = new Stack();
	
	@Override
	@Before
	public void setUp() {
		
		for(int i = 0; i < 20; i++){
			teststack.push(i);
		}
	}

	@Override
	@After
	public void tearDown() {
		teststack.clear();
	}
	

	@Override
	@Test
	public void testAdd() {
		assertEquals(20,teststack.size());
		assertEquals(19,teststack.peek());
		assertEquals(20,teststack.size());
		assertFalse(teststack.isEmpty());
	}

	@Override
	@Test
	public void testRemove() {
		assertEquals(20,teststack.size());
		assertEquals(19,teststack.pop());
		assertEquals(19,teststack.size());
		assertEquals(18,teststack.peek());
		assertFalse(teststack.isEmpty());
	}

	@Override
	@Test
	public void testFunctional() {
		for(int i = 0; i < 20; i++){
			teststack.pop();
		}
		assertTrue(teststack.isEmpty());
		teststack.push(100);
		teststack.push(200);
		assertEquals(200,teststack.pop());
		teststack.push(400);
		assertEquals(400,teststack.pop());
		assertFalse(teststack.isEmpty());
		assertEquals(100,teststack.pop());
		
		
		boolean hasExp = false;
		try{
			teststack.pop();
		}catch (IndexOutOfBoundsException e){
			hasExp = true;
		}
		assertTrue(hasExp);
	}

}

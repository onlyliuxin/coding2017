package com.danny.hw1.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.danny.hw1.Queue;
import com.danny.hw1.Stack;

public class StackTest {

	static Object[] Data = new Object[]{1,2,3,4,5,6,7,8};
	Stack test;
	@Before
	public void setUp() throws Exception{
		test = new Stack();
		for(Object data: Data){
			test.push(data);
		}
	}

	@Test
	public void testPush() {
		Object t=10;
		test.push(t);	
		assertEquals(Data.length+1,test.size());
	}

	@Test
	public void testPop() {
		assertEquals(Data[Data.length-1], test.pop());
		assertEquals(Data.length-1, test.size());
	}

	@Test
	public void testPeek() {
		assertEquals(Data[Data.length-1], test.peek());
		assertEquals(Data.length, test.size());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(test.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(Data.length, test.size());
	}

	

}

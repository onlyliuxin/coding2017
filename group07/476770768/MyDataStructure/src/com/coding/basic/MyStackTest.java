package com.coding.basic;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

public class MyStackTest {

	@Test
	public void testPush() {
		MyStack ms = new MyStack();
		assertEquals(0, ms.size());
		ms.push(new Object());
		assertEquals(1, ms.size());
	}

	@Test
	public void testPop() {
		MyStack ms = new MyStack();
		ms.push(new Object());
		assertNotNull(ms.pop());
		assertEquals(0, ms.size());
	}

	@Test
	public void testPeek() {
		MyStack ms = new MyStack();
		int tmp = 0;
		try {
			ms.peek();
		} catch (EmptyStackException e) {
			tmp = 1;
			assertEquals(1, tmp);
		}
		ms.push(new Object());
		assertNotNull(ms.peek());
		assertEquals(1, ms.size());
	}

	@Test
	public void testIsEmpty() {
		MyStack ms = new MyStack();
		assertTrue(ms.isEmpty());
		ms.push(new Object());
		assertFalse(ms.isEmpty());
	}

	@Test
	public void testSize() {
		MyStack ms = new MyStack();
		assertEquals(0, ms.size());
	}

}

package com.coding.basic.ut;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Stack;

public class StackTest {

	Stack target = null;
	@Before
	public void setUp() throws Exception {
		target = new Stack();
	}

	@Test
	public void testPush() {
		target.push(10);
		target.push(null);
	}

	@Test
	public void testPop() {
		target.push(10);
		target.push(11);
		target.push(12);
		assertEquals(12, target.pop());
		assertEquals(11, target.pop());
		assertEquals(10, target.pop());
		
	}

	@Test
	public void testPeek() {
		Object item = target.peek();
		assertEquals(item, null);
		target.push(10);
		item = target.peek();
		assertEquals(item, 10);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(target.isEmpty());
		target.push(10);
		assertFalse(target.isEmpty());
		target.pop();
		assertTrue(target.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(0, target.size());
		target.push(10);
		assertEquals(1, target.size());
		target.push(10);
		assertEquals(2, target.size());
		target.pop();
		assertEquals(1, target.size());
		target.pop();
		assertEquals(0, target.size());
	}

}

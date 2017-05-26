package com.xiaol.study;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPush() {
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		assertEquals(3, s.size());
	}

	@Test
	public void testPop() {
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		assertEquals(3, s.pop());
		assertEquals(2, s.pop());
		assertEquals(1, s.pop());
		assertEquals(0, s.size());
	}

	@Test
	public void testPeek() {
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		assertEquals(3, s.peek());
	}

	@Test
	public void testIsEmpty() {
		Stack s = new Stack();
		assertEquals(true, s.isEmpty());
		s.push(1);
		s.push(2);
		s.push(3);
		assertEquals(false, s.isEmpty());
	}

	@Test
	public void testSize() {
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		assertEquals(3, s.size());
	}

}

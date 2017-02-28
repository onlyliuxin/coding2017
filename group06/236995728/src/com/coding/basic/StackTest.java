package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 2017/2/26
 * @author 236995728
 *
 */
public class StackTest {
	Stack stack = new Stack();
	@Before
	public void setUp() throws Exception {
		for(int i=1;i<10;i++){
			stack.push(i);
		}
	}

	@Test
	public void testPush() {
		stack.push(10);
		Object o = stack.pop();
		assertEquals(10, o);
	}

	@Test
	public void testPop() {
		Object o = null;
		for(int i=1;i<7;i++){
			o = stack.pop();
		}
		assertEquals(4, o);
	}

	@Test
	public void testPeek() {
		Object o = stack.peek();
		assertEquals(9, o);
	}

	@Test
	public void testIsEmpty() {
		for(int i=0;i<9;i++){
			stack.pop();
		}
		boolean result = stack.isEmpty();
		assertTrue(result);
	}

	@Test
	public void testSize() {
		assertEquals(9, stack.size());
	}

}

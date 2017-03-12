package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {

	@Test
	public void testPush() {
		Stack st = new Stack();
		st.push(1);
		assertEquals(1, st.peek());
		
		st.push(2);
		st.push(3);
		st.push(4);
		assertEquals(4, st.peek());
	}

	@Test
	public void testPop() {
		Stack st = new Stack();
		assertEquals(null, st.pop());
		
		st.push(1);
		assertEquals(1, st.pop());
		
		st.push(2);
		st.push(3);
		st.push(4);
		assertEquals(4, st.pop());
	}

	@Test
	public void testIsEmpty() {
		Stack st = new Stack();
		assertEquals(true, st.isEmpty());
		
		st.push(1);
		assertEquals(false, st.isEmpty());
	}


}

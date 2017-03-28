package com.github.mrwengq.first;

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
		Stack st =new Stack();
		st.push("1");
		st.push("2");
		st.push("3");
		st.push("4");
		st.push("5");
		
	}

	@Test
	public void testPop() {
		Stack st =new Stack();
		st.push("1");
		st.push("2");
		st.push("3");
		st.push("4");
		st.push("5");
		assertEquals(st.pop(), "5");
		assertEquals(st.pop(), "4");
		assertEquals(st.pop(), "3");
		assertEquals(st.pop(), "2");
		assertEquals(st.pop(), "1");
		assertEquals(st.isEmpty(),true);
	}

	@Test
	public void testPeek() {
		Stack st =new Stack();
		st.push("1");
		st.push("2");
		st.push("3");
		st.push("4");
		st.push("5");
		assertEquals(st.peek(), "5");
		assertEquals(st.peek(), "4");
		assertEquals(st.peek(), "3");
		assertEquals(st.peek(), "2");
		assertEquals(st.peek(), "1");
		assertEquals(st.isEmpty(),false);
	}

	@Test
	public void testIsEmpty() {
		Stack st =new Stack();
		assertEquals(st.isEmpty(),true);
		st.push("1");
		st.push("2");
		st.push("3");
		st.push("4");
		st.push("5");
		assertEquals(st.isEmpty(),false);

	}

	@Test
	public void testSize() {
		Stack st =new Stack();
		assertEquals(st.size(),0);
		st.push("1");
		st.push("2");
		st.push("3");
		st.push("4");
		st.push("5");
		assertEquals(st.size(),5);
	}

}

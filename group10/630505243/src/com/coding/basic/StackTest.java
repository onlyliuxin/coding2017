package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackTest {

	Stack s = null;
	String a = "A";
	String b = "B";
	String c = "C";
	@Before
	public void setUp() throws Exception {
		s = new Stack();
		s.push(a);
		//System.out.println(s.peek());
		s.push(b);
		//System.out.println(s.peek());
		s.push(c);
		//System.out.println(s.peek());
	}

	@Test
	public void testPush() {
		String d = "D";
		String e = "E";
		s.push(d);
		System.out.println(s.peek());
		s.push(e);
		System.out.println(s.peek());
		assertEquals(s.size(), 5);
	}

	@Test
	public void testPop() {
		int len = s.size();
		for(int i=0;i<len;i++){
			System.out.println(s.pop());
		}
		//assertEquals(s.size(), 0);
	}

	@Test
	public void testPeek() {
		System.out.println(s.peek());
	}

	@Test
	public void testIsEmpty() {
		System.out.println(s.isEmpty());
		assertFalse(s.isEmpty());
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		assertEquals(s.size(), 3);
	}

}

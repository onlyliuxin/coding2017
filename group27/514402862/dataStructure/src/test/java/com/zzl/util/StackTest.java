package com.zzl.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackTest {

	private Stack s;
	
    @Before
    public void init() {
        s = new Stack();

		s.push("1");
		s.push("2");
		s.push("3");
		s.push("4");
		s.push("5");
    }
	
	@Test
	public void testPush() {
		assertEquals(s.size(), 5);
	}

	@Test
	public void testPop() {
		s.pop();
		
		assertEquals(s.size(), 4);
		String[] str = {"1","2","3","4"};
		Common.loop(s, str);
	}

	@Test
	public void testPeek() {
		String[] str = {"1","2","3","4","5"};
		Common.loop(s, str);
	}

	@Test
	public void testIsEmpty() {
		assertFalse(s.isEmpty());
		
		String[] str = {"1","2","3","4","5"};
		Common.loop(s, str);
		assertTrue(s.isEmpty());
		
		s.push("1");
		assertFalse(s.isEmpty());
	}
}

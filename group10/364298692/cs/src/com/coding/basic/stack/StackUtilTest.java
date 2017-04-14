package com.coding.basic.stack;

import static org.junit.Assert.fail;

import com.coding.basic.stack.Stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class StackUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddToBottom() {
		Stack<Integer> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		
		System.out.println(s.toString());
		
		System.out.println("=================");
		
		StackUtil.addToBottom(s, 4);
		
		System.out.println(s.toString());
	}
	@Test
	public void testReverse() {
		
	}

	@Test
	public void testRemove() {
		
	}

	@Test
	public void testGetTop() {
		
	}

	@Test
	public void testIsValidPairs() {
		
	}

}

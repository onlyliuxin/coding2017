package com.pan.alg;

import java.util.Stack;

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
		
		StackUtil.addToBottom(s, 0);
		
		Assert.assertEquals("[0, 1, 2, 3]", s.toString());
		
	}
	@Test
	public void testReverse() {
		Stack<Integer> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		Assert.assertEquals("[1, 2, 3, 4, 5]", s.toString());
		StackUtil.reverse(s);
		Assert.assertEquals("[5, 4, 3, 2, 1]", s.toString());
	}

	@Test
	public void testRemove() {
		Stack<Integer> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		StackUtil.remove(s, 2);
		Assert.assertEquals("[1, 3]", s.toString());
	}

	@Test
	public void testGetTop() {
		Stack<Integer> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		{
			Object[] values = StackUtil.getTop(s, 3);
			Assert.assertEquals(5, values[0]);
			Assert.assertEquals(4, values[1]);
			Assert.assertEquals(3, values[2]);
		}
	}

	@Test
	public void testIsValidPairs() {
		Assert.assertTrue(StackUtil.isValidPairs("([e{d}f])"));
		Assert.assertFalse(StackUtil.isValidPairs("([b{x]y})"));
	}

}

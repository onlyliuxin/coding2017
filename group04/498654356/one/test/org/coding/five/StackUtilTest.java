package org.coding.five;

import org.coding.one.Stack;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StackUtilTest {
	Stack s ;
	
	@Before
	public void setUp() throws Exception {
		s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
	}

	@After
	public void tearDown() throws Exception {
		s = null;
	}

	@Test
	public void testReverse() {
		StackUtil.reverse(s);
		Assert.assertEquals(5, s.size());
		Assert.assertEquals(1, s.pop());
		Assert.assertEquals(2, s.pop());
		Assert.assertEquals(3, s.pop());
		Assert.assertEquals(4, s.pop());
		Assert.assertEquals(5, s.pop());
	}
	@Test
	public void testReverse2() {
		StackUtil.reverse2(s);
		Assert.assertEquals(5, s.size());
		Assert.assertEquals(1, s.pop());
		Assert.assertEquals(2, s.pop());
		Assert.assertEquals(3, s.pop());
		Assert.assertEquals(4, s.pop());
		Assert.assertEquals(5, s.pop());
	}
	@Test
	public void testRemove() {
		StackUtil.remove(s, 1);
		Assert.assertEquals(4, s.size());
		Assert.assertEquals(5, s.pop());
		Assert.assertEquals(4, s.pop());
		Assert.assertEquals(3, s.pop());
		Assert.assertEquals(2, s.pop());
		Assert.assertEquals(0, s.size());
	}

	@Test
	public void testGetTop() {
		Object[] top = StackUtil.getTop(s, 2);
		Assert.assertEquals(5, top[0]);
		Assert.assertEquals(4, top[1]);
		
		Assert.assertEquals(5, s.size());
		Assert.assertEquals(5, s.pop());
		Assert.assertEquals(4, s.pop());
		Assert.assertEquals(3, s.pop());
		Assert.assertEquals(2, s.pop());
		Assert.assertEquals(1, s.pop());
		Assert.assertEquals(0, s.size());
	}

	@Test
	public void testIsValidPairs() {
		String s = "([e{d}f])";
		boolean v = StackUtil.isValidPairs(s);
		Assert.assertEquals(true, v);
		s = "([b{x]y})";
		v = StackUtil.isValidPairs(s);
		Assert.assertEquals(false, v);
		
	}

}

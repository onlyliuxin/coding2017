package com.ralf.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReverse() {

		MyStack<Integer> stack = new MyStack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		Assert.assertEquals(5, stack.size());

		StackUtil.reverse(stack);
		//Assert.assertEquals(5, stack.size());

		Assert.assertEquals(1, stack.pop().intValue());
		Assert.assertEquals(2, stack.pop().intValue());
		Assert.assertEquals(3, stack.pop().intValue());
		Assert.assertEquals(4, stack.pop().intValue());
		Assert.assertEquals(5, stack.pop().intValue());
	}

	@Test
	public void testRemove() {

		MyStack<Integer> stack = new MyStack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		Assert.assertEquals(5, stack.size());

		StackUtil.remove(stack, 3);

		Assert.assertEquals(4, stack.size());

		Assert.assertEquals(5, stack.pop().intValue());
		Assert.assertEquals(4, stack.pop().intValue());
		Assert.assertEquals(2, stack.pop().intValue());
		Assert.assertEquals(1, stack.pop().intValue());
	}

	public void testGetTop() {

		MyStack<Integer> stack = new MyStack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		Assert.assertEquals(5, stack.size());

		Integer[] integerReal = StackUtil.getTop(stack, 3);
		int[] intExpeted = { 1, 2, 3 };
		int[] intReal = new int[integerReal.length];
		for (int i = 0; i < integerReal.length; i++) {
			intReal[i] = integerReal[i];
		}
		Assert.assertEquals(5, stack.size());
		Assert.assertArrayEquals(intExpeted, intReal);

	}
	
	@Test
	public void testIsValidPair(){
		
		String stringTrue = "([e{d}f])";
		String stringFalse = "([b{x]y})";
		
		Assert.assertTrue(StackUtil.isValidPairs(stringTrue));
		Assert.assertFalse(StackUtil.isValidPairs(stringFalse));
		
	}

}

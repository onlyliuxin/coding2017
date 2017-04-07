package com.coding.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.stack.Stack;
import com.coding.basic.stack.StackUtil;

public class StackUtilTest {

	Stack stack;
	
	private void initStack() {
		stack = new Stack();
		stack.push(new Integer(1));
		stack.push(new Integer(2));
		stack.push(new Integer(3));
		stack.push(new Integer(4));
		stack.push(new Integer(5));
	}
	
	private String printElements() {
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.reverse();
		
		return sb.toString();
	}
	
	@Before
	public void setUp() throws Exception {
		initStack();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStackUtil() {
		StackUtil.reverse(stack);
		Assert.assertEquals("5,4,3,2,1", printElements());
	}
	
	@Test
	public void testRemove() {
		StackUtil.remove(stack, new Integer(1));
		Assert.assertEquals("2,3,4,5", printElements());
	}
	
	@Test
	public void testGetTop() {
		Assert.assertArrayEquals(StackUtil.getTop(stack, 3), new Integer[]{5,4,3});
		Assert.assertEquals("1,2,3,4,5", printElements());
	}

	@Test
	public void testIsValidPairs() {
		Assert.assertEquals(true, StackUtil.isValidPairs("([e{d}f])"));
		Assert.assertEquals(false, StackUtil.isValidPairs("([b{x]y})"));
	}
}

package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackUtilTest {
	Stack stack ;
	{
		stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
	}
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverse() {
		StackUtil.reverse(stack);
		Assert.assertEquals("[5,4,3,2,1]", stack.toString());
	}
	@Test
	public void testRemove() {
		StackUtil.remove(stack, 3);
		Assert.assertEquals("[1,2,4,5]", stack.toString());
	}
	@Test
	public void testGetTop(){
		Assert.assertEquals(new Object[]{5,4}, StackUtil.getTop(stack, 2));
		Assert.assertEquals("[1,2,3,4,5]", stack.toString());
	}
}

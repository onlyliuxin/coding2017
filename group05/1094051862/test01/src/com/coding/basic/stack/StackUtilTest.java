package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class StackUtilTest {

	@Test
	public void testReverse() {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.toString());
		StackUtil.reverse(stack);
		System.out.println(stack.toString());
	}

	@Test
	public void testRemove() {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.toString());
		StackUtil.remove(stack, 5);
		System.out.println(stack.toString());
		StackUtil.remove(stack, 2);
		System.out.println(stack.toString());
	}

	@Test
	public void testGetTop() {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.toString());
		Object[] top = StackUtil.getTop(stack, 3);
		System.out.println(ArrayUtils.toString(top, null));
		System.out.println(stack.toString());
	}

	@Test
	public void testIsValidPairs() {
		String str = "sdf{sdf[sdf]sdfsdff}";
		String str2 = "[sdf(sdf{sdf]}sdf)";
		Assert.assertTrue(StackUtil.isValidPairs(str));
		Assert.assertTrue(!StackUtil.isValidPairs(str2));
	}

}

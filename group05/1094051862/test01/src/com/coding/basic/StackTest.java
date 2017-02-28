package com.coding.basic;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class StackTest {

	@Test
	public void test() {
		Stack stack = new Stack();
		for (int i = 0; i < 100; i++) {
			stack.push(i);
		}
		Assert.assertEquals(100, stack.size());
		Assert.assertEquals(99, stack.pop());
		for (int i = 98; i >= 0; i--) {
			Assert.assertEquals(i, stack.peek());
			Assert.assertEquals(i, stack.pop());
		}
		Assert.assertEquals(0, stack.size());
	}

}

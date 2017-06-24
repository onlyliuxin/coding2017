package com.coding.basic.stack;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TwoStackInOneArrayTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		TwoStackInOneArray stack = new TwoStackInOneArray(10);
		stack.push1(1);
		stack.push1(2);
		stack.push1(3);
		stack.push1(4);
		stack.push1(5);
		
		stack.push2(1);
		stack.push2(2);
		stack.push2(3);
		stack.push2(4);
		stack.push2(5);
		
		for(int i=1;i<=5;i++){
			Assert.assertEquals(stack.peek1(), stack.peek2());
			Assert.assertEquals(stack.pop1(), stack.pop2());
		}
		
		
	}
	@Test
	public void test2() {
		TwoStackInOneArray stack = new TwoStackInOneArray(5);
		stack.push1(1);
		stack.push1(2);
		stack.push1(3);
		stack.push1(4);
		stack.push1(5);
		stack.push1(6);
		stack.push1(7);	
		
		stack.push2(1);
		stack.push2(2);
		stack.push2(3);
		stack.push2(4);
		
		
		Assert.assertEquals("[1, 2, 3, 4, 5, 6, 7]",Arrays.toString(stack.stack1ToArray()));
		Assert.assertEquals("[1, 2, 3, 4]",Arrays.toString(stack.stack2ToArray()));
	}
	
}

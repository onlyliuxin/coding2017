package com.coding.basic.stack;

import static org.junit.Assert.*;

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
	public void test() {
		TwoStackInOneArray twoStack = new TwoStackInOneArray();
		Assert.assertTrue(twoStack.isEmpty1());
		Assert.assertTrue(twoStack.isEmpty2());
		
		twoStack.push1(1);
		twoStack.push1(2);
		twoStack.push1(3);
		twoStack.push1(4);
		twoStack.push1(5);
		
		twoStack.push2(6);
		twoStack.push2(7);
		twoStack.push2(8);
		twoStack.push2(9);
		twoStack.push2(10);
		twoStack.push2(11);
		
		twoStack.push1(12);
		twoStack.push1(13);
		twoStack.push1(14);
		twoStack.push1(15);
		
		Assert.assertEquals(15,twoStack.pop1());
		Assert.assertEquals(14,twoStack.pop1());
		Assert.assertEquals(13,twoStack.pop1());
		Assert.assertEquals(12,twoStack.pop1());
		Assert.assertEquals(5,twoStack.pop1());
		Assert.assertEquals(4,twoStack.pop1());
		Assert.assertEquals(3,twoStack.pop1());
		Assert.assertEquals(2,twoStack.pop1());
		Assert.assertEquals(1,twoStack.pop1());
		
		Assert.assertEquals(11,twoStack.pop2());
		Assert.assertEquals(10,twoStack.pop2());
		Assert.assertEquals(9,twoStack.pop2());
		Assert.assertEquals(8,twoStack.pop2());
		Assert.assertEquals(7,twoStack.pop2());
		Assert.assertEquals(6,twoStack.pop2());
		
		Assert.assertTrue(twoStack.isEmpty1());
		Assert.assertTrue(twoStack.isEmpty2());
	}

}

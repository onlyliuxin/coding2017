package com.coding.basic.stack;

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
		TwoStackInOneArray ts = new TwoStackInOneArray();
		ts.push1(1);
		ts.push1(2);
		ts.push2(11);
		ts.push2(12);
		Assert.assertEquals(2, ts.pop1());
		Assert.assertEquals(12, ts.pop2());
		ts.push1(3);
		ts.push2(13);
		Assert.assertEquals(3, ts.pop1());
		Assert.assertEquals(13, ts.pop2());
		Assert.assertEquals(1, ts.pop1());
		Assert.assertEquals(11, ts.pop2());
		ts.push1(4);
		ts.push1(5);
		ts.push1(6);
		ts.push1(7);
		ts.push1(8);
		ts.push1(9);
		ts.push2(14);
		ts.push2(15);
		ts.push2(16);
		ts.push2(17);
		ts.push2(18);
		ts.push2(19);
		Assert.assertEquals(9, ts.peek1());
		Assert.assertEquals(19, ts.peek2());
		Assert.assertEquals(6, ts.size1());
		Assert.assertEquals(6, ts.size2());
	}

}

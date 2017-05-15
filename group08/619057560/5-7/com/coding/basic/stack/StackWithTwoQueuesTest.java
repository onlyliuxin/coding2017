package com.coding.basic.stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackWithTwoQueuesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		StackWithTwoQueues s = new StackWithTwoQueues();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		Assert.assertEquals(4, s.pop());
		Assert.assertEquals(3, s.pop());
		s.push(5);
		Assert.assertEquals(5, s.pop());
		Assert.assertEquals(2, s.pop());
		Assert.assertEquals(1, s.pop());
	}

}

package com.coding.basic.stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuickMinStackTest {

	QuickMinStack s;
	
	@Before
	public void setUp() throws Exception {
		s = new QuickMinStack();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		s.push(3); // 3
		Assert.assertEquals(3, s.findMin());
		s.push(2); // 3, 2
		Assert.assertEquals(2, s.findMin());
		s.push(1); // 3, 2, 1
		Assert.assertEquals(1, s.findMin());
		s.pop(); // 3, 2
		Assert.assertEquals(2, s.findMin());
		s.push(4); // 3, 2, 4
		Assert.assertEquals(2, s.findMin());
	}

}

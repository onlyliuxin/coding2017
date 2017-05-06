package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackWithTwoQueuesTest {

	StackWithTwoQueues stq = null;
	@Before
	public void setUp() throws Exception {
		stq = new StackWithTwoQueues();
	}

	@Test
	public void testPush() {
		stq.push(1);
		stq.push(2);
		stq.dump();
		assertEquals(2, stq.pop());
		assertEquals(1, stq.pop());
		stq.push(4);
		stq.dump();
		assertEquals(4, stq.pop());
	}

}

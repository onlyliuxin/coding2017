package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuickMinStackTest {

	QuickMinStack qms = null;
	@Before
	public void setUp() throws Exception {
		qms = new QuickMinStack();
	}

	@Test
	public void testPush() {
		qms.push(1);
		qms.push(2);
		assertEquals("1 2 ", qms.toString());
	}

	@Test
	public void testPop() {
		qms.push(1);
		qms.push(2);
		assertEquals(2, qms.pop());
		assertEquals(1, qms.pop());
	}

	@Test
	public void testFindMin() {
		qms.push(3);
		qms.push(2);
		qms.push(1);
		assertEquals(1, qms.findMin());
		qms.pop();
		assertEquals(2, qms.findMin());
		qms.pop();
	}

}

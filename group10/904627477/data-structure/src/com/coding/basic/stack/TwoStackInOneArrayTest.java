package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwoStackInOneArrayTest {
	
	private TwoStackInOneArray stack;

	@Before
	public void setUp() throws Exception {
		stack = new TwoStackInOneArray();
	}

	@After
	public void tearDown() throws Exception {
		stack = null;
	}

	@Test
	public void testPush1() {
		stack.push1(1);
		stack.push1(2);
		stack.push1(3);
		assertEquals("1,2,3,null,null", stack.toString());
	}

	@Test
	public void testPop1() {
		stack.push1(1);
		stack.push1(2);
		stack.push1(3);
		assertEquals(3, stack.pop1());
		assertEquals("1,2,null,null,null", stack.toString());
	}

	@Test
	public void testPeek1() {
		stack.push1(1);
		stack.push1(2);
		stack.push1(3);
		assertEquals(3, stack.peek1());
		assertEquals("1,2,3,null,null", stack.toString());
	}

	@Test
	public void testPush2() {
		stack.push2(1);
		stack.push2(2);
		stack.push2(3);
		assertEquals("null,null,3,2,1", stack.toString());
	}

	@Test
	public void testPop2() {
		stack.push2(1);
		stack.push2(2);
		stack.push2(3);
		assertEquals(3, stack.pop2());
		assertEquals("null,null,null,2,1", stack.toString());
	}

	@Test
	public void testPeek2() {
		stack.push2(1);
		stack.push2(2);
		stack.push2(3);
		assertEquals(3, stack.peek2());
		assertEquals("null,null,3,2,1", stack.toString());
	}

	@Test
	public void testTwo(){
		stack.push1(1);
		stack.push2(2);
		stack.push1(3);
		stack.push1(4);
		stack.push2(5);
		stack.push1(6);
		assertEquals("1,3,4,6,null,null,null,null,5,2", stack.toString());
		assertEquals(6, stack.pop1());
		assertEquals(4, stack.pop1());
		assertEquals(5, stack.pop2());
		assertEquals("1,3,null,null,2", stack.toString());
		stack.push2(7);
		assertEquals("1,3,null,7,2", stack.toString());
		stack.push2(8);
		assertEquals("1,3,null,null,null,null,null,8,7,2", stack.toString());
	}
	
}

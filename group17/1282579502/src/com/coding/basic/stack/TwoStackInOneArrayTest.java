package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TwoStackInOneArrayTest {

	TwoStackInOneArray ts = null;
	@Before
	public void setUp() throws Exception {
		ts = new TwoStackInOneArray();
	}

//	@Test
//	public void testPush1() {
//		ts.push1(1);
//		ts.dump();
//		
//	}
//
//	@Test
//	public void testPop1() {
//		ts.push1(1);
//		Object item = ts.pop1();
//		ts.dump();
//		assertEquals(1, item);
//	}
//
//	@Test
//	public void testPeek1() {
//		ts.push1(1);
//		ts.push1(2);
//		ts.dump();
//		assertEquals(2, ts.pop1());
//	}
	
	
	
	
	
//	@Test
//	public void testPush2() {
//		ts.push2(1);
//		ts.dump();
//		
//	}
//
//	@Test
//	public void testPop2() {
//		ts.push2(1);
//		Object item = ts.pop2();
//		ts.dump();
//		assertEquals(1, item);
//	}
//
//	@Test
//	public void testPeek2() {
//		ts.push2(1);
//		ts.push2(2);
//		ts.dump();
//		assertEquals(2, ts.pop2());
//	}
	
	@Test
	public void expendTest(){
		ts.push1(1);
		ts.push1(2);
		ts.push2(11);
		ts.push2(14);
		//ts.dump();
		
		ts.push1(4);
		//ts.dump();
		ts.push1(5);
		ts.dump();
	}

	@Test
	public void emptyTest(){
		ts.push1(1);
		ts.pop1();
		assertEquals(null, ts.pop1());
	}
}

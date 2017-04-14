package com.coding.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Stack;
import com.coding.util.StackUtil;

public class StackUtilTest {
	
	private Stack s;

	@Before
	public void setUp() throws Exception {
		s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
	}

	@After
	public void tearDown() throws Exception {
		s = null;
	}

	@Test
	public void testReverse() {
		StackUtil.reverse(s);
		assertEquals("1,2,3,4,5", s.toString());
	}
	
	@Test
	public void testReverse1() {
		StackUtil.reverse1(s);
		assertEquals("1,2,3,4,5", s.toString());
	}
	
	@Test
	public void testReverse2() {
		StackUtil.reverse2(s);
		assertEquals("1,2,3,4,5", s.toString());
	}

	@Test
	public void testRemove() {
		StackUtil.remove(s, 3);
		assertEquals("5,4,2,1", s.toString());
	}

	@Test
	public void testGetTop() {
		Object[] objs = StackUtil.getTop(s, 2);
		assertEquals("5,4", arrayToString(objs));
	}

	@Test
	public void testIsValidPairs() {
		String s1 = "([e{d}f])";
		String s2 = "([b{x]y})";
		assertEquals(true, StackUtil.isValidPairs(s1));
		assertEquals(false, StackUtil.isValidPairs(s2));
	}
	
	public String arrayToString(Object[] objs){
		StringBuffer sb = new StringBuffer();
		for (Object obj : objs) {
			if(sb.length()!=0){
				sb.append(",");
			}
			sb.append(obj);
		}
		return sb.toString();
	}

}

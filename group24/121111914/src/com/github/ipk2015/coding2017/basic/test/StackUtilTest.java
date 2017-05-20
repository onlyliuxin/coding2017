package com.github.ipk2015.coding2017.basic.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.ipk2015.coding2017.basic.stack.Stack;
import com.github.ipk2015.coding2017.basic.stack.StackUtil;

public class StackUtilTest {
	Stack stack=null;
	@Before
	public void setUp() throws Exception {
		stack=new Stack();
	}

	@Test
	public void testReverse() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		StackUtil.reverse(stack);
		assertEquals("4,3,2,1,", stack.toString());
	}

	@Test
	public void testRemove() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		StackUtil.remove(stack, 2);
		assertEquals("1,3,4,", stack.toString());
		StackUtil.remove(stack, 5);
		assertEquals("1,3,4,", stack.toString());
	}

	@Test
	public void testGetTop() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		Object[] array=StackUtil.getTop(stack, 3);
		assertEquals("1,2,3,4,", stack.toString());
		StringBuffer buffer=new StringBuffer();
		for(int i=0;i<array.length;i++){
			buffer.append(array[i]+",");
		}
		assertEquals("2,3,4,", buffer.toString());
	}

	@Test
	public void testIsValidPairs() {
		String s="([a{b}c])";
		boolean result=StackUtil.isValidPairs(s);
		assertEquals(true, result);
		s="[a]{[(b)]}{(c)}";
		result=StackUtil.isValidPairs(s);
		assertEquals(true, result);
		s="([a{b]c})";
		result=StackUtil.isValidPairs(s);
		assertEquals(false, result);
		s="{[{a}]b]";
		result=StackUtil.isValidPairs(s);
		assertEquals(false, result);
		s="{(a)}b(][)";
		result=StackUtil.isValidPairs(s);
		assertEquals(false, result);
	}
}

package com.coding.basic.stack;

import static org.junit.Assert.fail;

import com.coding.basic.stack.Stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class StackUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddToBottom() {
		Stack<Integer> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		
		System.out.println(s.toString());
		
		System.out.println("=================");
		
		StackUtil.addToBottom(s, 4);
		
		System.out.println(s.toString());
	}
	@Test
	public void testReverse() {
		Stack<Integer> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		
		System.out.println(s.toString());
		
		System.out.println("=================");
		
		StackUtil.reverse(s);
		
		System.out.println(s.toString());
	}

	@Test
	public void testRemove() {
		Stack<Object> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		System.out.println(s.toString());
		
		System.out.println("=================");
		
		Object removeNum = new Integer(2);
		StackUtil.remove(s, removeNum);
		
		System.out.println(s.toString());
	}
	
	@Test
	public void testGetTop() {
		Stack<Object> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		System.out.println(s.toString());
		
		System.out.println("=================");
		
		Object[] result = StackUtil.getTop(s, 4);
		
		System.out.println(s.toString());
		for(int i=0; i<result.length; i++){
			System.out.println(result[i]);
		}
		
	}

	@Test
	public void testGetBottom() {
		Stack<Object> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		System.out.println(s.toString());
		
		System.out.println("=================");
		
		Object[] result = StackUtil.getBottom(s, 4);
		
		System.out.println(s.toString());
		for(int i=0; i<result.length; i++){
			System.out.println(result[i]);
		}
		
	}

	@Test
	public void testIsValidPairs() {
		String s = "p[fe]wf{wawea}few";
		boolean isValidPairs = StackUtil.isValidPairs(s);
		System.out.println(isValidPairs);
	}

}

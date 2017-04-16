package com.coding.basic.stack;

import static org.junit.Assert.*;
import java.util.Stack;
import org.junit.Assert;
import org.junit.Test;

public class StackUtilTest {
	
	@Test
	public void testReverse() {
		Stack<Integer> s = new Stack<>();
		StackUtil.reverse(s);
		Assert.assertEquals("[]", s.toString());
		
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		
		StackUtil.reverse(s);
		Assert.assertEquals("[1,2,3,4,5]", StackUtil.toString(s));	
	}
	@Test
	public void testRemove() {
		Stack<Object> s = new Stack<>();
		StackUtil.remove(s,3);
		Assert.assertEquals("[]", s.toString());
		{
			s.push(1);
			s.push(2);
			s.push(3);
			s.push(4);
			s.push(5);
		
			StackUtil.remove(s,3);
			Assert.assertEquals("[5,4,2,1]", StackUtil.toString(s));
		}
		{
			s.push(1);
			s.push(2);
			s.push(3);
			s.push(4);
			s.push(5);
		
			StackUtil.remove(s,7);
			Assert.assertEquals("[5,4,3,2,1]", StackUtil.toString(s));
		}
	}
	@Test
	public void testGetTop() {
		Stack<Object> s = new Stack<>();
		assertArrayEquals(new Object[0], StackUtil.getTop(s,3));	
		
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
			
		int[] expected = {5,4,3};
		Object[] result = StackUtil.getTop(s,3);
		int[] actual = new int[result.length];
		for(int i = 0; i < result.length; i++){
			actual[i] = (int)result[i];
		}

		assertArrayEquals(expected, actual);		
	}
	
	@Test
	public void testIsValidPairs(){
		String s1 = "([e{d}f])";
		assertTrue(StackUtil.isValidPairs(s1));
		
		String s2 = "([b{x]y})";
		assertFalse(StackUtil.isValidPairs(s2));
	}
}



package com.vvv.basic.stack;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackUtilTest {
	private Stack stack;
	
	@Before
	public void setUp() throws Exception {
		stack = new Stack();
		stack.push(new Integer(1));
		stack.push(new Integer(2));
		stack.push(new Integer(3));
		stack.push(new Integer(4));
		stack.push(new Integer(5));
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void reverseTest() {
		StackUtil.reverse(stack);
		Assert.assertEquals("5,4,3,2,1", stackToString(stack));		
	}
	
	@Test
	public void removeTest() {
		StackUtil.remove(stack,new Integer(2));
		StackUtil.remove(stack,new Integer(1));
		StackUtil.remove(stack,new Integer(5));
		Assert.assertEquals("3,4", stackToString(stack));	
	}
	
	@Test
	public void getTopTest() {
		Assert.assertArrayEquals(new Integer[]{5,4,3}, StackUtil.getTop(stack,3));
		Assert.assertEquals("1,2,3,4,5",stackToString(stack));
	}
	
	@Test
	public void isValidPairsTest() {
		Assert.assertEquals(false, StackUtil.isValidPairs("([b{x]y})"));
		Assert.assertEquals(true ,StackUtil.isValidPairs("([e{d}f])"));
	}
	
	private String stackToString(Stack s) {
		StringBuilder buffer = new StringBuilder();
		while (!s.isEmpty()) {
			buffer.append(s.pop());
			if (s.size() > 0) {
				buffer.append(",");
			}
		}
		String str  = buffer.reverse().toString();
		System.out.println(str);
		return str;
	}
	
}

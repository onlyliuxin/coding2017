package com.easy.util.mystack;

import org.junit.Assert;
import org.junit.Test;

public class TestStack {
	
	@Test
	public void test_push_object(){
		Stack stack =new Stack();
		stack.push("aa");
		stack.push("bb");
		stack.push("cc");
		Assert.assertEquals("[aa,bb,cc]", stack.toString());
		Assert.assertEquals("cc", stack.peek());
		Assert.assertEquals(3, stack.size());
	}
	
	@Test
	public void test_pop(){
		Stack stack =new Stack();
		stack.push("aa");
		stack.push("bb");
		stack.push("cc");
		Assert.assertEquals("cc", stack.pop());
		Assert.assertEquals("bb", stack.pop());
		Assert.assertEquals("aa", stack.pop());
		Assert.assertEquals(0, stack.size());
	}
	
}

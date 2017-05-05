package test;

import org.junit.Assert;
import org.junit.Test;

import basic.Stack;

public class StackTest {
	
	@Test
	public void test1(){
		Stack stack = new Stack();
		
		Assert.assertEquals(true, stack.isEmpty());
		
		stack.push(123);
		stack.push("qwe");
		stack.push(456);
		
		Assert.assertEquals(false, stack.isEmpty());
		
		int size = stack.size();
		Assert.assertEquals(3, size);
		
		Object peek = stack.peek();
		Assert.assertEquals(456, peek);
		
		Object pop = stack.pop();
		Assert.assertEquals(456, pop);
	}

}

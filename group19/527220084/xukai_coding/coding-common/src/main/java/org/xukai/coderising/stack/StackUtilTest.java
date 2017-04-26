package org.xukai.coderising.stack;

import org.junit.Assert;
import org.junit.Test;

public class StackUtilTest {
	
	
	@Test
	public void testReverse(){
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.display();
		StackUtil.reverse(stack);
		stack.display();
	}


	@Test
	public void testRemove(){
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.display();
		StackUtil.remove(stack,3);
		stack.display();
	}


	@Test
	public void testGetTop(){
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.display();
		Object[] objects = StackUtil.getTop(stack, 8);
		for (int i = 0; i < objects.length; i++) {
			System.out.println(objects[i]);
		}
		Assert.assertEquals(5,objects.length);
	}

	@Test
	public void testIsValidPairs(){
		Assert.assertTrue(StackUtil.isValidPairs("[d(a)](da){21}"));
		Assert.assertTrue(!StackUtil.isValidPairs("[d(a{)}](da){21}"));
	}
	
	
}

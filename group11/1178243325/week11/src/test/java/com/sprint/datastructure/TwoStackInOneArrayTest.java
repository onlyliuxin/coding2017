package com.sprint.datastructure;

import org.junit.Assert;
import org.junit.Test;
public class TwoStackInOneArrayTest {
	
	TwoStackInOneArray stack = new TwoStackInOneArray();
	@Test
	public void testStack() {
		//测试前栈push , pop 并扩荣
		for (int i = 0; i < 20; i++) {
			System.out.println(i);
			stack.push1(i);
		}
		Assert.assertEquals(0, stack.peek1());
		Assert.assertEquals(21, stack.capacity);
		stack.push2(0);
		stack.push2(1);
		stack.push2(2);
		stack.push2(3);
		System.out.println("Stack的容量:" + stack.capacity);
		
		Assert.assertEquals(19, stack.pop1());
		Assert.assertEquals(3, stack.pop2());
		
		Assert.assertEquals(0, stack.peek2());
	}

	@Test
	public void testRearStack() {
		//原来不同的＠Test里面会会重新初始化stack.
	}

}

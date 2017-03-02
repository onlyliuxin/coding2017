package test.com.pxshuo.basic.impl;

import org.junit.Assert;
import org.junit.Test;

import com.pxshuo.basic.impl.Queue;
import com.pxshuo.basic.impl.Stack;

public class StackTest {
	public Stack object = new Stack();
	
	@Test
	public void enQueueTest() {
		Assert.assertEquals(true, object.isEmpty());
		object.push("hello");
		object.push("world");
		Assert.assertEquals(false, object.isEmpty());
		Assert.assertEquals(2, object.size());
		Assert.assertEquals("world", object.peek());
		Assert.assertEquals("world", object.pop());
		Assert.assertEquals("hello", object.pop());
		Assert.assertEquals(0, object.size());
	}

}

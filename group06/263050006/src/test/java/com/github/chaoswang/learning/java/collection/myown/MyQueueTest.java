package com.github.chaoswang.learning.java.collection.myown;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MyQueueTest {

	@Rule  
	public ExpectedException thrown= ExpectedException.none(); 
	
	@Test
	public void testAdd(){
		MyQueue<String> myQueue = new MyQueue<String>(3);
		//加3个元素
		myQueue.add("1");
		myQueue.add("2");
		myQueue.offer("3");
		//继续添加返回false
		Assert.assertFalse(myQueue.offer("4"));
		//读取
		Assert.assertEquals("1", myQueue.element());
		Assert.assertEquals("1", myQueue.peek());
		//开始移除
		Assert.assertEquals("1", myQueue.remove());
		Assert.assertEquals("2", myQueue.remove());
		Assert.assertEquals("3", myQueue.poll());
		//继续移除返回false
		Assert.assertNull(myQueue.poll());
	}
	
	@Test
	public void testAddWhenQueueIsFull(){
		thrown.expect(IllegalStateException.class);
		MyQueue<String> myQueue = new MyQueue<String>(3);
		myQueue.add("1");
		myQueue.add("2");
		myQueue.add("3");
		//继续添加抛异常
		myQueue.add("4");
	}
	
	@Test
	public void testRemoveWhenQueueIsEmpty(){
		thrown.expect(NoSuchElementException.class);
		MyQueue<String> myQueue = new MyQueue<String>(3);
		myQueue.add("1");
		myQueue.remove();
		//继续移除抛异常
		myQueue.remove();
	}
}

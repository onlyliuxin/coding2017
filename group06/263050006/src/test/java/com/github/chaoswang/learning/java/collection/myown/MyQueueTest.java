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
		//��3��Ԫ��
		myQueue.add("1");
		myQueue.add("2");
		myQueue.offer("3");
		//������ӷ���false
		Assert.assertFalse(myQueue.offer("4"));
		//��ȡ
		Assert.assertEquals("1", myQueue.element());
		Assert.assertEquals("1", myQueue.peek());
		//��ʼ�Ƴ�
		Assert.assertEquals("1", myQueue.remove());
		Assert.assertEquals("2", myQueue.remove());
		Assert.assertEquals("3", myQueue.poll());
		//�����Ƴ�����false
		Assert.assertNull(myQueue.poll());
	}
	
	@Test
	public void testAddWhenQueueIsFull(){
		thrown.expect(IllegalStateException.class);
		MyQueue<String> myQueue = new MyQueue<String>(3);
		myQueue.add("1");
		myQueue.add("2");
		myQueue.add("3");
		//����������쳣
		myQueue.add("4");
	}
	
	@Test
	public void testRemoveWhenQueueIsEmpty(){
		thrown.expect(NoSuchElementException.class);
		MyQueue<String> myQueue = new MyQueue<String>(3);
		myQueue.add("1");
		myQueue.remove();
		//�����Ƴ����쳣
		myQueue.remove();
	}
}

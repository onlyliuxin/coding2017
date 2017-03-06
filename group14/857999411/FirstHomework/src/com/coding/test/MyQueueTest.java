package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.MyQueue;

public class MyQueueTest {

	@Test
	public void test() {
		MyQueue mq =new MyQueue();
		mq.enQueue("java001");
		mq.enQueue("java002");
		mq.enQueue("java003");
		mq.enQueue("java004");
		mq.deQueue();
		mq.deQueue();
		
		System.out.println(mq.isEmpty());
		System.out.println(mq.size());
	}

}

package com.coding.basic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.Queue;

public class QueueTest {

	@Test
	public void test() {
		Queue<String> qu = new Queue<String>();
		qu.enQueue("gong");
		qu.enQueue("bo");
		qu.enQueue("jie");
		System.out.println(qu.size());
		qu.deQueue();
		System.out.println(qu.size());
		System.out.println(qu.isEmpty());
		qu.enQueue("gong");
		qu.enQueue("bo");
		qu.deQueue();
		qu.enQueue("jie");
		System.out.println(qu.size());
	}

}

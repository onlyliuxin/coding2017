package com.test;

import org.junit.Test;

import com.mycoding.Queue;

public class QueueTest {

	
	@Test
	public void Test1() {
		Queue que = new Queue();
		for(int i=0;i<10;i++) {
			que.enQueue(i);
		}
		System.out.println(que);
		System.out.println(que.isEmpty());
		System.out.println(que.size());
		
		//程序有点问题，队列为空时输出“]”
		for(int i=0;i<10;i++) {
			que.deQueue();
		}
		System.out.println(que);
		System.out.println(que.isEmpty());
		System.out.println(que.size());
	}
}

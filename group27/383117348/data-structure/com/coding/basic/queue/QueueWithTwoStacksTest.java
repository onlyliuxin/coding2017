package com.coding.basic.queue;

import org.junit.Test;

public class QueueWithTwoStacksTest {
	
	
	
	@Test
	public void testenQueueAndDequeue(){
		int[] ints = new int[]{2,5,10,32,41};
		QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<Integer>();
		for(int i=0;i<ints.length;i++){
			queue.enQueue(ints[i]);
		}
		while(!queue.isEmpty()){
			System.out.print(queue.deQueue()+" ");
		}
		
	}
	
	
}

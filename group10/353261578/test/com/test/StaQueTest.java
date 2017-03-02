package com.test;


import org.junit.Test;

import com.sx.structures.MyQueue;
import com.sx.structures.MyStack;

public class StaQueTest {
	private MyStack s;
	private MyQueue queue;

	@Test
	public void Stacktest() {
		
		s = new MyStack();
		
		for(int i=0;i<10;i++){
			s.push(i);
		}
		System.out.println("\npop:");
		while(s.isEmpty()==false){
			System.out.println("-"+s.isEmpty()+":"+s.pop());
		}
		
		System.out.println("\n"+"-"+s.isEmpty()+":"+s.pop());
		
		System.out.println("\npeek");
		for(int i=1;i<3;i++){
		System.out.print(s.peek()+" ");
		}
	}
	
	@Test
	public void queueTest(){
		queue = new MyQueue();
		for(int i=0;i<10;i++)
			queue.enQueue(i);
		while(queue.size()>0)
			System.out.print(queue.deQueue()+" ");
	}
	

}

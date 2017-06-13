package com.skomefen.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.skomefen.list.ArrayList;
import com.skomefen.list.Iterator;
import com.skomefen.list.LinkedList;
import com.skomefen.list.Queue;
import com.skomefen.list.Stack;






public class test {

	@Test
	public void arrayListTest() {
		ArrayList array = new ArrayList();
		for(int i=0;i<200;i++){
			array.add(""+i);
		}
		System.out.println(array);
		for(int i=0;i<200;i++){
			array.add(i,""+(i+1));
		}
		System.out.println(array);

		array = new ArrayList();
		array.add(1);
		array.add(3);
		array.add(4);

		array.add(1,2);
		System.out.println(array);
		System.out.println(array.get(2));
		System.out.println(array.size());

		System.out.println(array.remove(2));
		System.out.println(array);
		
		Iterator iterator = array.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
	
	@Test
	public	void StackTest(){
		Stack s = new Stack();
		System.out.println(s.isEmpty());
		s.push("1");
		s.push("2");
		s.push("3");
		System.out.println(s.peek());		
		System.out.println(s.size());

		System.out.println(s.pop());
		System.out.println(s.peek());
		System.out.println(s.size());
		System.out.println(s.isEmpty());
		

	}
	
	@Test
	public void QueueTest(){
		Queue queue = new Queue();
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		
		assertEquals(false,queue.isEmpty());
		assertEquals(3, queue.size());
		assertEquals(1,queue.deQueue());
		assertEquals(2,queue.deQueue());
		assertEquals(1, queue.size());
		assertEquals(3,queue.deQueue());
		assertEquals(true,queue.isEmpty());

	}
	
	@Test
	public void LinkedListTest(){
		LinkedList link = new LinkedList();
		for(int i=0;i<10;i++){
			link.add(""+i);
		}
		for(int i=0;i<10;i++){
			System.out.println(link.get(i));
		}
		System.out.println(link);
		System.out.println(link.remove(0));
		System.out.println(link.remove(1));
		Iterator iterator = link.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next()+",");
		}
		link.add(0,1.1);
		link.add(1,1.2);
		System.out.println(link);
		
		
		
	}
}

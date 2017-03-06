package com.coding.basic;
import com.coding.basic.*;
public class Queue {
	private LinkedList list;
	
	public Queue(){
		list = new LinkedList();
	}
	/*入队*/
	public void enQueue(Object o){
		list.addLast(o);
	}
	
	/*出队*/
	public Object deQueue(){
		if (isEmpty()){
			System.out.println("队空");
		}
		return list.removeFirst();
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public int size(){
		return list.size();
	}
}

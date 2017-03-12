/*
 * Queue:单向队列
 * 
 */

package com.firsthomework;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


@SuppressWarnings("all")
public class MyQueue {
	private LinkedList ll =new LinkedList();
	
	public void addLast(Object o){
		ll.addLast(o);
	}
	
	public Object removeFirst(){
		return ll.removeFirst();
	}
	public int size(){
		return ll.size();
	}
	
	public boolean empty(){
		return ll.size()==0;
	}
	
	public String toString(){
		return ll.toString();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyQueue e =new MyQueue();
		System.out.println(e.empty());
		e.addLast("hello");
		e.addLast("world");
		e.addLast("welcome");
		e.addLast("java");
		System.out.println(e.size());
		e.removeFirst();
		System.out.println(e.size());
		System.out.println(e);
		
	}

}

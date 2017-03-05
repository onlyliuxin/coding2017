package com.team6.week1;

public class Queue {
	private Node first;
	private int index;
	
	class Node{
		Object data;
		Node next;
		
		
		Node(Object data){
			this.data = data;
		}
	
		public void add(Object data){
			if(this.next == null){
				this.next = new Node(data);
			}else{
				this.next.add(data);
			}
		}
		

		public void traversal(){
			if(this.next != null){
				index++;
				this.next.traversal();
			}
		}
	}
	
	public void enQueue(Object o){		
		if(first != null){
			first.add(o);
		}
	}
	
	public Object deQueue(){
		if(first != null){
			Object obj = first.data;
			first = first.next;
			return obj;
		}
		return null;
	}
	
	public boolean isEmpty(){
		if(first == null){
			return true;
		}else{
			return false;
		}
	}
	
	public int size(){
		if(first != null){
			first.traversal();
		}
		return index;
	}
}

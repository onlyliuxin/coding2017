package com.coding.basic.first.impl;

import com.coding.basic.first.List;

/**
 * 链表
 * @author zap
 *	LinkedList
 */
public class LinkedList implements List {
	
	
	
	private Node head = new Node();
	private int size ;//
	
	private static class Node{
		Object data;
		Node next;
		
		public Node() {
		}
		
		public Node(Object data) {
			this.data = data;
		}
		
	}
	

	@Override
	public void add(Object o) {
		
		if(size==0){
			Node node = new Node(o);
			head = node;
//			head = tail = node;
		}else{
			Node current = getCurrentNode(size);
			Node node = new Node(o);
			current.next = node;
//			tail = node;
		}
		size ++;
		
	
	}

	@Override
	public void add(int index, Object o) {
		judgeIndexRangge(index);
		if(size == 0 ){
			Node node = new Node(o);
			head = node;
//			head = tail = node;
		}else if(index == 0){
			Node node = new Node(o);
			node.next = head;
			head = node;
		}else{
			Node prev = getCurrentNode(index);
			Node temp = prev.next; 
			Node node = new Node(o);
			node.next = temp;
			prev.next = node;
		}
		size ++;
		
	}

	@Override
	public Object get(int index) {
		judgeGetIndexRangge(index);
		Node current = getCurrentNode(index + 1);
		return current.data;
	}

	@Override
	public Object remove(int index) {
		judgeIndexRangge(index);//下标
		Object obj = null;
		if(index == 0){
			Node node = head.next;
			obj = head.data;
			head = node;
		}else{
			Node prev = getCurrentNode(index);
			Node temp = prev.next;
			Node after  =temp.next ;
			prev.next = after;
			obj = temp.data;
		}
		size -- ;
		return obj;
	}

	@Override
	public int size() {
		return size;
	}
	
	 
	
	private void judgeIndexRangge(int index){
		 
		 if (index > size || index < 0)
	            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	 }
	
	private void judgeGetIndexRangge(int index){
		
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}
	 
	 private String outOfBoundsMsg(int index) {
	        return "Index: "+index+", Size: "+size;
	  }
	 
	 private Node getCurrentNode(int index){
		 
		 Node temp = head;
		 Node prev = head;
		 for(int i = 0 ;i < index;i++){//找到该个元素
				prev = temp;//
				temp = temp.next;//
		}
		return prev;
		 
	 }
	 
	 
	 public void addLast(Object o){
		 add(o);
	 }
	 
	 public Object removeFirst(){
		 Object o = remove(0);
		 return o;
	 }
	
	
}

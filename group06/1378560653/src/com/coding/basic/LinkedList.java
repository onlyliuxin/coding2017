package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	public void add(Object o){
		if(null == head){
			head = new Node(o);
			head.next = null;
		}else{
			Node pt = head;
			while(head.next != null){
				pt = pt.next;
			}
			pt.next = new Node(o);
			pt.next.next =null;
		}
		size++;	
	}
	
	public void add(int index , Object o){
		if(index < size){
			Node pt = head;
			for(int i = 0; i < index-1; i++){
				pt = pt.next;
			}
			Node pt1 = pt.next.next; 
			pt.next = new Node(o);
			pt.next.next = pt1;
			size ++;
		}
	}
	public Object get(int index){
		if(index < size){
			Node pt = head;
			for(int i = 0; i < index; i++){
				pt = pt.next;
			}
			return pt.data;
		}else{
			return null;
		}
	}
	public Object remove(int index){
		if(index < size){
			Node pt = head;
			for(int i = 0; i< index -1;i++){
				pt = pt.next;
			}
			Node pt1 = pt.next;
			pt.next = pt1.next;
			return pt1.data;
		}else{
			return null;
		}
	}
	
	public int size(){
		if(null == head){
			size = 0;
		}else{
			Node pt = head;
			while(pt.next != null){
				size++;
			}
		}
		return size;
	}
	
	public void addFirst(Object o){
		if(null == head){
			head = new Node(o);
			head.next = null;
		}else{
			Node pt = new Node(o);
			pt.next = head;
			head = pt;
		}
		size++;
	}
	public void addLast(Object o){
		if(null == head){
			head = new Node(o);
			head.next = null;
		}else{
			Node pt = head;
			while(pt.next != null){
				pt = pt.next;
			}
			pt.next = new Node(o);
			Node pt1 = pt.next;
			pt1.next = null;
		}
		size++;
	}
	public Object removeFirst(){
		if(null != head){
			Node pt = head;
			head = pt.next;
			size--;
			return head.data;
		}else{ 
			return null;
		}
	}
	public Object removeLast(){
		if(null != head){
			Node pt = head;
			while(pt.next.next != null){
				pt = pt.next;
			}
			Node pt1 = pt.next;
			pt.next = null;
			size--;
			return pt1.data;
		}else{
			return null;
		}
	}
	
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	
	private class LinkedListIterator implements Iterator {
		LinkedList  linkedlist = null;
		int pos = 0; 
		
		private LinkedListIterator(LinkedList linkedlist) {
			this.linkedlist = linkedlist;
		}
		@Override
		public boolean hasNext() {
			pos++;
			if(pos >= size){
				return false;
			}else{
				return true;
			}
		}

		@Override
		public Object next() {
			Node pt = head;
			for(int i = 0; i < pos; i++ ){
				while(pt.next != null){
					pt = pt.next;
				}
			}
			return pt.data;
		}
		
	}
	
	private static  class Node{
		public Node(Object o) {
			this.data = o;
		}
		Object data;
		Node next;	
	}
}

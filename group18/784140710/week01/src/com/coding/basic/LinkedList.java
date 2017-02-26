package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size = 0;
	
	public void add(Object o){
		++size;
		Node node = new Node(o);
		
		if(head == null){
			head = node;
			return;
		}
		Node tmp = new Node(o);
		tmp = head;
		while(tmp.next!=null){
			tmp = tmp.next;
		}
		tmp.next = node;
	}
	public void add(int index , Object o){
		++size;
		Node node = new Node(o);
		Node tmp = new Node(o);
		tmp = head;
		int i =0;
		while(tmp.next!=null && i<index-1){
			tmp = tmp.next;
			i++;
		}
		node.next = tmp.next;
		tmp.next = node;
		
	}
	public Object get(int index){
		
		if(head == null){
			return null;
		}
		Node tmp = head;
		int i =0;
		while(tmp.next!=null && i<index){
			tmp = tmp.next;
			i++;
		}
		return tmp;
	}
	public Object remove(int index){
		--size;
		Node node = null;
		if(head == null){
			return null;
		}
		Node tmp = head;
		int i = 0;
		while(tmp.next!=null && i<index-1){
			tmp = tmp.next;
			i++;
		}
		node = tmp.next;
		tmp.next = node.next;
		
		return node;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		++size;
		Node node = new Node(o);
		node.next = head;
		head = node;
	}
	public void addLast(Object o){
		add(o);
	}
	public Object removeFirst(){
		--size;
		if(head == null){
			return null;
		}
		Node tmp = head;
		head = head.next;
		return tmp;
	}
	public Object removeLast(){
		--size;
		Node tmp = head;
		Node node = null;
		while(tmp.next!=null){
			node = tmp;
			tmp = tmp.next;
		}
		
		node.next = null;
		return tmp;
	}
	public Iterator iterator(){
		return new ArrayIterator(this);
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		public Node(Object data){
			this.data = data;
		}
	}
}

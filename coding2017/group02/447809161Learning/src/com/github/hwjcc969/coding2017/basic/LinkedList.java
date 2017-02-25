package com.github.hwjcc969.coding2017.basic;

public class LinkedList implements List {
	
	private Node head;
	private int size;
	
	public void add(Object o){
		Node newNode = new Node(o);
		if (head == null) {
			head = newNode;
		}
		else{
			Node last = head.next;
			while (last != null){
				last = last.next;
			}
			last = newNode;
		}
		size++;

	}
	public void add(int index , Object o){
		Node dummy = new Node();
		dummy.next = head;
		Node pre = dummy;
		while (index > 0) {
			pre = pre.next;
		}
		Node cur  = new Node(o);
		cur.next = pre.next;
		pre.next = cur;
		size++;
	}
	public Object get(int index){
		Node cur = head;
		while (index > 0 && cur != null) {
			cur = cur.next;
		}
		return cur;
	}
	public Object remove(int index){
		Node dummy = new Node();
		dummy.next = head;
		Node pre = dummy;
		while (index > 0) {
			pre = pre.next;
		}
		Node cur = pre.next;
		Node next = pre.next.next;
		pre.next = next;
		size--;
		return cur;

	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node firstNode = new Node(o);
		firstNode.next = head;
		head = firstNode;
	}
	public void addLast(Object o){
		Node pre = new Node();
		while (pre.next != null){
			pre = pre.next;
		}
		Node lastNode = new Node(o);
		pre.next = lastNode;
	}
	public Object removeFirst(){
		Node dummy = new Node();
		dummy.next = head;
		dummy.next = head.next;
		return dummy.next;
	}
	public Object removeLast(){
		Node pre = new Node();
		while (pre.next.next != null) {
			pre = pre.next;
		}
		Node lastNode = pre.next.next;
		pre.next = null;
		return lastNode;
		
	}
	public Iterator iterator(){
		return null;
	}
	
	
	private static  class Node{
		public Node(Object o) {
			// TODO Auto-generated constructor stub
		}
		public Node() {
			// TODO Auto-generated constructor stub
		}
		Object data;
		Node next;
		
	}


	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (size() == 0){
			return false;
		}
		return true;
	}


}

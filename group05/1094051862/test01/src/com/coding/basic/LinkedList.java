package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	private Node last;
	private int size = 0;
	
	public void add(Object o){
		if (head == null) {
			head = new Node(o, null);
			size ++;
			return;
		}
		Node n = new Node(o, null);
		if (last == null) {
			last = n;
			head.next = last;
		}
		last.next = n;
		last = n;
		size ++;
	}
	public void add(int index , Object o){
		if (index < 0 || index > size) {
			System.out.println("linkedList.add: index < 0 || index > size");
			return;
		}
		if (index == size) {
			add(o);
			return;
		}
		if (index == 0) {
			addFirst(o);
			return;
		}
		Node pre = head;
		for (int i = 1; i < index; i++) {
			pre = pre.next;
		}
		Node post = pre.next;
		Node n = new Node(o,post);
		pre.next = n;
		size ++;
	}
	public Object get(int index){
		if (index == 0) {
			return head.data;
		}
		Node n = head;
		for (int i = 1; i <= index; i++) {
			n = n.next;
		}
		return n.data;
	}
	public Object remove(int index){
		if (index < 0 || index >= size) {
			System.out.println("remove :index < 0 || index >= size");
			return null;
		}
		if (index == 0) {
			return removeFirst();
		}
		if (index == size - 1) {
			return removeLast();
		}
		Node pre = head;
		for (int i = 1; i < index; i++) {
			pre = pre.next;
		}
		Node n = pre.next;
		Node post = n.next;
		n.next = null;
		pre.next = post;
		size --;
		return n.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node n = new Node(o,head);
		head = n;
		size ++;
		return;
	}
	public void addLast(Object o){
		Node n = new Node(o,null);
		last.next = n;
		last = n;
		size ++;
		return;
	}
	public Object removeFirst(){
		Object o = head.data;
		Node n = head.next;
		head.next = null;
		head = n;
		size --;
		return o;
	}
	public Object removeLast(){
		Node preLast = head;
		for (int i = 1; i < size; i++) {
			preLast = preLast.next;
		}
		preLast.next = null;
		Object o = last.data;
		last = preLast;
		size --;
		return o;
	}
	public Iterator iterator(){
		return new Iterator() {
			int cusor = 0;
			Node current = head;
			@Override
			public Object next() {
				if (!hasNext()) {
					System.out.println("next : !hasNext");
					return null;
				}
				Object o = current.data;
				current = current.next;
				cusor ++;
				return o;
			}
			
			@Override
			public boolean hasNext() {
				return cusor < size;
			}
		};
	}
	
	
	private static class Node {
		
		Object data;
		Node next;
		
		public Node (Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
}

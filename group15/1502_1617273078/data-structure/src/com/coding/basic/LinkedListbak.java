package com.coding.basic;

public class LinkedListbak implements List {
	private int thesize;
	private Node head;
	public void add(Object o){
		if (head == null) {
			head = new Node();
			head.data = o;
			head.next = null;
			thesize++;
		} else {
			addLast(o);
		}
	}
	public void add(int index , Object o){
		if (index > thesize) {
			throw new IndexOutOfBoundsException();
		} else if (index == thesize) {
			addLast(o);
		} else if(index<thesize){
			Node x = head;
			int i=0;
			do {
				x = x.next;
				i++;
			} while (i == index);
			Node old = x;
			Node newindex = new Node();
			newindex.data = o;
			newindex.next = old;
			thesize++;
		}
		
	}
	public Object get(int index){
		if (index >= thesize) {
			throw new IndexOutOfBoundsException();
		} else{
			Node x = head;
			int i=0;
			do {
				x = x.next;
				i++;
			} while (i == index);
			return x.data;
		}
	}
	public Object remove(int index){
		Node x = head;
		for (int i = 1; i <index ; i++) {
			x = x.next;
		}
		Node fus = x.next.next;
		Node fu = x.next;
		x.next = fus;
		Object s = fu.data;
		fu = null;
		thesize--;
		return s;
	}
	
	public int size(){
		return thesize;
	}
	
	public void addFirst(Object o){
		Node oldfirst = head;
		head = new Node();
		head.data = o;
		head.next = oldfirst;
		thesize++;
	}
	public void addLast(Object o){
		if (head == null) {
			add(o);
		}
		if (thesize == 1) {
			Node s = new Node();
			s.data = o;
			head.next = s;
			s.next = null;
			thesize++;
		} else {
			Node x = head;
/*		do {
			x = x.next;
		} while (x!=null);*/
			for (int i = 1; i <size() ; i++) {
				x = x.next;
			}

			Node oldlast=x;
			x = new Node();
			x.data = o;
			x.next = null;
			oldlast.next = x;
			thesize++;
		}

	}
	public Object removeFirst(){
		head = head.next;
		thesize--;
		return head.data;

	}
	public Object removeLast(){
		Node x = head;
/*		while (x != null) {
			x = x.next;
		}
		do {
			x = x.next;
		} while (x != null);*/
		for (int i = 1; i <thesize; i++) {
			x = x.next;
		}
		Node fu = new Node();
		fu.data = x.data;
		x = null;
		thesize--;
		return fu.data;
	}
	public Iterator iterator(){

		return new Iterator() {
			Node x = head;
			@Override
			public boolean hasNext() {
				x = x.next;
				return x!=null;
			}

			@Override
			public Object next() {
				x = x.next;
				return x.data;
			}
		};
	}
	
	
	private static  class Node{
		Object data;
		Node next;

/*		public Node(Object object,Node next) {
			this.data = object;
			this.next = next;
		}

		public Node(Object o) {
			this.data = o;
		}

		public Node() {

		}*/
	}
}

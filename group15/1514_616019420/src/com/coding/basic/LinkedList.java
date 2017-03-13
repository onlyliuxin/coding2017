package com.coding.basic;

public class LinkedList implements List {

	private Node head;
	private int size;

	public void add(Object o) {
		Node n = new Node();
		n.data = o;
		if (head != null) {
			n.next = head;
			size++;
		} else {
			size = 1;
		}
		head = n;
	}

	public void add(int index, Object o) {

		Node n1 = head;
		Node n2 = new Node();
		for (int i = size - 1; i >= index; i--) {
			if (i == index) {
				n2.next = n1.next;
				n2.data = 0;
				n1.next = n2;
			} else {
				n1 = n1.next;
			}

		}

		size++;
	}

	public Object get(int index) {
		Node n1 = head;
		Object o = null;
		for (int i = size - 1; i >= index; i--) {
			n1 = n1.next;
			if (i == index) {
				o = n1.data;
			}

		}

		return o;
	}

	public Object remove(int index) {
		Node n1 = head;
		Node n2 = new Node();
		Node n3 = new Node();
		Object o = null;
		for (int i = size - 1; i >= index; i--) {
			if (i == index + 1) {
				n2 = n1.next;
			} else if (i == index) {
				n3 = n2.next;
				o = n3.data;
				n1 = n3.next;
			} else {
				n1 = n1.next;
			}

		}
		n2.next = n1;
		size--;
		return o;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node n=new Node();
		Node n1=head;

		for(int i=size-1;i>=0;i--)
		{
			n1=n1.next;
			if(i==0){
				n=n1.next;
				n.data=o;
			}
			
		}
	}

	public void addLast(Object o) {
		Node n=new Node();
		n.data=o;
		n.next=head;
		head=n;
		
	}

	public Object removeFirst() {
		Object o= new Object();
		Node n1=head;

		for(int i=size-1;i>=0;i--)
		{
			n1=n1.next;
		 if(i==1){
			o=n1.next.data;
			n1.next=null;
			}
			
		}
		return o;
	}

	public Object removeLast() {
		Object o= new Object();
		Node n=head;
		head=n.next;
		o=n.data;
		n.next=null;
		return o;
	}

	public Iterator iterator() {
		
		return null;
	}

	private static class Node {
		Object data;
		Node next;

	}
}

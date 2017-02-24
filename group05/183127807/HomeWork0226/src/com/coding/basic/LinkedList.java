package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;

	private int size ;

	private Node current = head;
	public LinkedList() {
		head = null;
		size = 0;
	}
	
	public void add(Object o){
		Node newNode = new Node();
		newNode.data = o;
		if (current.next == null)
		{
			current.next = newNode;
		}

		while (current.next != null){
			current = current.next;
		}
		current.next = newNode;
		size++;

	}
	public void add(int index , Object o){

		Node newNode = new Node();
		newNode.data = o;
		 if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < index - 2; i++) {

			current = current.next;
		}

		newNode.next = current.next;
		current.next = newNode;
		size++;
	}
	public Object get(int index){


		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return head;
		}
		for (int i = 0;i<index - 1; i++) {
			current = current.next;
		}

		return current;
	}

	public Object remove(int index){
		for (int i = 1; i <= index; i++) {

			if (i == index - 1) {
				current.next = current.next.next;
				size--;
			} else {
				current = current.next;
			}

		}
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node newHead = new Node();
		newHead.data = o;
		newHead.next = head;
		head = newHead;
		size++;
	}
	public void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;
		while (current.next != null){
			current = current.next;
		}
		current.next = newNode;
		size++;
	}
	public Object removeFirst(){
		Node removeHead = head;
		head.next = head.next.next;
		size--;
		return removeHead;
	}
	public Object removeLast(){
		Node theNext = current.next;
        Node oldLast;
		while(theNext.next != null) {
			current = theNext;
			theNext = theNext.next;
		}
		oldLast=current.next;
		current.next = theNext.next;

		size--;
		return oldLast;
	}
	public Iterator iterator(){
		return new LinkedListIterator();
	}

	public Object head() {
		return head;
	}
	private class LinkedListIterator implements Iterator {
		@Override
		public boolean hasNext() {
			return current.next!=null;
		}

		@Override
		public Object next() {
			Node data = (Node) current.data;
			current.next = current.next.next;
			return data;
		}
	}

	
	private static  class Node{
		Object data;

		Node next;
		
	}
}

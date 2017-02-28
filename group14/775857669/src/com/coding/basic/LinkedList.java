package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	
	private int size = 0;
	
	private Node head;
	
	public LinkedList() {
		head = new Node();
	}
	
	public void add(Object o){
		Node temp = head;
		for (int i=0 ; i<size ; i++){
			temp = temp.next;
		}
		temp.next = new Node(o, null);
		size++;
	}
	
	public void add(int index , Object o){
		checkRangeForAdd(index);
		Node before = head;
		Node after = null;
		for (int i=0 ; i <index ; i++){
			before = before.next;
		}
		after = before.next;
		Node newNode = new Node(o, after);
		before.next = newNode;
		size++;
	}

	public Object get(int index){
		checkRange(index);
		Node tmp = head.next;
		for (int i=0 ; i<index ; i++) {
			tmp = tmp.next;
		}
		return tmp.data;
	}
	
	public Object remove(int index){
		Node before = head;
		for(int i=0 ; i<index ; i++) {
			before = before.next;
		}
		Node after = before.next;
		Node target = before.next;
		before.next = after;
		size--;
		return target.data;
	}
	
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		Node after = head.next;
		Node newNode = new Node(o, after);
		head.next = newNode;
		size++;
	}
	public void addLast(Object o){
		Node tail = head;
		for(int i=0 ; i<size ; i++){
			tail = tail.next;
		}
		tail.next = new Node(o, null);
		size++;
	}
	public Object removeFirst(){
		if (size < 1) {
			throw new ArrayIndexOutOfBoundsException(0);
		}
		Node first = head.next;
		Node second = first.next;
		head.next = second;
		size--;
		return first.data;
	}
	public Object removeLast(){
		if (size == 0)
			throw new NoSuchElementException();
		Node beforeLast = head;
		for (int i=0 ; i<size-1 ; i++){
			beforeLast = beforeLast.next;
		}
		Node tail = beforeLast.next;
		beforeLast.next = null;
		size--;
		return tail.data;
	}
	public Iterator iterator(){
		return new Iterator() {
			Node index = head;
			@Override
			public Object next() {
				index = index.next;
				return index.data;
			}
			
			@Override
			public boolean hasNext() {
				return index.next!=null;
			}
		};
	}
	
	private void checkRangeForAdd(int index) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}
	
	private void checkRange(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}
	
	private static  class Node{
		public Node() {
		}
		
		public Node(Object data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}

		private Object data;
		private Node next;
		
	}
}

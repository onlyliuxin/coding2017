package com.coding.basic;

public class LinkedList implements List {
	
	private Node head;
	
	public void add(Object o) {
		if(head == null) {
			head = new Node();
			head.data = o;
		}
		else {
			Node newNode = new Node();
			newNode.data = o;
			Node lastNode = head;
			while(head.next != null) {
				lastNode = head.next;
			}
			lastNode.next = newNode;
		}
	}
	
	public void add(int index , Object o) {
		if(index >= this.size())
			throw new IndexOutOfBoundsException("Index out of bound");
		
		Node newNode = new Node();
		newNode.data = o;
		if(index == 0) {
			newNode.next = this.head;
			this.head = newNode;
		}
		else if(index == this.size()) {
			Node curNode = this.head;
			while(curNode.next != null){
				curNode = curNode.next;
			}
			curNode.next = newNode;
		}
		else {
			Node beforeNode = this.head;
			Node afterNode = null;
			for(int i = 1; i < index; i++) {
				beforeNode = head.next;
			}
			afterNode = beforeNode.next;
			newNode.next = afterNode;
			beforeNode.next = newNode;
		}
	}
	
	public Object get(int index){
		Node retNode = this.head;
		
		if(index < 0){
			throw new IndexOutOfBoundsException("Index out of bound");
		}
		
		if(index != 0) {
			for(int i = 0; i < index; i++) {
				retNode = retNode.next;
			}
		}
		
		return retNode.data;
	}
	
	public Object remove(int index){
		Node beforeNode = null;
		Node afterNode = null;
		Node removedNode = null;
		if(index == 0) {
			removedNode = this.head;
			this.head = this.head.next;
		}
		else {
			for(int i = 1; i < index; i++) {
				beforeNode = head.next;
			}
			removedNode = beforeNode.next;
			afterNode = removedNode.next;
			beforeNode.next = afterNode;
		}
		

		return removedNode.data;
	}
	
	public int size(){
		int i = 0;
		if(this.head == null)
			return 0;
		
		Node curNode = this.head;
		while(curNode != null){
			curNode = curNode.next;
			i++;
		}
		return i;
	}
	
	public void addFirst(Object o){
		Node firstNode = new Node();
		firstNode.data = o;
		firstNode.next = this.head;
		this.head = firstNode;
	}
	
	public void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;
		if(this.size() == 0){
			this.head = newNode;
		}
		
		Node curNode = this.head;
		while(curNode.next != null) {
			curNode = curNode.next;
		}
		curNode.next = newNode;
	}
	
	public Object removeFirst() {
		Node retNode = this.head;
		this.head = this.head.next;
		
		return retNode;
	}
	
	public Object removeLast() {
		Node curNode = null;
		if(this.size() == 0) {
			curNode = null;
		}
		else if(this.size() == 1) {
			curNode = this.head;
			this.head = null;
			return curNode;
		}
		else {
			Node beforeNode = this.head;
			for (int i = 1; i < this.size() - 1; i++) {
				beforeNode = beforeNode.next;
			}
			curNode = beforeNode.next;
			beforeNode.next = null;
		}
		
		return curNode;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	private class LinkedListIterator implements Iterator {

		private Node curNode = head;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return this.curNode.next != null;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return this.curNode.next;
		}
		
		
		
	}
	
	
	private static  class Node{
		Object data;
		Node next;
		
	}
}

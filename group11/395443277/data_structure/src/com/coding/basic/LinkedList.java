package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {
	private Node head;
	
	public void add(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;
		
		if (head == null) {
			head = newNode;
		} else {
			Node curr = head;
			while(curr.next != null) {
				curr = curr.next;
			}
			curr.next = newNode;
		}
	}
	public void add(int index , Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;
		
		if (head == null) {
			head = newNode;
		} else {
			Node curr = head;
			Node prev = curr;
			while(index >0 && curr.next != null) {
				prev = curr;
				curr = curr.next;
				index--;
			}
			
			prev.next = newNode;
			newNode.next = curr;
		}
	}
	public Object get(int index){
		Node curr = head;
		while(index > 0) {
			curr = curr.next;
			index--;
		}	
		return curr.data;
	}
	public Object remove(int index){
		if (index ==0) {
			return this.removeFirst();
		}
		
		Node curr = head;
		Node prev = curr;
		while(index >0 && curr.next != null) {
			prev = curr;
			curr = curr.next;
			index--;
		}
		
		Object target = curr.data;
		prev.next = curr.next;
		curr.next = null;
		
		return target;
	}
	public int size(){
		int size = 0;
		Node curr = head;
		while(curr != null) {
			size++;
			curr = curr.next;
		}
		return size;
	}
	public void addFirst(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;
		
		if (head == null) {
			head = newNode;
		} else {
			newNode.next = head.next;
			head.next = newNode;
		}
	}
	public void addLast(Object o){
		Node newNode = new Node();
		newNode.data = o;
		newNode.next = null;
		
		Node curr = head;
		if (head == null) {
			head = newNode;
		} else {
			while(curr.next != null) {
				curr = curr.next;
			}
			curr.next = newNode;
		}
	}
	public Object removeFirst(){
		if (head == null) {
			return null;
		}
		
		Object target = head.data;
		head = head.next;
		return target;
	}
	public Object removeLast(){
		if (head == null) {
			return null;
		}
		Node curr = head;
		Node prev = curr;
		while(curr.next != null) {
			prev = curr;
			curr = curr.next;
		}
		Object target = curr.data;
		prev.next = null;
		return target;
	}
	public Iterator iterator(){
		return new SeqIterator();
	}
	
	private class SeqIterator implements Iterator {
		Node curr = head;
		
		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public Object next() {
			if (!hasNext()) throw new NoSuchElementException();
			Object target = curr.data;
			curr = curr.next;
			return target;
		}

	}
	
	private static class Node{
		Object data;
		Node next;
		
	}
}

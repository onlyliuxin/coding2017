package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private Node head;
	private int size = 0;

	public void add(Object o) {
		if (isEmpty()) {
			head = new Node(o);
		} else {
			Node tail = (Node)get(size-1);
			Node node = new Node(o);
			tail.next = node;
		}
		size++;
	}

	public boolean isEmpty() {
		return (size == 0) ? true : false;
	}

	public void add(int index, Object o) {
		rangeCheck(index);
		if (index ==0) {
			Node node = new Node(o);
			node.next = head;
			head = node;
		} else {
			Node pre = (Node)get(index-1);
			Node node = new Node(o);
			node.next = pre.next;
			pre.next = node;
		}
	}
	
	private void rangeCheck(int index){
		if (index >= size || index <0){
			throw new IndexOutOfBoundsException(); 
		}
	}
	
	public Object get(int index) {
		rangeCheck(index);
		Node dest = head;
		for (int i = 0; i< index; i++){
			dest = dest.next;
		}
		return dest.data;
	}

	public Object remove(int index) {
		rangeCheck(index);
		Node pre = (Node)get(index);
		Node dest = pre.next;
		pre.next = dest.next;
		size --;
		return dest;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node node = new Node(o);
		node.next = head;
		head = node;
		size ++;
	}

	public void addLast(Object o) {
		Node last = (Node)get(size-1);
		Node node = new Node(o);
		last.next = node;
		size++;
	}

	public Object removeFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node newhead = head;
		head = head.next;
		size --;
		return newhead;
	}

	public Object removeLast() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		if (head.next == null) {
			Node tmp = head;
			head = null;
			size --;
			return tmp;
		}
		Node newLastNode = (Node)get(size-2);
		Node oldLastNode = newLastNode.next;
		newLastNode.next = null;
		size --;
		return oldLastNode;
	}

	public Iterator iterator() {
		return null;
	}

	private static class Node {
		Object data;
		Node next;

		Node(Object data) {
			this.data = data;
			next = null;
		}
	}

}

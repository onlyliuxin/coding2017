package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private Node head;
	private Node tail;
	private int size;

	private static class Node {
		Object data;
		Node next;
		Node previous;
	}

	public void add(Object o) {
		if (head == null) {
			head = new Node();
			head.data = o;
			tail = head;
		} else {
			Node oldtail = tail;
			tail = new Node();
			tail.data = o;
			tail.next = null;
			tail.previous = oldtail;
			oldtail.next = tail;
		}
		size++;
	}

	public void add(int index, Object o) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("数组越界异常");
		} else if (index == size) {
			Node oldtail = tail;
			tail = new Node();
			tail.data = o;
			tail.previous = oldtail;
			oldtail.next = tail;
			size++;
		} else {

		}
		size++;
	}

	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("数组越界异常");
		}
		for (int i = 0; i < index; i++) {
			head = head.next;
		}
		return head.data;
	}

	public Object remove(int index) {

		return null;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node oldhead = head;
		head = new Node();
		head.data = o;
		head.next = oldhead;
		head.previous = null;
		oldhead.previous = head;
		size++;
	}

	public void addLast(Object o) {
		Node oldtail = tail;
		tail = new Node();
		tail.data = o;
		tail.previous = oldtail;
		tail.next = null;
		oldtail.next = tail;
		size++;
	}

	public Object removeFirst() {
		Object data = head.data;
		head = head.next;
		head.previous = null;
		size--;
		return data;
	}

	public Object removeLast() {
		Object data = tail.data;
		tail = tail.previous;
		tail.next = null;
		size--;
		return data;
	}

	public Iterator iterator() {

		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator {
		int cursor;
		int lastReset = -1;

		@Override
		public boolean hasNext() {
			return size != cursor;
		}

		@Override
		public Object next() {
			int i = cursor;
			if (i > size) {
				throw new NoSuchElementException();
			}
			Node n = head;
			for (int j = 0; j < i; j++) {
				n = n.next;
			}
			cursor = i + 1;
			lastReset = i;
			return n.data;
		}
	}
}
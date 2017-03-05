package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private Node head;
	private int size;

	public void add(Object o) {

		Node newHead = new Node(o, null);
		Node f = head;
		for (int i = 0; i < size - 1; i++) {
			f = f.next;
		}
		f.next = newHead;
		size++;

	}

	public void add(int index, Object o) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node newHead = new Node(o, null);
		Node f = head;
		for (int i = 0; i < index - 1; i++) {
			f = f.next;
		}
		newHead.next = f.next;
		f.next = newHead;
		size++;
	}

	public Object get(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node f = head;
		for (int i = 0; i < index; i++) {
			f = f.next;
		}
		return f.data;
	}

	public Object remove(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node f = head;
		for (int i = 0; i < index - 1; i++) {
			f = f.next;
		}
		f.next = f.next.next;
		final Node d = f.next;
		final Object element = d.data;
		d.data = null;
		d.next = null;
		size--;
		return element;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node newHead = new Node(o, head);
		head = newHead;
		size++;
	}

	public void addLast(Object o) {
		Node newHead = new Node(o, null);
		Node f = head;
		for (int i = 0; i < size - 1; i++) {
			f = f.next;
		}
		f.next = newHead;
		size++;
	}

	public Object removeFirst() {
		final Node f = head;
		if (f == null)
			throw new NoSuchElementException();
		final Object element = f.data;
		head = f.next;
		f.data = null;
		f.next = null;
		size--;
		return element;
	}

	public Object removeLast() {
		Node f = head;
		for (int i = 0; i < size - 2; i++) {
			f = f.next;
		}
		Object element = f.next;
		f.next = null;
		size--;
		return element;
	}

	public Iterator iterator() {
		return null;
	}

	private static class Node {
		Object data;
		Node next;

		private Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
}

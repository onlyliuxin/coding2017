package com.coding.basic;

public class LinkedList implements List {

	private Node head;
	private int size;

	public LinkedList() {

	}

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("越界！");
		}
		if (head == null) {
			head = new Node();
			head.data = o;
		} else {

			Node n = getNodebyIndex(index);

			Node newnode = new Node();
			newnode.data = o;
			newnode.next = n.next;
			n.next = newnode;
		}
		size++;
	}

	public Object get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("越界！");
		}
		if (head == null)
			return null;
		Node n = getNodebyIndex(index);
		return n.data;
	}

	public Object remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("越界！");
		}
		Node n = getNodebyIndex(index - 1);
		Object o = n.next.data;
		n.next = n.next.next;
		size--;
		return o;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		if (head == null) {
			head = new Node();
			head.data = o;
		} else {
			Node newnode = new Node();
			newnode.data = o;
			newnode.next = head;
			head = newnode;
		}
		size++;
	}

	public void addLast(Object o) {
		if (head == null) {
			head = new Node();
			head.data = o;
		} else {
			Node n = getNodebyIndex(size);
			Node newnode = new Node();
			newnode.data = o;
			n.next = newnode;
		}
		size++;
	}

	public Object removeFirst() {
		if (head == null)
			return null;

		head = head.next;
		size--;
		return head.data;
	}

	public Object removeLast() {
		if (head == null)
			return null;
		Node n = getNodebyIndex(size - 1);
		Object o = n.next.data;
		n.next = null;
		size--;
		return o;
	}

	public Iterator iterator() {
		return null;
	}

	private Node getNodebyIndex(int index) {
		int i = 0;
		Node n = head;
		while (i < index) {
			n = n.next;
			i++;
		}
		return n;
	}

	private static class Node {
		Object data;
		Node next;

		Object Getdata() {
			return data;
		}

		void Setdata(Object o) {
			data = o;
		}

		Node Getnext() {
			return next;
		}

		void Setnext(Node n) {
			next = n;
		}
	}
}


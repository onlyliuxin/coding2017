package com.coding.basic;

public class LinkedList implements List {

	private int size = 0;
	// 头结点
	private Node head;
	// 尾结点
	private Node tail;

	private void rangeCheck(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}

	public void add(Object o) {
		Node node = new Node(o, null);
		if (head == null) {
			head = tail = node;
		}
		Node oldTail = tail;
		tail = node;
		oldTail.next = tail;
		size++;
	}

	public void add(int index, Object o) {
		rangeCheck(index);
		if (index == size) {
			this.add(o);
		} else {
			// 保存index处节点
			Node x = head;
			// 保存index-1处的节点
			Node y = null;
			for (int i = 0; i < index; i++) {
				y = x;
				x = x.next;
			}
			Node node = new Node(o, x);
			y.next = node;
			size++;
		}
	}

	public Object get(int index) {
		rangeCheck(index);
		Node x = head;
		for (int i = 0; i < index; i++) {
			x = x.next;
		}
		return x.data;
	}

	public Object remove(int index) {
		rangeCheck(index);
		Object removeData;
		if (index == 0) {
			removeData = removeFirst();
		} else if (index == size - 1) {
			removeData = removeLast();
		} else {
			Node x = head;
			Node y = head;
			for (int i = 0; i < index; i++) {
				y = x;
				x = x.next;
			}
			y.next = x.next;
			size--;
			removeData = x.data;
		}
		return removeData;
	}

	public int size() {
		return this.size;
	}

	public void addFirst(Object o) {
		Node oldHead = head;
		head = new Node(o, oldHead);
		size++;
	}

	public void addLast(Object o) {
		Node oldTail = tail;
		tail = new Node(o, null);
		oldTail.next = tail;
		size++;
	}

	public Object removeFirst() {
		Node oldHead = head;
		head = oldHead.next;
		size--;
		return oldHead.data;
	}

	public Object removeLast() {
		Node oldTail = tail;
		Node temp = head;
		for (int i = 0; i < size - 2; i++) {
			temp = temp.next;
		}
		tail = temp;
		size--;
		return oldTail.data;
	}

	public Iterator iterator() {
		return new LinkedListIterator();
	}

	private static class Node {
		Object data;
		Node next;

		Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}

	}

	private class LinkedListIterator implements Iterator {
		Node x = head;

		@Override
		public boolean hasNext() {
			return x != null;
		}

		@Override
		public Object next() {
			Object data = x.data;
			x = x.next;
			return data;
		}

	}
}

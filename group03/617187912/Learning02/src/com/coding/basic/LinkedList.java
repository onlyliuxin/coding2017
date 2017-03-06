package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private Node head;
	private int size;

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		checkIndexRange(index);
		if (index == 0) {
			head = new Node(o, head);
			size++;
		} else {
			Node nd = getNode(index-1);
			nd.next = new Node(o, nd.next);
			size++;
		}
	}

	public Object get(int index) {
		return getNode(index).data;
	}

	private Node getNode(int index) {
		Node nd = head;
		for (int i = 0; i < index; i++) {
			nd = nd.next;
		}
		return nd;
	}

	public Object remove(int index) {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		checkIndexRange(index);
		if (index == 0) {
			Object o = head.data;
			head = head.next;
			size--;
			return o;
		} else {
			Node nd = getNode(index - 1);
			Object o = nd.next.data;
			nd.next = nd.next.next;
			size--;
			return o;
		}
	}

	private void checkIndexRange(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("index超出数组界限?");
		}
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		add(0, o);
	}

	public void addLast(Object o) {
		if (size == 0) {
			addFirst(o);
		} else {
			add(size, o);
		}
	}

	public Object removeFirst() {
		return remove(0);
	}

	public Object removeLast() {
		return remove(size - 1);
	}

	public Iterator iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator {

		public Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Object next() {
			Object o = current.data;
			current = current.next;
			return o;
		}
	}

	private static class Node {
		Object data;
		Node next;

		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		for (int i = 0; i < list.size; i++) {
			System.out.println(list.get(i));
		}
		System.out.println(list.get(2));
		list.add(2, 100);
		System.out.println(list.get(2));
		list.addFirst(10);
		System.out.println(list.get(2));
		list.addLast(100);
		System.out.println(list.remove(1));
		System.out.println(list.removeFirst());
		System.out.println(list.removeLast());
	}
}

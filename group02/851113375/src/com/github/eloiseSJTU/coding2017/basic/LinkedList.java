package com.github.eloiseSJTU.coding2017.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private int size = 0;
	
	private Node head;

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		checkBoundsForAdd(index);
		
		if (index == 0) {
			addFirst(o);
		} else if (index == size) {
			addLast(o);
		} else {
			Node cur = head;
			while (--index > 0) {
				cur = cur.next;
			}
			Node newNode = new Node(o, cur.next);
			cur.next = newNode;
			size++;
		}
	}

	public Object get(int index) {
		checkBounds(index);
		
		Node cur = head;
		while (index-- > 0) {
			cur = cur.next;
		}
		return cur.data;
	}

	public Object remove(int index) {
		checkBounds(index);
		
		if (index == 0) {
			return removeFirst();
		} else if (index == size - 1) {
			return removeLast();
		} else {
			Node cur = head;
			int i = 0;
			while (++i < index) {
				cur = cur.next;
			}
			Node node = cur.next;
			Object o = node.data;
			cur.next = node.next;
			node.data = null;
			node.next = null;
			size--;
			return o;
		}
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node newNode = new Node(o, head);
		head = newNode;
		size++;
	}

	public void addLast(Object o) {
		Node newNode = new Node(o, null);
		if (head == null) {
			head = newNode;
		} else {
			Node cur = head;
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next = newNode;
		}
		size++;
	}

	public Object removeFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		
		Object o = head.data;
		Node node = head.next;
		head.data = null;
		head.next = null;
		head = node;
		size--;
		return o;
	}

	public Object removeLast() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		
		if (head.next == null) {
			return removeFirst();
		}
		
		Node cur = head;
		int index = 0;
		while (++index < size - 1) {
			cur = cur.next;
		}
		Object o = cur.next.data;
		cur.next.data = null;
		cur.next = null;
		size--;
		return o;
	}

	public Iterator iterator() {
		return new Itr();
	}
	
	private class Itr implements Iterator {
		
		private Node cur = head;

		@Override
		public boolean hasNext() {
			return cur != null;
		}

		@Override
		public Object next() {
			if (cur == null) {
				throw new NoSuchElementException();
			}
			Object o = cur.data;
			cur = cur.next;
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
	
	private void checkBounds(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private void checkBoundsForAdd(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}
}

package com.coding.basic;

public class LinkedList implements List {
	private static class Node {
		Object data;
		Node next;
	}

	private int size = 0; // 初始化size=0
	private Node head = null;

	@Override
	public void add(Object o) {
		Node node = new Node();
		node.data = o;
		node.next = null;
		if (head == null) {
			head = node;
		} else {
			Node tail = head;
			while (tail.next != null) {
				tail = tail.next;
			}
			tail.next = node;
		}
		size++;
	}

	@Override
	public void add(int index, Object o) {
		if (size < 1) {
			index = 0;
		} else {
			if (index < 0) {
				index = 0;
			}
			if (index > size - 1) {
				index = size - 1;
			}
		}
		Node p = null;// 插入位置的前一节点
		Node q = head;
		while (index > 0) {
			p = q;
			q = q.next;
			index--;
		}
		Node node = new Node();
		node.data = o;
		node.next = null;
		if (p == null) {
			node.next = head;
			head = node;
		} else {
			node.next = p.next;
			p.next = node;
		}
		size++;
	}

	@Override
	public Object get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		Node p = head;
		while (index > 0) {
			p = p.next;
			index--;
		}
		return p.data;
	}

	@Override
	public Object remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		Node removeObj;
		Node p = null;
		Node q = head;
		while (index > 0) {
			p = q;
			q = q.next;
			index--;
		}
		if (p == null) {
			removeObj = head;
			head = head.next;
		} else {
			removeObj = p.next;
			p.next = removeObj.next;
		}
		size--;
		return removeObj.data;
	}

	@Override
	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node node = new Node();
		node.data = o;

		node.next = head;

		head = node;
		size++;
	}

	public void addLast(Object o) {
		Node node = new Node();
		node.data = o;

		if (head == null) {
			head = node;
		} else {
			Node p = head;
			while (p.next != null) {
				p = p.next;
			}
			p.next = node;
		}
		size++;
	}

	public Object removeFirst() {
		if (size < 1) {
			throw new IndexOutOfBoundsException();
		}
		Node removeObj = head;
		head = head.next;
		size--;
		return removeObj.data;
	}

	public Object removeLast() {
		if (size < 1) {
			throw new IndexOutOfBoundsException();
		}
		Node removeObj;
		if (head.next == null) {
			removeObj = head;
			head = null;
		} else {
			Node p = head;
			while (p.next.next != null) {
				p = p.next;
			}
			removeObj = p.next;
			p.next = null;
		}
		size--;
		return removeObj.data;
	}

	public Iterator iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator {

		Node curNode = head;

		@Override
		public boolean hasNext() {
			return curNode != null;
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				throw new IndexOutOfBoundsException();
			}
			Object object = curNode.data;
			curNode = curNode.next;
			return object;
		}

	}
}

package com.coding.basic;

/**
 * 单向链表
 * 
 * @author Administrator
 * 
 */
public class LinkedList implements List {

	private Node head = new Node(null, null);
	private int size = 0;

	public LinkedList() {
		head.next = head;
	}

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		// 1、检查是否在合理范围内
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		Node currNode = findNodeByIndex(index);
		Node newNode = new Node(o, currNode);
		if (index == 0) { // 直接插入到第一个位置
			head = newNode;
		} else {
			Node preNode = findNodeByIndex(index - 1);
			preNode.next = newNode;
		}
		size++;
	}

	public Object get(int index) {
		return findNodeByIndex(index).data;
	}

	public Object remove(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		Node targetNode = this.findNodeByIndex(index);
		Object obj = targetNode.data;
		if (index == 0) {
			targetNode.data = null;
			head = targetNode.next;
		} else {
			Node preNode = findNodeByIndex(index - 1);
			preNode.next = targetNode.next;
		}
		// targetNode.data = null;
		size--;
		return obj;
	}

	public int size() {
		return this.size;
	}

	public void addFirst(Object o) {
		Node nextNode = head;
		Node newNode = new Node(o, nextNode);
		head = newNode;
		size++;
	}

	public void addLast(Object o) {
		Node subNode = new Node(o, null);
		if (size == 0) {
			head = subNode;
		} else {
			Node lastNode = findNodeByIndex(size - 1);
			lastNode.next = subNode;
		}
		size++;
	}

	public Object removeFirst() {
		return this.remove(0);
	}

	public Object removeLast() {
		return this.remove(size - 1);
	}

	private Node findNodeByIndex(int index) {
		Node lastNode = head;
		for (int i = 0; i < index; i++) {
			lastNode = lastNode.next;
		}
		return lastNode;
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

		private int cursor = 0;
		private Node cursorNode = head;

		@Override
		public boolean hasNext() {
			return size != cursor;
		}

		@Override
		public Object next() {
			Object obj = cursorNode.data;
			cursorNode = cursorNode.next;
			cursor++;
			return obj;
		}

	}
}

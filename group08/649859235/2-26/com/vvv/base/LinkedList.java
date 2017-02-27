package com.vvv.base;

public class LinkedList implements IList {
	private int size;
	private Node<Object> head;
	private Node<Object> tail;

	@Override
	public void add(Object o) {
		Node<Object> node = new Node<Object>(o);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
	}

	@Override
	public void add(int index, Object o) {
		check(index);
		Node<Object> node = new Node<Object>(o);
		Node<Object> temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		if (temp.prev != null && temp.next != null) {
			Node<Object> prevNode = temp.prev;
			prevNode.next = node;
			node.prev = prevNode;
			node.next = temp;
			temp.prev = node;
		} else if (temp.prev == null) {
			head = node;
			temp.prev = node;
			node.next = temp;
		} else if (temp.next == null) {
			temp.next = node;
			node.prev = temp;
			tail = node;
		}
		size++;
	}

	@Override
	public Object get(int index) {
		check(index);
		Node<Object> temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp.data;
	}

	@Override
	public Object remove(int index) {
		check(index);
		Node<Object> temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		Object oldValue = temp.data;
		if (temp.prev != null && temp.next != null) {
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			temp = null;
		} else if (temp.prev == null) {
			head = temp.next;
			temp.next.prev = null;
			temp = null;
		} else if (temp.next == null) {
			tail = temp.prev;
			temp.prev.next = null;
			temp = null;
		}
		size--;
		return oldValue;
	}

	public boolean remove(Object obj) {
		Node<Object> temp = head;
		for (int i = 0; i < size; i++) {
			if (obj.equals(temp.data)) {
				remove(i);
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	private void check(int index) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("index: " + index + ", size: "
					+ size);
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("hiding")
	private static class Node<Object> {
		Object data;
		Node<Object> next;
		Node<Object> prev;

		public Node(Object data) {
			this.data = data;
		}
	}

}

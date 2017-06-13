package main.week01.data_structure;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private Node head;
	private int size = 0;

	public void add(Object o) {
		if (isEmpty()) {
			addFirst(o);
		} else {
			addLast(o);
		}
	}

	public boolean isEmpty() {
		return (size == 0) ? true : false;
	}

	public void add(int index, Object o) {
		rangeCheck(index);
		if (index == 0) {
			addFirst(o);
		} else if (index == size) {
			addLast(o);
		} else {
			Node pre = getNode(index - 1);
			Node node = new Node(o);
			node.next = pre.next;
			pre.next = node;
			size++;
		}
	}

	private void rangeCheck(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}

	public Object get(int index) {
		rangeCheck(index);
		Node dest = head;
		for (int i = 0; i < index; i++) {
			dest = dest.next;
		}
		return dest.data;
	}

	public Node getNode(int index) {
		rangeCheck(index);
		Node dest = head;
		for (int i = 0; i < index; i++) {
			dest = dest.next;
		}
		return dest;
	}

	public Object remove(int index) {
		rangeCheck(index);
		if (index == 0) {
			return removeFirst();
		}else if(index == size){
			return removeLast();
		}
		Node pre = getNode(index - 1);
		Node dest = pre.next;
		pre.next = dest.next;
		size--;
		return dest.data;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node node = new Node(o);
		node.next = head;
		head = node;
		size++;
	}

	public void addLast(Object o) {
		Node last = getNode(size - 1);
		Node node = new Node(o);
		last.next = node;
		size++;
	}

	public Object removeFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node newhead = head;
		Node dest = head;
		head = head.next;
		size--;
		return dest.data;
	}

	public Object removeLast() {
		Node newLastNode = getNode(size - 2);
		Node oldLastNode = newLastNode.next;
		newLastNode.next = null;
		size--;
		return oldLastNode;
	}

	private static class Node {
		Object data;
		Node next;

		Node(Object data) {
			this.data = data;
			next = null;
		}
	}

	public class LinkedListIterator implements Iterator {

		private LinkedList list;

		private int position = 0;

		private LinkedListIterator() {
		}

		private LinkedListIterator(LinkedList list) {
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			return position + 1 <= list.size;
		}

		@Override
		public Object next() {
			return list.get(position++);
		}

	}

	public LinkedListIterator iterator() {
		return new LinkedListIterator(this);
	}
}

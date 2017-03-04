package com.coding.basic.list;

public class LinkedList implements List {

	private Node head;
	private Node last;
	private int index;

	private Node getNode(int index) {
		Node tempNode = head;
		for (int i = 0; i < index; i++) {
			tempNode = tempNode.next;
		}
		return tempNode;
	}
	
	public void add(Object o) {
		Node node = new Node(o, null);
		if (null == head) {
			head = node;
		} else {
			last.next = node;
		}
		last = node;
		index++;
	}

	public void add(int index, Object o) {
		if (index > size()) {
			throw new ArrayIndexOutOfBoundsException("越界");
		}
		if (index == size()) {
			addLast(o);
		} else if (index == 0) {
			addFirst(o);
		} else {
			Node tempLast = getNode(index-1);
			Node newNode = new Node(o, null);
			Node tempNext = tempLast.next;
			tempLast.next = newNode;
			newNode.next = tempNext;
			this.index++;
		}
	}

	public Object get(int index) {
		if (index >= size()) {
			throw new ArrayIndexOutOfBoundsException("越界");
		}
		return getNode(index).data;
	}

	public Object remove(int index) {
		if (index >= size()) {
			throw new ArrayIndexOutOfBoundsException("越界");
		}
		Object object;
		if (index == 0) {
			object = head.data;
			head = head.next;
		} else {
			Node lastNode = getNode(index-1);
			if (index == size()-1) {
				object = last.data;
				last = lastNode;
				lastNode.next = null;
			} else {
				Node node = lastNode.next;
				Node next = node.next;
				lastNode.next = next;
				object = node.data;
				node = null;
			}
		}
		this.index--;
		return object;
	}

	public int size() {
		return index;
	}

	public void addFirst(Object o) {
		Node node = new Node(o, null);
		if (null != head) node.next = head;
		if (null == last) last = node;
		head = node;
		index++;
	}

	public void addLast(Object o) {
		Node node = new Node(o, null);
		if (null == head) head = node;
		last.next = node;
		last = node;
		index++;
	}

	public Object removeFirst() {
		return remove(0);
	}

	public Object removeLast() {
		return remove(size()-1);
	}

	public Iterator iterator() {
		return new LinkedListIterator();
	}

	private static class Node {
		private Object data;
		private Node next;

		public Node(Object data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
	}

	private class LinkedListIterator implements Iterator {

		private int index = 0;
		private Node last = head;
		@Override
		public boolean hasNext() {
			return index < size();
		}

		@Override
		public Object next() {
			Object object = last.data;
			last = last.next;
			index++;
			return object;
		}

	}

}

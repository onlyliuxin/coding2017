package main;

import utils.ListUtils;

public class LinkedList implements List {
	private Node head;
	private int size;

	private static class Node {
		private Object data;
		private Node next;
	}

	@Override
	public void add(Object o) {
		add(size, o);
	}

	@Override
	public void add(int index, Object o) {
		ListUtils.CheckIndexInRange(0, size, index);
		if (0==size) {
			head = new Node();
			head.data = o;
			size++;
			return;
		}
		if (0 == index) {
			Node node = new Node();
			node.data = o;
			node.next = head;
			head = node;
			size++;
			return;
		}
		if (index == size) {
			Node node = head;
			while (null != node.next) {
				node = node.next;
			}
			Node addNode = new Node();
			addNode.data = o;
			node.next = addNode;
			size++;
			return;
		}
		Node node = head;
		for (int i = 0; i < index - 1; i++) {
			node = node.next;
		}
		Node addNode = new Node();
		addNode.data = o;
		addNode.next = node.next;
		node.next = addNode;
		size++;
	}

	@Override
	public Object remove(int index) {
		ListUtils.CheckIndexInRange(0, size - 1, index);
		Node node = head;
		if (0 == index) {
			head = head.next;
			size--;
			return node.data;
		}
		for (int i = 0; i < index - 1; i++) {
			node = node.next;
		}
		Node removeNode = node.next;
		node.next = removeNode.next;
		size--;
		return removeNode.data;
	}

	@Override
	public Object get(int index) {
		ListUtils.CheckIndexInRange(0, size - 1, index);
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.data;
	}

	@Override
	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new Iterator() {
			private int cursor;

			@Override
			public boolean hasNext() {
				if (size > 0) {
					return cursor < size;
				} else {
					return false;
				}

			}

			@Override
			public Object next() {
				Object tag = get(cursor);
				cursor++;
				return tag;
			}
		};
	}

}

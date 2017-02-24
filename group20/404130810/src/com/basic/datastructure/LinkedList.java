package com.basic.datastructure;

public class LinkedList implements List {
	private Node first;
	private Node last;

	private int size;

	public void add(Object o) {
		addLast(o);
	}

	public void add(int index, Object o) {
		checkRange(index);
		if (index == 0) {
			addFirst(o);
		} else if (index == size) {
			addLast(o);
		} else {

		}
	}

	public Object get(int index) {
		checkRange(index);
		if(size > 0){
			int loopTimes = 0;
			Node p = first;
			while(index > loopTimes){
				p = p.next;
				loopTimes ++;
			}
			return p;
		}

		return null;
	}

	public Object remove(int index) {
		return null;
	}

	public int size() {
		return size;
	}

	private void addFirst(Object o) {
		if (size == 0) {
			first = new Node(o);
			last = first;
		} else {
			Node tmpNode = new Node(o);
			tmpNode.next = first;
			first = tmpNode;
		}
		size++;
	}

	private void addLast(Object o) {
		if (size == 0) {
			first = new Node(o);
			last = first;
		} else {
			last.next = new Node(o);
			last = last.next;
		}
		size++;
	}

	public Object removeFirst() {
		Node tmpNode = first;
		first = first.next;
		size--;
		return tmpNode;
	}

	public Object removeLast() {
		Node tmpNode = last;

		return null;
	}

	private void checkRange(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + "Size: " + size);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node p = first;
		while (p != null) {
			sb.append(p.item + "\n");
			p = p.next;
		}
		return sb.toString();
	}

	private static class Node {
		private Object item;
		private Node next;

		Node(Object item) {
			this.item = item;
		}
	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 5; i++) {
			list.add(i);
		}
		list.get(5);

		System.out.println(list);
	}
}

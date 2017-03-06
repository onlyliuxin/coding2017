package com.coding.basic;

public class LinkedList implements List {

	private Node head;
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(8);
		list.add(10);
		list.add(3,18888);
		list.remove(8);
		list.addFirst(111111111);
		list.addFirst(2222);
		list.addLast(5678);
		list.addLast(1234);
		System.out.println(list.removeFirst());
		System.out.println(list.removeLast());
		int len = list.size();
		System.out.print(list.size()+" : ");
		for (int i = 0; i < len; i++) {
			System.out.print(list.get(i)+" ");
		}
		
		System.out.println("\n===========================");
		System.out.println(list.get(5));
	}
	public void add(Object o) {
		Node node = new Node(o);
		if (head == null) {
			head = node;
			return;
		}
		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = node;
	}

	public void add(int index, Object o) {
		Node node = new Node(o);
		if (index == 0) {
			node.next = head;
			head = node;
			return;
		}
		Node pre = head;
		Node current = head.next;
		for (int i = 0; i < index; i++) {
			if (i == index - 1) {
				pre.next = node;
				node.next = current;
				return;
			}
			pre = pre.next;
			current = current.next;
		}
	}

	public Object get(int index) {
		int i = 0;
		Node current = head;
		while (current != null) {
			if (i == index) {
				return current.data;
			}
			current = current.next;
			i++;
		}
		return null;
	}

	public Object remove(int index) {
		if (index == 0) {
			head = head.next;
		}
		Node pre = head;
		Node current = head.next;
		for (int i = 0; i < index; i++) {
			if (i == index - 1) {
				pre.next = current.next;
				current.next = null;
				return current.data;
			}
			pre = pre.next;
			current = current.next;
		}
		return null;
	}

	public int size() {
		int size = 0;
		Node current = head;
		while (current != null) {
			current = current.next;
			size++;
		}
		return size;
	}

	public void addFirst(Object o) {
		Node node = new Node(o);
		if (head == null) {
			head = node;
			return;
		}
		node.next = head;
		head = node;
	}

	public void addLast(Object o) {
		Node node = new Node(o);
		if (head == null) {
			head = node;
			return;
		}
		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = node;
	}

	public Object removeFirst() {
		if (head == null) {
			return null;
		}
		Object data = head.data;
		head = head.next;
		return data;
	}

	public Object removeLast() {
		if (head == null) {
			return null;
		}
		Node current = head;
		if (current.next == null) {
			return current.data;
		}
		Object data = null;
		while (current.next != null) {
			if (current.next.next != null) {
				current = current.next;
				continue;
			}
			data = current.next.data;
			current.next = null;
		}
		return data;
	}

	public Iterator iterator() {
		return null;
	}

	private static class Node {
		Object data;
		Node next;

		public Node(Object data) {
			this.data = data;
		}
	}
}

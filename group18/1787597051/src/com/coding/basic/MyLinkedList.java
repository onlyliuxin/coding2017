package com.coding.basic;

public class MyLinkedList implements MyList {
	private int size;
	private Node head;

	public MyLinkedList() {
		head = new Node();
		head.data = "Í·½áµã";
		head.next = null;
	}

	public void add(Object o) {
		Node p = head;
		while (p.next != null) {
			p = p.next;
		}
		Node p3 = new Node();
		p3.data = o;
		p.next = p3;
		size++;
	}

	public void add(int index, Object o) {
		int num = 0;
		Node p = head;
		while (p.next != null) {
			if (num == index) {
				Node p2 = new Node();
				p2.data = o;
				p2.next = p.next;
				p.next = p2;
				size++;
			}
			p = p.next;
			num++;
		}
	}

	public Object get(int index) {
		int num = 0;
		Node p = head.next;
		while (p != null) {
			if (num == index) {
				return p.data;
			}
			p = p.next;
			num++;
		}
		return null;
	}

	public Object remove(int index) {
		int num = 0;
		Node p = head;
		while (p.next != null) {
			if (num == index) {
				Node p2 = p.next;
				p.next = p.next.next;
				size--;
				return p2.data;
			}
			p = p.next;
			num++;
		}
		return null;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node p = new Node();
		p.data = o;
		p.next = head.next;
		head.next = p;
		size++;
	}

	public void addLast(Object o) {
		Node p = head;
		while (p.next != null) {
			p = p.next;
		}
		Node p2 = new Node();
		p2.data = o;
		p.next = p2;
		size++;
	}

	public Object removeFirst() {
		Node p = head;
		if (p.next != null) {
			Node p2 = head.next;
			p.next = p.next.next;
			size--;
			return p2.data;
		}
		return null;
	}

	public Object removeLast() {
		Node p = head;
		if (p.next != null) {
			while (p.next.next != null) {
				p = p.next;
			}
			Node p2 = new Node();
			p2 = p.next;
			p.next = null;
			size--;
			return p2.data;
		}
		return null;
	}
	/*
	 * public Iterator iterator(){ return null; }
	 */

	private static class Node {
		Object data;
		Node next;
	}
}

package com.coding.basic;

import java.util.NoSuchElementException;

public class LinkedList implements List {

	private int size = 0;

	private Node head;
	
	
	public Node getHead() {
		return head;
	}

	public LinkedList() {
		this.head = new Node();
	}

	@Override
	public String toString() {
		return "[" + head + "]";
	}

	private void outOfBoundsForAdd(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("数组下标越界");
	}

	private void outOfBoundsForOther(int index) {
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException("数组下标越界");
	}

	public void add(Object o) {
			Node node = head;
			while (node.next != null) {
				node = node.next;
			}
			node.next = new Node(o);
		size++;
	}

	public void add(int index, Object o) {
		outOfBoundsForAdd(index);
		if(size == index)
			add(o);
		else{
			Node prevNode = head;
			for (int i = 0; i < index; i++) {
				prevNode = prevNode.next;
			}
			Node nextNode = prevNode.next;
			Node node = new Node(o);
			prevNode.next = node;
			node.next = nextNode;
			size++;
		}
	}

	public Object get(int index) {
		outOfBoundsForOther(index);
		Node node = head;
		for (int i = 0; i <= index; i++) {
			node = node.next;
		}
		return node.data;
	}

	public Object remove(int index) {
		outOfBoundsForOther(index);
		Node prevNode = head;
		for (int i = 0; i < index; i++) {
			prevNode = prevNode.next;
		}
		Node node = prevNode.next;
		prevNode.next = node.next;
		size--;
		return node.data;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object o) {
		Node newNode = new Node(o);
		Node node = head.next;
		head.next = newNode;
		newNode.next = node;
		size++;
	}

	public void addLast(Object o) {
		Node node = head;
		while (node.next != null) {
			node = node.next;
		}
		node.next = new Node(o);
		size++;
	}

	private void noSuchEle() {
		if (head.next == null)
			throw new NoSuchElementException("没有这个元素");
	}

	public Object removeFirst() {
		noSuchEle();
		Node node = head.next;
		head.next = node.next;
		size--;
		return node.data;
	}

	public Object removeLast() {
		noSuchEle();
		Node node = head;
		for(int i=0;i<size-1;i++){
			node = node.next;
		}
		Node reNode = node.next;
		node.next = null;
		size--;
		return reNode.data;
	}

	public static class Node {
		Object data;
		Node next;

		@Override
		public String toString() {
			return data + ", " + next;
		}

		public Node() {
		}

		public Node(Object data) {
			this.data = data;
		}
	}
}

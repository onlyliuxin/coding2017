package com.coding.basic;

public class LinkedList implements List {

	private Node first = null;
	private Node last = null;
	private int size = 0;

	public void add(Object o){
		Node node = new Node(o);
		if (first == null) {
			first = node;
		} else {
			last.next = node;
			node.prev = last;
		}
		last = node;
		size++;
	}

	public void add(int index , Object o) {
		if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException();

		Node node = new Node(o);

		if (first == null) {
			first = node;
			last = node;
		} else {
			if (index == 0) {
				node.next = first;
				first.prev = node;
                first = node;
			} else if (index == size) {
				last.next = node;
				node.prev = last;
				last = node;
			} else {
				Node temp = first;
				while (--index > 0) {
					temp = temp.next;
				}
				node.next = temp.next;
				temp.next.prev = node;
				temp.next = node;
				node.prev = temp;
			}
		}
		size++;
	}
	public Object get(int index){
		if (index < 0 || index > size - 1) throw new ArrayIndexOutOfBoundsException();
		Node node = first;
		while (index-- > 0) {
			node = node.next;
		}
		return node.data;
	}

	public Object remove(int index){
		if (index < 0 || index >= size) throw new ArrayIndexOutOfBoundsException();

		Node node = null;
		if (index == 0) {
			node = first;
			if (size == 1) {
				first = null;
				last = null;
			} else {
				first = first.next;
				first.prev = null;
			}
		} else if (index == size - 1) {
			node = last;
			last = last.prev;
			last.next = null;
		} else {
			node = first;
			Node temp = null;
			while (index-- > 0) {
				node = node.next;
			}
			temp = node.prev;
			temp.next = node.next;
			node.next.prev = temp;
		}
		size--;
		return node.data;
	}

	public int size(){
		return size;
	}
	
	public void addFirst(Object obj){
		add(0, obj);
	}

	public void addLast(Object obj){
		add(size, obj);
	}

	public Object removeFirst(){
		return remove(0);
	}

	public Object removeLast(){
		return remove(size - 1);
	}

	public Iterator iterator(){

		if (first == null || last == null) throw new IllegalStateException();

		return new InnerIterator();
	}

	private class InnerIterator implements Iterator {

		private Node nextNode = first;

		public boolean hasNext() {
			return nextNode != null;
		}

		public Object next() {
			if (!hasNext()) throw new ArrayIndexOutOfBoundsException();
			Node node = nextNode;
			nextNode = nextNode.next;
			return node.data;
		}

		public Object remove() {
			if (nextNode == first) throw new IllegalStateException();

			Node node = nextNode.prev;
			if (nextNode == first.next) {
				first = nextNode;
				first.prev = null;
			} else if (nextNode == null) {
				node = last;
				last = last.prev;
				last.next = null;
			} else {
				node.prev = node.next;
				node.next.prev = node.prev;
			}
			return node.data;
		}
	}

	private static class Node{

		Object data;
		Node next;
		Node prev;

		public Node(Object data) {
			this.data = data;
			next = null;
			prev = null;
		}
	}
}
